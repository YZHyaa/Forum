package com.xusm.forum.controller;

import com.xusm.forum.constant.WebsiteRedisKey;
import com.xusm.forum.config.prop.JwtProperties;
import com.xusm.forum.service.CodeService;
import com.xusm.forum.service.UserService;
import com.xusm.forum.service.WebsiteService;
import com.xusm.forum.utils.CookieUtils;
import com.xusm.forum.vo.UserRegisterRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CodeService codeService;

    @Resource
    private JwtProperties jwtProperties;

    @Autowired
    private WebsiteService websiteService;


    @GetMapping("/check")
    public ResponseEntity<Void> checkUserPhone(@RequestParam("phone") String phone){
        if(!this.userService.checkUserPhone(phone)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRegisterRequest request){
        if(this.userService.register(request)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/login")
    public ResponseEntity<Void> smsLogin(
            @RequestParam("phone") String phone,
            @RequestParam("code") String code,
            HttpServletResponse response,
            HttpServletRequest request){
        if(this.userService.checkUserPhone(phone)){
            return ResponseEntity.notFound().build();
        }else if(!this.codeService.checkCacheCode(phone,code)){
            return ResponseEntity.badRequest().build();
        }

        this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_LOGIN);

        String token = userService.generateToken(phone);
        CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token, jwtProperties.getCookieMaxAge()*60);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> pwdLogin(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        String token = this.userService.accredit(phone,password);
        if(StringUtils.isBlank(token)){
            return ResponseEntity.badRequest().build();
        }
        CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getCookieMaxAge()*60);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> reset(@RequestParam("phone")String phone,@RequestParam("password")String password){
        this.userService.reset(phone,password);
        return ResponseEntity.ok().build();
    }
}
