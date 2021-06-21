package com.xusm.forum.constant.redis;

public abstract class WebsiteRedisKey {
    // 每天注册模块的 redis key
    public static final String PREFIX_KEY_REGISTER= "website:register";
    // 每天登录模块的 redis key
    public static final String PREFIX_KEY_LOGIN= "website:login";
    // 每天用户信息的 redis key
    public static final String PREFIX_KEY_INFO= "website:info";
    // 每天数模论文的 redis key
    public static final String PREFIX_KEY_SM= "website:sm";
    // 每天技术论文 的 redis key
    public static final String PREFIX_KEY_JS= "website:js";
    // 每天资源下载的 redis key
    public static final String PREFIX_KEY_SOURCE = "website:source";
    // 每天 pv 的 redis key
    public static final String PREFIX_KEY_PV= "website:pv";
    // 每天 uv 的 redis key
    public static final String PREFIX_KEY_UV="website:uv";
    // 每天 iv 的 redis key
    public static final String PREFIX_KEY_IPS = "website:ips";

}
