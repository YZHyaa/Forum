package com.xusm.forum.testRedis;

import com.xusm.forum.constant.redis.ArticleRedisKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestKey {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test() {
        String prefixKey = ArticleRedisKey.RANK_PREFIX_KEY;
        String key1 = prefixKey + "12-04";
        String key2 = prefixKey + "12-05";
        String key3 = prefixKey + "12-06";
        String comKey = "article:weekrank";
        redisTemplate.opsForZSet().add(key1, "A", 6);
        redisTemplate.opsForZSet().add(key2, "B", 8);
        redisTemplate.opsForZSet().add(key3, "C", 1);
        List<String> list = Arrays.asList(key2, key3);
        redisTemplate.opsForZSet().unionAndStore(key1, list, comKey);

        redisTemplate.expire(key1, 30, TimeUnit.DAYS);
    }



}
