package com.pino.rabbitmq_demo.queue_receiver;

import com.pino.rabbitmq_demo.constant.QueueConst;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class TopicQueueReceiver {
    @RabbitListener(queues = {QueueConst.TOPIC_QUEUE1})
    public void receive1(String msg) {
        System.out.println(OffsetDateTime.now() + " -- instance 1 receive message from 【" + QueueConst.TOPIC_QUEUE1 + "】: " + msg);
    }

    @RabbitListener(queues = {QueueConst.TOPIC_QUEUE2})
    public void receive2(String msg) {
        System.out.println(OffsetDateTime.now() + " -- instance 2 receive message from 【" + QueueConst.TOPIC_QUEUE2 + "】: " + msg);
    }
}
