package com.module.cache.key;

import com.module.cache.annotation.CacheParam;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class PostCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
//        System.out.println(Arrays.toString(method.getParameterTypes()));
//        System.out.println(Arrays.toString(method.getAnnotationsByType(CacheParam.class)));
//        System.out.println(Arrays.deepToString(method.getParameterAnnotations()));
//        System.out.println(Arrays.toString(method.getAnnotatedParameterTypes()));
//        System.out.println(method.getAnnotation(CacheParam.class));
        return String.format("%s", arrayToDelimitedString(method, params));
    }

    public String arrayToDelimitedString(Method method, Object... params) {
        System.out.println("arrayToDelimitedString : ");
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

        System.out.println("sb.toString() : " + sb.toString());
        return sb.toString();
    }
}
