package com.xusm.forum.config.interceptor.security;

import com.xusm.forum.constant.FunctionType;
import com.xusm.forum.config.prop.JwtProperties;
import com.xusm.forum.dao.FunctionDao;
import com.xusm.forum.entity.Function;
import com.xusm.forum.entity.UserInfo;
import com.xusm.forum.utils.CookieUtils;
import com.xusm.forum.utils.JwtUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CommentRoleInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private FunctionDao functionDao;

    @Resource
    private JwtProperties jwtProperties;

    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        Function function = new Function();
        function.setFunName("评论权限");
        // 评论权限是只有登录后才能评论
        if(this.functionDao.selectOne(function).getFunState() == FunctionType.COMMENT_FOR_LOGIN){
            // 校验 token
            String cookieValue = CookieUtils.getCookieValue(request,jwtProperties.getCookieName());
            if(StringUtils.isNotBlank(cookieValue)){
                UserInfo info = JwtUtils.getInfoFromToken(cookieValue, jwtProperties.getPublicKey());
                if(info != null){
                    THREAD_LOCAL.set(info.getId()); // 将 userId 放到 ThreadLocal
                    return true;
                }
            }

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 评论权限是所有人都能评论
        String cookieValue = CookieUtils.getCookieValue(request,jwtProperties.getCookieName());
        if(StringUtils.isNotBlank(cookieValue)){
            UserInfo info = JwtUtils.getInfoFromToken(cookieValue, jwtProperties.getPublicKey());
            if(info != null){
                // 将 userId 放到 ThreadLocal
                // 如果是未登录的用户，则此时 userId 为 null
                THREAD_LOCAL.set(info.getId());
            }
        }
        return true;
    }

    public static Long getUserId(){
        return THREAD_LOCAL.get();
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        THREAD_LOCAL.remove();
    }
}
