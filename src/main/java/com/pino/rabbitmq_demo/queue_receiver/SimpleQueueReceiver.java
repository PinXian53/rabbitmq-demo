package com.pino.rabbitmq_demo.queue_receiver;

import com.pino.rabbitmq_demo.constant.QueueConst;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class SimpleQueueReceiver {

    @RabbitListener(queues = {QueueConst.SIMPLE_QUEUE})
    public void receive(String msg) {
        System.out.println(OffsetDateTime.now() + " -- receive message from 【" + QueueConst.SIMPLE_QUEUE + "】: " + msg);
    }

}
