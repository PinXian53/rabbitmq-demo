package com.pino.rabbitmq_demo.queue_receiver;

import com.pino.rabbitmq_demo.util.ThreadUtils;
import com.pino.rabbitmq_demo.constant.QueueConst;
import com.pino.rabbitmq_demo.model.RpcDataDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.time.OffsetDateTime;
import java.util.List;

public class RpcQueueReceiver {
    @RabbitListener(queues = {QueueConst.RPC_QUEUE})
    public int receive(RpcDataDTO dto) {
        System.out.println(OffsetDateTime.now() + " -- instance receive message from 【" + QueueConst.RPC_QUEUE + "】: " + dto.getData());
        int result = sum(dto.getData());
        ThreadUtils.sleep(dto.getDelayTime() * 1000);
        System.out.println(OffsetDateTime.now() + " -- instance return result : " + result);
        return result;
    }

    private int sum(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }
}
