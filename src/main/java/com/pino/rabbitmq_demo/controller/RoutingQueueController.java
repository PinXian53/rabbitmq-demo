package com.pino.rabbitmq_demo.controller;

import com.pino.rabbitmq_demo.constant.ExchangeConst;
import com.pino.rabbitmq_demo.constant.RoutingKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routingQueue")
@Api(tags = "Routing Queue")
public class RoutingQueueController {
    private final RabbitTemplate rabbitTemplate;

    public RoutingQueueController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @ApiOperation(value = "傳送訊息")
    @PostMapping("/{routingKey}/{msg}")
    public ResponseEntity<String> sendMessage(@PathVariable RoutingKey routingKey, @PathVariable String msg) {
        rabbitTemplate.convertAndSend(ExchangeConst.ROUTING_EXCHANGE, routingKey.name(), msg);
        return ResponseEntity.accepted().body("訊息傳送成功");
    }
}
