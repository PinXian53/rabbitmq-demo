package com.pino.rabbitmq_demo.queue_receiver;

import com.pino.rabbitmq_demo.constant.ExchangeConst;
import com.pino.rabbitmq_demo.constant.QueueConst;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.OffsetDateTime;

@Component
public class ManualAckReceiver {
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            name = QueueConst.MANUAL_ACK_QUEUE,
                            durable = "true",
                            autoDelete = "true"
                    ),
                    exchange = @Exchange(
                            name = ExchangeConst.MANUAL_ACK_EXCHANGE,
                            type = ExchangeTypes.DIRECT
                    ),
                    key = "black"
            )
    )
    public void receive(String msg, Channel channel, Message message) throws IOException {
        try {
            System.out.println(OffsetDateTime.now() + " -- instance receive message from 【" + QueueConst.MANUAL_ACK_QUEUE + "】: " + msg);

            if (msg.length() % 2 == 0) {
                throw new RuntimeException();
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                System.err.println("訊息已重複處理失敗,拒絕再次接收");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒絕訊息
            } else {
                System.err.println("訊息處理失敗，重新加入 Queue");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}
