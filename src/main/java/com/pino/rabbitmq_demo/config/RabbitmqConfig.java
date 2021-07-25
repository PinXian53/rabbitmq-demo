package com.pino.rabbitmq_demo.config;

import com.pino.rabbitmq_demo.constant.QueueConst;
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

}