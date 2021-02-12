package com.xusm.forum.config.interceptor.info;

import com.xusm.forum.constant.WebsiteByHourRedisKey;
import com.xusm.forum.constant.WebsiteRedisKey;
import com.xusm.forum.service.WebsiteDetailService;
import com.xusm.forum.service.WebsiteService;
import com.xusm.forum.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UvInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private WebsiteService websiteService;

    public static final String VISIT_COOKIE_NAME = "XUSM_VISIT_ID";

    @Autowired
    private WebsiteDetailService websiteDetailService;

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        String visitId = CookieUtils.getCookieValue(request, VISIT_COOKIE_NAME);
        if(StringUtils.isNotBlank(visitId)){
            THREAD_LOCAL.set(CookieUtils.getCookieValue(request, VISIT_COOKIE_NAME));
            return true;
        }

        String cookieValue = UUIDUtils.getUUID32();
        CookieUtils.setCookie(request,response,VISIT_COOKIE_NAME,cookieValue,CookieDeathUtils.getCookieDeath()*60);
        THREAD_LOCAL.set(cookieValue);

        this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_UV);
        this.websiteDetailService.insertByHour(WebsiteByHourRedisKey.PREFIX_KEY_UV);

        return true;
    }

    public static String getVisitId(){
        return THREAD_LOCAL.get();
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        THREAD_LOCAL.remove();
    }
}
