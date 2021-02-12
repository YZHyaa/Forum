package com.xusm.forum.service.Impl;

import com.alibaba.druid.util.StringUtils;
import com.aliyuncs.exceptions.ClientException;
import com.xusm.forum.config.prop.SmsProperties;
import com.xusm.forum.service.CodeService;
import com.xusm.forum.utils.NumberUtils;
import com.xusm.forum.utils.SmsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class CodeServiceImpl implements CodeService {

    static final String KEY_PREFIX = "code:phone:";

    static final Logger LOGGER = LoggerFactory.getLogger(CodeService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SmsUtils smsUtils;

    @Override
    public Boolean sendCode(String phone) {
        String code = NumberUtils.generateCode(6);
        this.redisTemplate.opsForValue().set(KEY_PREFIX+phone,code,5, TimeUnit.MINUTES);
        try {
            this.smsUtils.sendSms(phone,code);
        } catch (ClientException e) {
            LOGGER.error("发送短信失败："+ phone +","+code);
            return false;
        }
        return true;
    }

    public Boolean checkCacheCode(String phone,String code){
        String cacheCode = this.redisTemplate.opsForValue().get(KEY_PREFIX+phone);
        if(StringUtils.equals(code,cacheCode)){
            this.redisTemplate.delete(KEY_PREFIX+phone);
            return true;
        }
        return false;
    }
}
