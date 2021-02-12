package com.xusm.forum.service;


import com.xusm.forum.vo.UserRegisterRequest;

public interface UserService {

    Boolean checkUserPhone(String phone);

    Boolean register(UserRegisterRequest request);

    String generateToken(String phone);

    String accredit(String phone, String password);

    void reset(String phone, String password);

}
