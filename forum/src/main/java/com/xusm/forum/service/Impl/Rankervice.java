package com.xusm.forum.service.Impl;
 
import com.xusm.forum.constant.redis.ArticleRedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Rankervice {
 
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 增加文章 score
     */
    public void increby(long articleId) {
        String date = new SimpleDateFormat("MM-dd").format(new Date());
        String rankKey = ArticleRedisKey.RANK_PREFIX_KEY + date;

        String id = String.valueOf(articleId);
        // 判断zset中是否已经有该成员了
        if (redisTemplate.opsForZSet().rank(rankKey, id) == null) {
            // 没有就初始化
            redisTemplate.opsForZSet().add(rankKey, id, 1);
        } else {
            // 有就 score++
            redisTemplate.opsForZSet().incrementScore(rankKey, id, 1);
        }
    }

    /**
     * 获取当日排行
     * @return 前五篇文章 ID
     */
    public List<Long> queryTodayRankList() {
        String date = new SimpleDateFormat("MM-dd").format(new Date());
        String rankKey = ArticleRedisKey.RANK_PREFIX_KEY + date;

        return getRankList(rankKey);
    }

    /**
     * 获取这一周（过去七天）排行
     */
    public List<Long> queryWeekRankList() {
        String prefixKey = ArticleRedisKey.RANK_PREFIX_KEY;
        String todayKey = prefixKey + getLastTimeString(0);

        List<String> keys = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            String key = prefixKey + getLastTimeString(i);
            keys.add(key);
        }

        redisTemplate.opsForZSet().unionAndStore(todayKey, keys, ArticleRedisKey.RANK_WEED_PREFIX_KEY);
        return getRankList(ArticleRedisKey.RANK_WEED_PREFIX_KEY);
    }

    /**
     * 获取这一月（过去30天）排行
     */
    public List<Long> queryMonthRankList() {
        String prefixKey = ArticleRedisKey.RANK_PREFIX_KEY;
        String todayKey = prefixKey + getLastTimeString(0);

        List<String> keys = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            String key = prefixKey + getLastTimeString(i);
            keys.add(key);
        }

        redisTemplate.opsForZSet().unionAndStore(todayKey, keys, ArticleRedisKey.RANK_MONTH_PREFIX_KEY);
        return getRankList(ArticleRedisKey.RANK_MONTH_PREFIX_KEY);
    }

    private List<Long> getRankList(String rankKey) {
        Set<String> ids = redisTemplate.opsForZSet().reverseRange(rankKey, 0, 5);
        return ids.stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
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