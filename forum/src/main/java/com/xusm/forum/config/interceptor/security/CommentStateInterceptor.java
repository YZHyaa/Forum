package com.xusm.forum.config.interceptor.security;

import com.xusm.forum.constant.FunctionType;
import com.xusm.forum.dao.FunctionDao;
import com.xusm.forum.entity.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CommentStateInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private FunctionDao functionDao;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        Function fun = new Function();
        fun.setFunName("文章评论");
        if(this.functionDao.selectOne(fun).getFunState() == FunctionType.COMMENT_ON){
            return true;
        }else{
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
    }
}
