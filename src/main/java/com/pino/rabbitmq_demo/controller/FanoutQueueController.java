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
@RequestMapping("/fanoutQueue")
@Api(tags = "Fanout Queue")
public class FanoutQueueController {
    private final RabbitTemplate rabbitTemplate;

    public FanoutQueueController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @ApiOperation(value = "傳送訊息")
    @PostMapping("/{msg}")
    public ResponseEntity<String> sendMessage(@PathVariable String msg) {
        rabbitTemplate.convertAndSend(ExchangeConst.FANOUT_EXCHANGE, "", msg);
        return ResponseEntity.accepted().body("訊息傳送成功");
    }
}
