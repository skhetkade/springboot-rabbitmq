package com.study.rabbitmq.publisher;

import com.study.rabbitmq.config.OrderDTO;

import com.study.rabbitmq.config.TicketDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/rabbitDemo")
public class PublishMessageToQueue {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired private RabbitTemplate rabbitTemplate;

  @Value("${OrderQueueName}")
  private String orderQueueName;

  @Value("${TicketQueueName}")
  private String ticketQueueName;

  @Value("${ExchangeName}")
  private String exchangeName;

  @Value("${OrderQueueRoutingKey}")
  private String orderQueueRoutingKey;

  @Value("${TicketQueueRoutingKey}")
  private String ticketQueueRoutingKey;

  @GetMapping("/")
  public String ping() {
    return "Pong!!";
  }

  @PostMapping("/order/{orderedBy}")
  public String publishOrder(@RequestBody OrderDTO orderDTO, @PathVariable String orderedBy) {
    logger.info("Received order from {}.", orderedBy);
    orderDTO.setId(UUID.randomUUID().toString());
    rabbitTemplate.convertAndSend(exchangeName, orderQueueRoutingKey, orderDTO);
    logger.info("OrderMessage Published to Rabbitmq ORDER queue");
    return "Order message received";
  }

  @PostMapping("/ticket")
  public String requestTicket(@RequestBody TicketDTO ticketDTO) {
    logger.info("Ticket request received");
    ticketDTO.setTicketId(UUID.randomUUID().toString());
    rabbitTemplate.convertAndSend(exchangeName, ticketQueueRoutingKey, ticketDTO);
    logger.info("Ticket request published to the Ticket Queue");
    return "Ticket request received!!";
  }
}
