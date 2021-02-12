package com.xusm.forum.quartz;

import com.xusm.forum.dao.ArticleDao;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Component
public class ArticleClickTask extends QuartzJobBean {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ArticleDao articleDao;

    private static final String KEY = "article:*";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Set<String> keys = this.redisTemplate.keys(KEY);
        if(!CollectionUtils.isEmpty(keys)){
            keys.forEach(k->{
                Integer count = Integer.parseInt(redisTemplate.opsForValue().get(k));
                Long id = Long.parseLong(StringUtils.substringAfter(k,"id:"));
                this.articleDao.updateClick(id,count);
                redisTemplate.delete(k);
            });
        }
    }

}
