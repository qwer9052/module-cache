package com.module.cache.key;

public class CacheKey {
    private CacheKey() {
    }
    public static final int DEFAULT_EXPIRE_SEC = 60;
    public static final int USER_EXPIRE_SEC = 180;
    public static final String USER = "user";
    public static final String POST = "post";

    public static final String POST_KEY_GENERATOR = "postCacheKeyGenerator";

}