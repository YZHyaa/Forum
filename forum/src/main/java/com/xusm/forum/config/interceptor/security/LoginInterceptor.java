package com.xusm.forum.config.interceptor.security;

import com.xusm.forum.config.prop.JwtProperties;
import com.xusm.forum.entity.UserInfo;
import com.xusm.forum.utils.CookieUtils;
import com.xusm.forum.utils.JwtUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JwtProperties jwtProperties; // 获取 cookie 信息

    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        // 获取 cookie 中的 token
        String cookieValue = CookieUtils.getCookieValue(request, this.jwtProperties.getCookieName());
        // 如果请求中没有 token
        if(StringUtils.isBlank(cookieValue)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 校验 token 合法性，是否包含了 user 信息
        UserInfo user = JwtUtils.getInfoFromToken(cookieValue,jwtProperties.getPublicKey());
        if(user == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 将 token 中保存的当前 user 的 id 放入 ThreadLocal
        THREAD_LOCAL.set(user.getId());

        return true;
    }

    public static Long getUserId(){
        return THREAD_LOCAL.get();
    }

    /**
     * 在请求处理完后删除 ThreadLocal 中保存的 id
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        THREAD_LOCAL.remove();
    }
}
