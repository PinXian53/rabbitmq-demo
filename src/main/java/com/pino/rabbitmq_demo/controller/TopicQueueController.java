package com.pino.rabbitmq_demo.controller;

import com.pino.rabbitmq_demo.constant.ExchangeConst;
import com.pino.rabbitmq_demo.constant.RoutingKeyExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicQueue")
@Api(tags = "Topic Queue")
public class TopicQueueController {
    private final RabbitTemplate rabbitTemplate;

    public TopicQueueController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @ApiOperation(value = "傳送訊息")
    @PostMapping("/{routingKey}/{msg}")
    public ResponseEntity<String> sendMessage(@PathVariable RoutingKeyExample routingKey, @PathVariable String msg) {
        rabbitTemplate.convertAndSend(ExchangeConst.TOPIC_EXCHANGE, routingKey.getVal(), msg);
        return ResponseEntity.accepted().body("訊息傳送成功");
    }
}
