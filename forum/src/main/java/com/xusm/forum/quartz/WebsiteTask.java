package com.xusm.forum.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xusm.forum.service.WebsiteService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class WebsiteTask extends QuartzJobBean {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WebsiteService websiteService;

    private static final String KEY = "website:*";

    @Override
    protected void executeInternal(JobExecutionContext context) {
        Set<String> keys = this.redisTemplate.keys(KEY);
        if(CollectionUtils.isEmpty(keys)){
            return;
        }
        this.websiteService.insertAllToDb(keys);
        keys.forEach(k-> this.redisTemplate.delete(k));


    }
}
