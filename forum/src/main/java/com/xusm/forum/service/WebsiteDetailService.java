package com.xusm.forum.service;

import java.util.Set;

public interface WebsiteDetailService {

    void insertByHour(String key);

    void insertJsonToDB(Set<String> keys);
}
