package com.xusm.forum.quartz;

import com.xusm.forum.service.SourceService;
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
public class SourceClickTask extends QuartzJobBean {

    @Autowired
    private SourceService  sourceService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY = "source:*";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Set<String> keys = this.redisTemplate.keys(KEY);
        if(!CollectionUtils.isEmpty(keys)){
            keys.forEach(k->{
                Integer count = Integer.parseInt(this.redisTemplate.opsForValue().get(k));
                this.sourceService.updateSourceClick(Long.parseLong(StringUtils.substringAfter(k,"id:")),count);
                this.redisTemplate.delete(k);
            });
        }

    }
}
