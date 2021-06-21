//package com.xusm.forum.quartz;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import java.util.Set;
//
//@Component
//public class LikeAddrTask extends QuartzJobBean {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    private static final String KEY = "like:*";
//
//    @Override
//    protected void executeInternal(JobExecutionContext context) {
//        Set<String> keys = this.redisTemplate.keys(KEY);
//        if(CollectionUtils.isEmpty(keys)){
//            return;
//        }
//        keys.forEach(k -> this.redisTemplate.delete(k));
//    }
//}
