package com.xusm.forum.m1;

import com.xusm.forum.ForumApplication;
import com.xusm.forum.dao.UserInfoDao;
import com.xusm.forum.entity.UserInfo;
import com.xusm.forum.service.Impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest(classes = ForumApplication.class)
@RunWith(SpringRunner.class)
public class tmapper {

    @Resource
    private UserInfoDao userInfoDao;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void m1(){
//        userService.register("15389093298","1234","123456");
//        userService.register("15389093294","1234","123456");
//        userService.register("15389093295","1234","123456");
//        userService.register("15389093296","1234","123456");
    }
}
