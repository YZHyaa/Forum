package com.xusm.forum.service;

public interface CodeService {
    Boolean sendCode(String phone);

    Boolean checkCacheCode(String phone,String code);
}
