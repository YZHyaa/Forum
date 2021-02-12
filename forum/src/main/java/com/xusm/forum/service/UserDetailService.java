package com.xusm.forum.service;

import com.xusm.forum.entity.UserDetail;

import java.util.Map;

public interface UserDetailService {
    UserDetail queryUserDetail();

    Boolean updateUserDetail(UserDetail userDetail);

    String selectUsernameById(Long id);

    Map<String,Object> queryUserInfo(Long id);

}
