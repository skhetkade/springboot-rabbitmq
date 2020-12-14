package com.study.rabbitmq.config;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String id;
    private String orderEntity;
    private String orderQty;
    private String orderETA;
}
