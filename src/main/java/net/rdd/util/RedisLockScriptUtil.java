package net.rdd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

/**
 * Created by rdd on 2018/12/18.
 */
@Component
public class RedisLockScriptUtil {

    private Integer acquireTimeout = 10;//资源占有锁的时间 秒s
    private Integer acquireInterval = 1000;//尝试获取锁的时限 ms

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("lockScript")
    private RedisScript<Boolean> lockScript;
    @Autowired
    @Qualifier("unlockScript")
    private RedisScript<Boolean> unlockScript;


    public String tryLock(String lockKey) {
        String lockValue = UUID.randomUUID().toString();
        Long endTime = System.currentTimeMillis() + acquireTimeout;
        while (System.currentTimeMillis() < endTime) {
            Boolean lockResult = (Boolean) redisTemplate.execute(lockScript, Collections.singletonList(lockKey), lockValue, 7);
            if (lockResult) {
                //加锁成功
                return lockValue;
            } else {
                //等待,准备再次获取锁
                try {
                    Thread.sleep(acquireInterval);
                } catch (InterruptedException ex) {
                    continue;
                }
            }
        }
        //获取锁超时,可以直接抛异常

        return "";
    }

    public boolean releaseLock(String lockKey, String lockValue) {
        Boolean releaseResult = (Boolean) redisTemplate.execute(unlockScript, Collections.singletonList(lockKey), lockValue);
        return releaseResult;
    }

}
