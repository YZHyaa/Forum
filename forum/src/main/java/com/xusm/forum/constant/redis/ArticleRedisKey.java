package com.xusm.forum.constant.redis;

public abstract class ArticleRedisKey {

    // 缓存文章点击量的key
    public static final String COUNT_PREFIX_KEY = "article:click:";

    // 日点击排行的 key
    public static final String RANK_PREFIX_KEY = "article:rank:";

    // 周点击排行的 key
    public static final String RANK_WEED_PREFIX_KEY = "article:weekrank";

    // 月点击排行的 key
    public static final String RANK_MONTH_PREFIX_KEY = "article:monthrank";

    // TODO 缓存首页文章列表的 key
    public static final String INDEX_PAGE_KEY = "article:page";

}
