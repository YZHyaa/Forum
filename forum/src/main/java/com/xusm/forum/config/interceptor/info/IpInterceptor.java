package com.xusm.forum.config.interceptor.info;

import com.xusm.forum.constant.WebsiteByHourRedisKey;
import com.xusm.forum.service.WebsiteDetailService;
import com.xusm.forum.service.WebsiteService;
import com.xusm.forum.utils.IpAddrUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IpInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private WebsiteService websiteService;

    @Autowired
    private WebsiteDetailService websiteDetailService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String remoteAddr = IpAddrUtils.getIpAddr(request);
        if(StringUtils.isNotBlank(remoteAddr)){
            if( !websiteService.hasRomteIp(remoteAddr)){
                this.websiteService.insertRomteIp(remoteAddr);
                this.websiteDetailService.insertByHour(WebsiteByHourRedisKey.PREFIX_KEY_IP);
            }
        }
        return true;
    }
}
