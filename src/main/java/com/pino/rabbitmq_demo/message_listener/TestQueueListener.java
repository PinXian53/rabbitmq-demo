package com.pino.rabbitmq_demo.message_listener;

import com.pino.rabbitmq_demo.constant.QueueConst;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class TestQueueListener {

    @RabbitListener(queues = {QueueConst.TEST_QUEUE})
    public void receive(String msg) {
        System.out.println(OffsetDateTime.now() + " -- receive message from 【" + QueueConst.TEST_QUEUE + "】: " + msg);
    }

}
