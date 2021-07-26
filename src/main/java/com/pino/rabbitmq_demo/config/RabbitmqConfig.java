package com.pino.rabbitmq_demo.config;

import com.pino.rabbitmq_demo.constant.ExchangeConst;
import com.pino.rabbitmq_demo.constant.QueueConst;
import com.pino.rabbitmq_demo.constant.RoutingKey;
import com.pino.rabbitmq_demo.queue_receiver.RpcQueueReceiver;
import com.pino.rabbitmq_demo.queue_receiver.WorkQueueReceiver;
import org.springframework.amqp.core.*;
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


    //********************
    // Routing
    //********************
    @Bean
    public DirectExchange routing() {
        return new DirectExchange(ExchangeConst.ROUTING_EXCHANGE);
    }

    @Bean
    public Queue routingQueue1() {
        return new Queue(QueueConst.ROUTING_QUEUE1, true, false, true);
    }

    @Bean
    public Queue routingQueue2() {
        return new Queue(QueueConst.ROUTING_QUEUE2, true, false, true);
    }

    @Bean
    public Binding binding1a(DirectExchange routing, Queue routingQueue1) {
        return BindingBuilder.bind(routingQueue1).to(routing).with(RoutingKey.black);
    }

    @Bean
    public Binding binding1b(DirectExchange routing, Queue routingQueue1) {
        return BindingBuilder.bind(routingQueue1).to(routing).with(RoutingKey.orange);
    }

    @Bean
    public Binding binding2a(DirectExchange routing, Queue routingQueue2) {
        return BindingBuilder.bind(routingQueue2).to(routing).with(RoutingKey.black);
    }

    @Bean
    public Binding binding2b(DirectExchange routing, Queue routingQueue2) {
        return BindingBuilder.bind(routingQueue2).to(routing).with(RoutingKey.green);
    }


    //********************
    // Routing
    //********************
    @Bean
    public TopicExchange topic() {
        return new TopicExchange(ExchangeConst.TOPIC_EXCHANGE);
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue(QueueConst.TOPIC_QUEUE1, true, false, true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(QueueConst.TOPIC_QUEUE2, true, false, true);
    }

    @Bean
    public Binding bindingTopic1a(TopicExchange topic, Queue topicQueue1) {
        return BindingBuilder.bind(topicQueue1).to(topic).with("*.orange.*");
    }

    @Bean
    public Binding bindingTopic2a(TopicExchange topic, Queue topicQueue2) {
        return BindingBuilder.bind(topicQueue2).to(topic).with("*.*.rabbit");
    }

    @Bean
    public Binding bindingTopic2b(TopicExchange topic, Queue topicQueue2) {
        return BindingBuilder.bind(topicQueue2).to(topic).with("lazy.#");
    }


    //********************
    // Remote procedure call (RPC)
    //********************
    @Bean
    public DirectExchange rpc() {
        return new DirectExchange(ExchangeConst.RPC_EXCHANGE);
    }

    @Bean
    public Queue rpcQueue() {
        return new Queue(QueueConst.RPC_QUEUE, true, false, true);
    }

    @Bean
    public Binding binding(DirectExchange rpc, Queue rpcQueue) {
        return BindingBuilder.bind(rpcQueue)
                .to(rpc)
                .with("rpc");
    }

    @Bean
    public RpcQueueReceiver rpcQueueReceiver() {
        return new RpcQueueReceiver();
    }
}