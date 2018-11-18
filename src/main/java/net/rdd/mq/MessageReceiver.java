package net.rdd.mq;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

@Component
public class MessageReceiver extends MessageListenerAdapter {


    @Override
    protected void handleListenerException(Throwable ex) {

        System.out.println("MessageReceiver 异常了....");
        super.handleListenerException(ex);
    }

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {

//        throw new NullPointerException("fdsafds");
        System.out.println("收到一条消息onMessage："+message.toString());

    }

    /**接收消息的方法*/
    public void handleMessage(String message){
        System.out.println("收到一条消息handleMessage："+message);
    }

    /**接收消息的方法*/
    public void receiveMessage(String message){
        System.out.println("收到一条消息："+message);
    }

}
