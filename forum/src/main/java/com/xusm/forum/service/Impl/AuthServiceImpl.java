package com.xusm.forum.service.Impl;

import com.xusm.forum.dao.UserDetailDao;
import com.xusm.forum.entity.UserDetail;
import com.xusm.forum.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDetailDao userDetailDao;

    @Override
    public Map<String, Object> queryUserInfo(Long id) {
        Map<String,Object> map = new HashMap<>();
        UserDetail userDetail = this.userDetailDao.selectByPrimaryKey(id);
        map.put("username",userDetail.getUsername());
        map.put("userhead",userDetail.getHead());
        return map;
    }
}
