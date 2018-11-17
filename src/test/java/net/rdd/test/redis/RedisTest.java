package net.rdd.test.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by rdd on 2018/11/16.
 */
public class RedisTest extends MainTest {
//    @Qualifier(value = "rddRedisTemplate")
//    private StringRedisTemplate rddRedisTemplate;

    @Autowired
    @Qualifier(value = "taskRedisTemplate")
    private RedisTemplate taskRedisTemplate;


    @Test
    public void test02() {
        HashOperations hashOperations = taskRedisTemplate.opsForHash();
        hashOperations.put("2", "3", "ew");
    }


}
