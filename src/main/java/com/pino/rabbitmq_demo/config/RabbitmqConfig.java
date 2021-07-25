package com.pino.rabbitmq_demo.config;

import com.pino.rabbitmq_demo.constant.QueueConst;
import com.pino.rabbitmq_demo.message_listener.WorkQueueReceiver;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitmqConfig {

    @Bean
    public Queue testQueue() {
        return new Queue(QueueConst.TEST_QUEUE, true, false, true);
    }


    //********************
    // Work Queue
    // > one queue to many receiver
    //********************
    @Bean
    public Queue workQueue() {
        return new Queue(QueueConst.WORK_QUEUE, true, false, true);
    }

    @Bean
    public WorkQueueReceiver workQueueReceiver1() {
        return new WorkQueueReceiver(1);
    }

    @Bean
    public WorkQueueReceiver workQueueReceiver2() {
        return new WorkQueueReceiver(2);
    }

}