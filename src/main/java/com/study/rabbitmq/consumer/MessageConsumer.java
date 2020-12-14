package com.study.rabbitmq.consumer;

import com.study.rabbitmq.config.OrderDTO;
import com.study.rabbitmq.config.TicketDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @RabbitListener(queues = "${OrderQueueName}")
  public void consumeOrders(OrderDTO orderDTO) {
    logger.info("Order Received from queue \n {}", orderDTO);
  }

  @RabbitListener(queues = "${TicketQueueName}" )
  public void consumeTickets(TicketDTO ticketDTO) {
    logger.info("Ticket request received from queue \n {}", ticketDTO);
  }
}
