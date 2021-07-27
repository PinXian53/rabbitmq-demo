package com.pino.rabbitmq_demo.controller;

import com.pino.rabbitmq_demo.constant.ExchangeConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manualAckQueue")
@Api(tags = "Manual Ack Queue")
public class ManualAckController {

    private final RabbitTemplate rabbitTemplate;

    public ManualAckController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @ApiOperation(value = "傳送訊息")
    @PostMapping("/{msg}")
    public ResponseEntity<String> sendMessage(@PathVariable String msg) {
        rabbitTemplate.convertAndSend(ExchangeConst.MANUAL_ACK_EXCHANGE, "black", msg);
        return ResponseEntity.accepted().body("訊息傳送成功");
    }
}
