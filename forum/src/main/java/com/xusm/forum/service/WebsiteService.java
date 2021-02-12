package com.xusm.forum.service;


import java.util.Set;

public interface WebsiteService {

    void insertVisit(String key);

    Boolean hasRomteIp(String addr);

    void insertRomteIp(String addr);

    void insertAllToDb(Set<String> keys);

}
