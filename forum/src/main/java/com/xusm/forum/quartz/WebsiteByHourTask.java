package com.xusm.forum.quartz;

import com.xusm.forum.service.WebsiteDetailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Component
public class WebsiteByHourTask extends QuartzJobBean {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WebsiteDetailService websiteDetailService;

    private static final String KEY = "webByHour:*";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Set<String> keys = this.redisTemplate.keys(KEY);
        if(CollectionUtils.isEmpty(keys)){
            return;
        }
        this.websiteDetailService.insertJsonToDB(keys);
        keys.forEach(k->{
            this.redisTemplate.delete(k);
        });
    }
}
