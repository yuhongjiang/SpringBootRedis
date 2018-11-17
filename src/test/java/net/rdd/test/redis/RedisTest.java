package net.rdd.test.redis;

import net.rdd.util.SpringContextUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnectionFactory;
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

    @Autowired
    @Qualifier(value = "rddRedisTemplate")
    private RedisTemplate rddRedisTemplate;

    @Test
    public void test02() {
        taskRedisTemplate.opsForValue().set("dssd","fdsfds");

        rddRedisTemplate.opsForValue().set("322","ewew");
    }

//    @Qualifier("taskConnectionFactory")
    @Autowired
    private RedisConnectionFactory taskConnectionFactory;

    @Test
    public void test03() {
        System.out.println(333);
        Object bean = SpringContextUtil.getBean("taskConnectionFactory");
        Object bean2 = SpringContextUtil.getBean("rddConnectionFactory");
        System.out.println(222);
    }


}
