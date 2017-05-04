package com.zk.google.guava.caches;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zk_chs on 5/3/17.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/CachesExplained
 */
public class CacheLoaderUsage {

    private static final Logger log = LogManager.getLogger();
    private static LoadingCache<String, String> caches;

    public static void main(String[] args) throws ExecutionException {
        getCacheWithCheckedException();
        getCacheWithRuntimeException();
    }

    private static void getCacheWithCheckedException (){
        createCacheWithCheckedException();
        String key = "key";
        try {
            log.info("get value from cache, key is {}, value -> {}", key, caches.get(key));
        } catch (ExecutionException e) {
            log.error("meet the 1/2 rate to throw an exception");
        }
    }

    private static void createCacheWithCheckedException (){
        caches = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return createExpensiveValueWithCheckedException(key);
                    }
                });
    }

    private static String createExpensiveValueWithCheckedException (String key) throws Exception {
        long now = System.currentTimeMillis();
        if (now % 2 == 0){
            throw new Exception("50% rate to throw an exception");
        }
        return "this is a big value of key " + key;
    }

    // If you have defined a CacheLoader that does not declare any checked exceptions
    // then you can perform cache lookups using getUnchecked(K);
    private static void getCacheWithRuntimeException (){
        createCacheWithRuntimeException();
        String key = "key";
        log.info("get value from cache, key is {}, value -> {}", key, caches.getUnchecked(key));
    }

    private static void createCacheWithRuntimeException (){
        caches = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return createExceptionValueWithRuntimeException(key);
                    }
                });
    }

    private static String createExceptionValueWithRuntimeException (String key){
        long now = System.currentTimeMillis();
        if (now % 2 == 0){
            throw new RuntimeException("50% rate to throw an exception");
        }
        return "this is a big value of key " + key;
    }

}
