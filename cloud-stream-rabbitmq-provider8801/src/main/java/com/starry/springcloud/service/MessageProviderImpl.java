package com.starry.springcloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author starry
 * @version 1.0
 * @date 2022/2/24 11:27
 * @Description
 */
// 声明为输出接口，和配置的exchange进行绑定
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider{

    /**
     * 注入消息发送的channel
     * 必须叫output 且是@Resource注入
     *
     */
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String s = UUID.randomUUID().toString();
        Message<String> message = MessageBuilder.withPayload(s).build();
        // 发送消息
        this.output.send(message);
        return s;
    }
}
