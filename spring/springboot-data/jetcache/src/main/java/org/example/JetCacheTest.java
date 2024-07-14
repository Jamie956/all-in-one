package org.example;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import io.lettuce.core.api.sync.RedisStringCommands;
import io.lettuce.core.cluster.api.sync.RedisClusterCommands;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JetCacheTest {
    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MethodCacheTest methodCacheTest;

    /**
     * 必须 Serializable
     */
    @Data
    static class User implements Serializable {
        private String userName;
    }

    /**
     * 远程缓存
     */
    @CreateCache(name = "prefix-", expire = 120, cacheType = CacheType.REMOTE)
    private Cache<String, String> userNameCache;

    /**
     * User对象的二级缓存(本地+远程)
     */
    @CreateCache(name = "prefix-", localExpire = 60, localLimit = 100, expire = 120, cacheType = CacheType.BOTH)
    private Cache<String, User> userCache;

    @CreateCache(cacheType = CacheType.REMOTE)
    private Cache<String, Integer> numCache;

    @Test
    public void getUserInfo() {
        userNameCache.put("123", "userName");
        System.out.println("userNameCache : " + userNameCache.get("123"));

        User user = new User();
        user.setUserName("tim");
        userCache.put("1234", user);
        System.out.println("userCache: " + userCache.get("1234").getUserName());
    }

    @Test
    public void methodCache() {
        String s = methodCacheTest.get("1");
        System.out.println("s="+s);
    }

    @Test
    public void numCacheTest() {
        //自增等操作，jetcache暂时没做实现 可以使用unwrap 包装后使用
        RedisStringCommands<byte[], byte[]> unwrap = numCache.unwrap(RedisClusterCommands.class);
        Long incr = unwrap.incr("key1".getBytes());
        System.out.println(incr);
    }

    /**
     * 模糊匹配多个k-v
     */
    @Test
    public void multiGet() {
        userNameCache.put("1", "v1");
        userNameCache.put("2", "v2");
        userNameCache.put("3", "v3");
        userNameCache.put("4", "v4");
        userNameCache.put("5", "v5");
        userNameCache.put("6", "v6");
        userNameCache.put("7", "v7");

        String s = userNameCache.get("1");

        Set<String> keys = stringRedisTemplate.keys("prefix-*");
    }
}

