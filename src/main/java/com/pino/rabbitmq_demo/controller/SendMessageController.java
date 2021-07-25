package com.pino.rabbitmq_demo.controller;

import com.pino.rabbitmq_demo.constant.QueueConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testQueue")
@Api(tags = "測試用")
public class SendMessageController {

    private final RabbitTemplate rabbitTemplate;

    public SendMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @ApiOperation(value = "接收訊息")
    @PostMapping("/{msg}")
    public ResponseEntity<String> sendMessage(@PathVariable String msg) {
        rabbitTemplate.convertAndSend(QueueConst.TEST_QUEUE, msg);
        return ResponseEntity.accepted().body("message 接收成功");
    }
}
