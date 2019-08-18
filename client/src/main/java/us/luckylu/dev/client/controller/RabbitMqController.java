package us.luckylu.dev.client.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.luckylu.dev.client.config.RabbitConfig;
import us.luckylu.dev.common.model.dto.rsp.ResponseDto;
import us.luckylu.dev.common.util.ResponseUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lu
 * @date 2019-06-15 9:32
 */
@Slf4j
@RestController
@RequestMapping("mq")
public class RabbitMqController extends BaseController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "send")
    public ResponseDto sendMessage() throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        connectionFactory.setHost("localhost");
        // 创建一个连接
        Connection connection = connectionFactory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 创建一个Exchange
        channel.exchangeDeclare(RabbitConfig.EXCHANGE_NAME, RabbitConfig.EXCHANGE_DIRECT_TYPE);

        try {
            // 将信道设置成confirm模式
            channel.confirmSelect();
            // 发送消息
            for (int i = 0; i < 10; i++) {
                String message = "normal confirm test";
                channel.basicPublish(RabbitConfig.EXCHANGE_NAME, "", null, message.getBytes());
                // 等待发送消息的确认消息
                if (channel.waitForConfirms()) {
                    System.out.println("send message success");
                } else {
                    System.out.println("send message failed");
                    // do something else...
                }
            }
        }catch (InterruptedException  e) {
            e.printStackTrace();
        }
        return ResponseUtil.getSuccessRsp();
    }
}
