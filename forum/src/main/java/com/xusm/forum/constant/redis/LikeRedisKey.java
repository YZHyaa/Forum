package com.xusm.forum.constant.redis;

public abstract class LikeRedisKey {
    // 文章点赞的 redis key
    public static final String PREFIX_KEY_ARTICLE = "like:article:";
    // 评论点赞的 redis key
    public static final String PREFIX_KEY_COMMENT = "like:comment:";
    // 回复点赞的 redis key
    public static final String PREFIX_KEY_REPLY = "like:reply:";
}
