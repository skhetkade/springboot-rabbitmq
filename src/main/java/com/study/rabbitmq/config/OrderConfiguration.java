package com.study.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfiguration {

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

  @Bean
  public Queue orderQueue() {
    return new Queue(orderQueueName);
  }

  @Bean
  public Queue ticketQueue() {
    return new Queue(ticketQueueName);
  }

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(exchangeName);
  }

  @Bean
  public Binding orderBinding(Queue orderQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(orderQueue).to(topicExchange).with(orderQueueRoutingKey);
  }

  @Bean
  public Binding ticketBinding(Queue ticketQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(ticketQueue).to(topicExchange).with(ticketQueueRoutingKey);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate template(ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter());
    return rabbitTemplate;
  }
}
