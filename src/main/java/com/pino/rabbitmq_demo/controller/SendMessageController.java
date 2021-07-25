package com.pino.rabbitmq_demo.controller;

import com.pino.rabbitmq_demo.constant.QueueConst;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    private final RabbitTemplate rabbitTemplate;

    public SendMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/putMessage/{msg}")
    public ResponseEntity<String> putMessage(@PathVariable String msg) {
        rabbitTemplate.convertAndSend(QueueConst.TEST_QUEUE, msg);
        return ResponseEntity.accepted().body("message 接收成功");
    }
}
