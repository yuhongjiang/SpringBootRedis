package net.rdd.config;

import net.rdd.listener.RddMessageListener;
import net.rdd.mq.MessageReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * Created by 东东 on 2018/11/18.
 */
//@Configuration
public class RedisMqConfig {

    /**
     * redis消息监听器容器
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter,RddMessageListener rddMessageListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了test的通道
        //可以多个
        container.addMessageListener(listenerAdapter, new PatternTopic("test"));
//        container.addMessageListener(listenerAdapter, new PatternTopic("test"));
        container.addMessageListener(rddMessageListener, new PatternTopic("test"));

        return container;
    }

    /**
     * 消息监听器适配器
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
        //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
        //这里可以指定方法,也可以不指定方法,重写onMessage方法,如果指定了方法,则按指定的方法执行,如果没有则或执行onMessage方法.
        //这里跟监听redis key过期那差不多,参见我博客里面有
//        return new MessageListenerAdapter(receiver,"receiveMessage");

        return new MessageListenerAdapter(receiver);
    }

    @Bean
    MessageListenerAdapter rddlistenerAdapter(RddMessageListener receiver) {
        return new MessageListenerAdapter(receiver, "onMessage");
    }


}
