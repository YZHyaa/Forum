package com.xusm.forum.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xusm.forum.constant.redis.WebsiteByHourRedisKey;
import com.xusm.forum.dao.WebsiteDetailDao;
import com.xusm.forum.entity.WebsiteDetail;
import com.xusm.forum.service.WebsiteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WebsiteDetailServiceImpl implements WebsiteDetailService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WebsiteDetailDao websiteDetailDao;

    private static final String HOUR_KEY = "webByHour:*";

    @Override
    public void insertByHour(String key) {
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        String format = new SimpleDateFormat("HH").format(new Date());
        if(hashOps.hasKey(format)){
            Integer count = Integer.parseInt(hashOps.get(format).toString());
            count++;
            hashOps.put(format,count.toString());
        }else {
            hashOps.put(format,"1");
        }
    }

    @Override
    public void insertJsonToDB(Set<String> keys) {
        Map<String,String> map = new HashMap<>();
        keys.forEach(k->{
            BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(k);
            Map<Object, Object> entries = hashOps.entries();
            String json = JSON.toJSONString(entries);
            map.put(k,json);
        });

        WebsiteDetail websiteDetail = new WebsiteDetail();
        websiteDetail.setPv(map.get(WebsiteByHourRedisKey.PREFIX_KEY_PV));
        websiteDetail.setUv(map.get(WebsiteByHourRedisKey.PREFIX_KEY_UV));
        websiteDetail.setIp(map.get(WebsiteByHourRedisKey.PREFIX_KEY_IP));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        try {
            websiteDetail.setSchedule(simpleDateFormat.parse(format));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.websiteDetailDao.insertSelective(websiteDetail);
    }


}
