package com.xusm.forum.service;

import java.util.Map;

public interface AuthService {
    Map<String, Object> queryUserInfo(Long id);
}
