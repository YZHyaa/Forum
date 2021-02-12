package com.xusm.forum.service.Impl;

import com.xusm.forum.constant.WebsiteRedisKey;
import com.xusm.forum.dao.WebsiteDao;
import com.xusm.forum.entity.Website;
import com.xusm.forum.service.WebsiteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Service
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WebsiteDao websiteDao;

    @Override
    public void insertVisit(String key){
        if(!redisTemplate.hasKey(key)){
            redisTemplate.opsForValue().set(key,"0");
        }
        Integer count = Integer.parseInt(this.redisTemplate.opsForValue().get(key));
        count++;
        this.redisTemplate.opsForValue().set(key,count.toString());
    }

    @Override
    public Boolean hasRomteIp(String addr) {
        return redisTemplate.opsForSet().isMember(WebsiteRedisKey.PREFIX_KEY_IPS,addr);
    }

    @Override
    public void insertRomteIp(String addr) {
        redisTemplate.opsForSet().add(WebsiteRedisKey.PREFIX_KEY_IPS,addr);
    }


    @Override
    public void insertAllToDb(Set<String> keys) {
        Map<String,Integer> map = new HashMap<>();
        keys.forEach(k ->{
            if(StringUtils.equals(WebsiteRedisKey.PREFIX_KEY_IPS,k)){
                map.put(k,this.redisTemplate.opsForSet().size(k).intValue());
            }else {
                map.put(k,Integer.parseInt(redisTemplate.opsForValue().get(k)));
            }
        });

        Website website = new Website();
        website.setWebIp(map.get(WebsiteRedisKey.PREFIX_KEY_IPS));
        website.setWebInfo(map.get(WebsiteRedisKey.PREFIX_KEY_INFO));
        website.setWebJs(map.get(WebsiteRedisKey.PREFIX_KEY_JS));
        website.setWebUv(map.get(WebsiteRedisKey.PREFIX_KEY_UV));
        website.setWebLogin(map.get(WebsiteRedisKey.PREFIX_KEY_LOGIN));
        website.setWebRegister(map.get(WebsiteRedisKey.PREFIX_KEY_REGISTER));
        website.setWebPv(map.get(WebsiteRedisKey.PREFIX_KEY_PV));
        website.setWebSm(map.get(WebsiteRedisKey.PREFIX_KEY_SM));
        website.setWebSource(map.get(WebsiteRedisKey.PREFIX_KEY_SOURCE));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        try {
            website.setSchedule(simpleDateFormat.parse(format));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.websiteDao.insertSelective(website);
    }
}
