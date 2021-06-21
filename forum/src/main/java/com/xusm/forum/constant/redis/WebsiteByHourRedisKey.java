package com.xusm.forum.constant.redis;

public abstract class WebsiteByHourRedisKey {
    // 每小时 pv 的 redis key
    public static final String PREFIX_KEY_PV = "webByHour:pv";
    // 每小时 uv 的 redis key
    public static final String PREFIX_KEY_UV = "webByHour:uv";
    // 每小时 ip 的 redis key
    public static final String PREFIX_KEY_IP = "webByHour:ip";
}
