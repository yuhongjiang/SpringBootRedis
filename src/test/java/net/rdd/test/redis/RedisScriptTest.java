package net.rdd.test.redis;

import com.google.common.base.Strings;
import net.rdd.util.RedisLockScriptUtil;
import net.rdd.util.SpringContextUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import javax.swing.*;

/**
 * Created by rdd on 2018/12/18.
 */
public class RedisScriptTest extends MainTest {

    @Autowired
    @Qualifier(value = "taskRedisTemplate")
    private RedisTemplate taskRedisTemplate;

    @Autowired
    @Qualifier(value = "rddRedisTemplate")
    private RedisTemplate rddRedisTemplate;

    @Autowired
    @Qualifier(value = "lockScript")
    private RedisScript lockScript;

    @Autowired
    @Qualifier(value = "unlockScript")
    private RedisScript unlockScript;

    @Test
    public void test01() {

        String sss = SpringContextUtil.getBean(RedisLockScriptUtil.class).tryLock("aaaa");

        if (Strings.isNullOrEmpty(sss)) {
            //未获取到锁,且超时
//            throw new Exception("活动太火爆请稍后再试");
        }
        try {
            //todo doSomething
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean dd = SpringContextUtil.getBean(RedisLockScriptUtil.class).releaseLock("aaaa",sss);
        if (dd) {
           //释放锁成功
        }
        //释放锁失败

    }

}
