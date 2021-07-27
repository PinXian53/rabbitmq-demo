package com.pino.rabbitmq_demo.controller;

import com.pino.rabbitmq_demo.constant.ExchangeConst;
import com.pino.rabbitmq_demo.constant.RoutingKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/callBack")
@Api(tags = "CallBack Test")
public class CallBackController {
    private final RabbitTemplate rabbitTemplate;

    public CallBackController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Confirm 確認訊息
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        System.out.println("CCB - CorrelationData: " + correlationData);
        System.out.println("CCB - ack: " + ack);
        if (ack) {
            System.out.println("CCB - 傳送到 exchange 成功");
        } else {
            System.err.println("CCB - 傳送到 exchange 失敗");
        }
    };

    /**
     * return 返回訊息
     */
    final RabbitTemplate.ReturnsCallback returnCallback = (returned) ->
    {
        System.out.println("RCB - message: " + returned.getMessage());
        System.out.println("RCB - replyCode: " + returned.getReplyCode());
        System.out.println("RCB - replyText: " + returned.getReplyText());
        System.out.println("RCB - exchange: " + returned.getExchange());
        System.out.println("RCB - routingKey: " + returned.getRoutingKey());
    };

    @ApiOperation(value = "測試 ConfirmCallback")
    @PostMapping("/confirmCallback")
    public ResponseEntity<String> testConfirmCallback() {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("none", "none", "", correlationData);
        return ResponseEntity.accepted().body("訊息傳送成功");
    }

    @ApiOperation(value = "測試 ReturnCallback")
    @PostMapping("/returnCallback/fail")
    public ResponseEntity<String> testReturnCallback() {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(ExchangeConst.RETURN_CALLBACK_EXCHANGE, "none", "", correlationData);
        return ResponseEntity.accepted().body("訊息傳送成功");
    }

    @ApiOperation(value = "測試 ReturnCallback")
    @PostMapping("/returnCallback/success")
    public ResponseEntity<String> testReturnCallback2() {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(ExchangeConst.RETURN_CALLBACK_EXCHANGE, RoutingKey.black.name(), "", correlationData);
        return ResponseEntity.accepted().body("訊息傳送成功");
    }
}
