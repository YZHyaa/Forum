package com.xusm.forum.config.interceptor;

import com.xusm.forum.config.interceptor.info.IpInterceptor;
import com.xusm.forum.config.interceptor.info.PvInterceptor;
import com.xusm.forum.config.interceptor.info.UvInterceptor;
import com.xusm.forum.config.interceptor.security.CommentRoleInterceptor;
import com.xusm.forum.config.interceptor.security.CommentStateInterceptor;
import com.xusm.forum.config.interceptor.security.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private CommentStateInterceptor commentStateInterceptor;

    @Autowired
    private CommentRoleInterceptor commentRoleInterceptor;

    @Autowired
    private IpInterceptor addrInterceptor;

    @Autowired
    private PvInterceptor pvInterceptor;

    @Autowired
    private UvInterceptor uvInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(addrInterceptor).addPathPatterns("/**");
        registry.addInterceptor(uvInterceptor).addPathPatterns("/**");
        registry.addInterceptor(pvInterceptor).addPathPatterns("/article/**").excludePathPatterns("/article/ranklist")
                                            .addPathPatterns("/user/**").excludePathPatterns("/user/check")
                                            .addPathPatterns("/source/**");


        registry.addInterceptor(commentStateInterceptor).addPathPatterns("/comment").addPathPatterns("/reply");
        registry.addInterceptor(commentRoleInterceptor).addPathPatterns("/comment").addPathPatterns("/reply");

        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/detail")
                                                 .addPathPatterns("/like")
                                                 .addPathPatterns("/upload/**")
                                                 .addPathPatterns("/article/post")
                                                 .addPathPatterns("/source/detail/*");
    }
}
