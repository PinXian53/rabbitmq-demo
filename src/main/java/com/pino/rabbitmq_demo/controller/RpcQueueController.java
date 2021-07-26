package com.pino.rabbitmq_demo.controller;

import com.pino.rabbitmq_demo.constant.ExchangeConst;
import com.pino.rabbitmq_demo.model.RpcDataDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rpcQueue")
@Api(tags = "RPC Queue")
public class RpcQueueController {
    private final RabbitTemplate rabbitTemplate;

    public RpcQueueController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @ApiOperation(value = "數字加總")
    @PostMapping("/sum/{delayTime}")
    public ResponseEntity<String> sendMessage(@PathVariable Integer delayTime, @RequestBody List<Integer> data) {
        System.out.println("傳送資料，延遲：" + delayTime + "秒，資料：" + data);
        Integer response = (Integer) rabbitTemplate.convertSendAndReceive(ExchangeConst.RPC_EXCHANGE, "rpc", new RpcDataDTO(delayTime, data));
        System.out.println("訊息傳送成功，並接收到回傳值：" + response);
        return ResponseEntity.accepted().body("訊息傳送成功，接收到回傳值：" + response);
    }
}
