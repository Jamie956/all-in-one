package org.example;

import com.alicp.jetcache.anno.Cached;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MethodCacheTest {
    /**
     * 方法缓存，key由@Cached 的name属性 和 key属性的参数值 组成，value由方法的返回值构成
     * 缓存不存在此key-value时，会执行方法，否则不会执行方法，直接从缓存获取返回值
     * key=prefix2-1
     * value=J\x95:\x80\xAC\xED\x00\x05sr\x00#com.alicp.jetcache.CacheValueHolder\x91W\x92\x86\xBD\x9FTm\x02\x00\x03J\x00\x0AaccessTimeJ\x00\x0AexpireTimeL\x00\x05valuet\x00\x12Ljava/lang/Object;xp\x00\x00\x01}Kv\xA7\x0E\x00\x00\x01}Kx{\xCEt\x00\x02a1
     */
    @Cached(name = "prefix2-", key = "#code", expire = 120, timeUnit = TimeUnit.SECONDS)
    public String get(String code) {
        return "a" + code;
    }
}
