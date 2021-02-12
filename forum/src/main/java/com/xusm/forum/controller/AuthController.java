package com.xusm.forum.controller;

import com.xusm.forum.config.prop.JwtProperties;
import com.xusm.forum.entity.UserInfo;
import com.xusm.forum.service.AuthService;
import com.xusm.forum.utils.JwtUtils;
import com.xusm.forum.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class AuthController {

    @Resource
    private JwtProperties jwtProperties;

    @Autowired
    private AuthService authService;


    @GetMapping("/user/verify")
    public ResponseEntity<Map<String,Object>> Verify(
            HttpServletResponse response,
            HttpServletRequest request,
            @CookieValue("XUSM_TOKEN") String token // 拿到指定 cookie
        ){
        UserInfo userInfo = new UserInfo();
        try {
            // 获取 cookie 中的信息
            userInfo = JwtUtils.getInfoFromToken(token,this.jwtProperties.getPublicKey());
            // 如果没有，则说明客户端根本没有该 token
            if(userInfo == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            // 重新生成 token
            token = JwtUtils.generateToken(userInfo,jwtProperties.getPrivateKey(),jwtProperties.getExpire()*60);
            // 重新发送 cookie
            CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getCookieMaxAge()*60);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String,Object> map = this.authService.queryUserInfo(userInfo.getId());

        return ResponseEntity.ok(map);
    }
}
