package com.pino.rabbitmq_demo.config;

import com.pino.rabbitmq_demo.constant.ExchangeConst;
import com.pino.rabbitmq_demo.constant.QueueConst;
import com.pino.rabbitmq_demo.queue_receiver.WorkQueueReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitmqConfig {

    //********************
    // Simple Queue
    // > one queue to one receiver
    //********************
    @Bean
    public Queue simpleQueue() {
        return new Queue(QueueConst.SIMPLE_QUEUE, true, false, true);
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


    //********************
    // Publish/Subscribe
    //********************
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange(ExchangeConst.FANOUT_EXCHANGE);
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue(QueueConst.FANOUT_QUEUE1, true, false, true);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(QueueConst.FANOUT_QUEUE2, true, false, true);
    }

    @Bean
    public Binding binding1(FanoutExchange fanout, Queue fanoutQueue1) {
        return BindingBuilder.bind(fanoutQueue1).to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanout, Queue fanoutQueue2) {
        return BindingBuilder.bind(fanoutQueue2).to(fanout);
    }

}