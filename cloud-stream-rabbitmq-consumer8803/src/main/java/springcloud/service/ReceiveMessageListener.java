package springcloud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @author starry
 * @version 1.0
 * @date 2022/2/27 14:30
 * @Description
 */
// 绑定输入管道
@EnableBinding(Sink.class)
public class ReceiveMessageListener {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 监听管道（收到消息后会调用此方法）
     *
     * Sink.INPUT 就是 ”input“
     * 即 配置文件的
     * bindings:
     *   # 自定义通道名称
     *   input:
     * @param message
     */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者2号，=======》收到消息：" + message.getPayload() + "\t port:" + serverPort);
    }
}
