package com.pino.rabbitmq_demo.message_listener;

import com.pino.rabbitmq_demo.Util.ThreadUtils;
import com.pino.rabbitmq_demo.constant.QueueConst;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.time.OffsetDateTime;

public class WorkQueueReceiver {

    private final int instance;

    public WorkQueueReceiver(int instance) {
        this.instance = instance;
    }

    @RabbitListener(queues = {QueueConst.WORK_QUEUE})
    public void receive(String msg) {
        String msgFormat = "%s -- instance %s receive message from 【%s】: %s";
        System.out.printf((msgFormat) + "%n", OffsetDateTime.now(), instance, QueueConst.WORK_QUEUE, msg);
        ThreadUtils.sleep(5000); // 暫停5秒
    }
}
