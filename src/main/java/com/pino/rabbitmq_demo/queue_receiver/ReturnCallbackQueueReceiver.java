package com.pino.rabbitmq_demo.queue_receiver;

import com.pino.rabbitmq_demo.constant.ExchangeConst;
import com.pino.rabbitmq_demo.constant.QueueConst;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ReturnCallbackQueueReceiver {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            name = QueueConst.RETURN_CALLBACK_QUEUE,
                            durable = "true",
                            autoDelete = "true"
                    ),
                    exchange = @Exchange(
                            name = ExchangeConst.RETURN_CALLBACK_EXCHANGE,
                            type = ExchangeTypes.DIRECT
                    ),
                    key = "black"
            )
    )
    public void receive(Message msg) {
        System.out.println(OffsetDateTime.now() + " -- instance receive message from 【" + QueueConst.RETURN_CALLBACK_QUEUE + "】: " + msg);
    }
}
