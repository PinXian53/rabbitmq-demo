package com.pino.rabbitmq_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
public class RpcDataDTO implements Serializable {
    Integer delayTime;
    List<Integer> data;
}
