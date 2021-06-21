package com.xusm.forum.config.interceptor.info;

import com.xusm.forum.constant.redis.WebsiteByHourRedisKey;
import com.xusm.forum.constant.redis.WebsiteRedisKey;
import com.xusm.forum.service.WebsiteDetailService;
import com.xusm.forum.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PvInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private WebsiteService websiteService;

    @Autowired
    private WebsiteDetailService websiteDetailService;

    // 只要有请求进来，就将 PV++
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_PV);
        this.websiteDetailService.insertByHour(WebsiteByHourRedisKey.PREFIX_KEY_PV);
        return true;
    }
}
