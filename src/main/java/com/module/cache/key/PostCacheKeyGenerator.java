package com.module.cache.key;

import com.module.cache.annotation.CacheParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class PostCacheKeyGenerator implements KeyGenerator {

    Logger logger = LoggerFactory.getLogger(PostCacheKeyGenerator.class);

    @Override
    public Object generate(Object target, Method method, Object... params) {
        logger.info("[Redis Cache logger] Start CacheKeyGenerating...");
        return String.format("%s", arrayToDelimitedString(method, params));
    }

    public String arrayToDelimitedString(Method method, Object... params) {
        final var sb = new StringBuilder();
        //메소드의 어노테이션 정보 가져오기
        //Annotation[] annotations = method.getDeclaredAnnotations();
        Annotation[][] annotations = method.getParameterAnnotations();

        for (int t = 0; t < annotations.length; t++) {
            for (int i = 0; i < annotations[t].length; i++) {
                if(annotations[t][i] instanceof CacheParam){
                    if(params[t] != null) {
                        sb.append(params[t]);
                    }
                }
            }
        }

        return sb.toString();
    }
}
