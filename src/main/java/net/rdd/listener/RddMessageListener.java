package net.rdd.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RddMessageListener extends MessageListenerAdapter {
//public class RedisMessageListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(getClass());

	private final RedisSerializer<String> stringSerializer = new StringRedisSerializer();
	
	@Override
	//key过期会执行这个方法
	public void onMessage(Message message, byte[] pattern) {

		String key = stringSerializer.deserialize(message.getBody());

		System.out.println("RddMessageListener"+key);

	}


}
