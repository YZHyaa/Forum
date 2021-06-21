package com.xusm.forum.quartz;

import com.xusm.forum.constant.redis.ArticleRedisKey;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class ArticleRankTask extends QuartzJobBean {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY = ArticleRedisKey.RANK_PREFIX_KEY + "*";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String lastestKey = getLastTimeString(30);
        if (redisTemplate.hasKey(lastestKey)) {
            redisTemplate.delete(lastestKey);
        }
    }

    /**
     * 获取过去第 n 天的 MM-dd
     */
    private String getLastTimeString(int days) {
        long time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < days; i++) {
            time -= 60 * 1000 * 60 * 24;
        }
        Date date = new Date(time);
        String format = new SimpleDateFormat("MM-dd").format(date);

        return format;
    }
}
