package dev.sstol.tasktrackerscheduler.infrastructure.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.sstol.tasktrackerscheduler.features.tasks.TaskQueuesNames.*;

/**
 * @author Sergey Stol
 * 2024-10-12
 */
@Configuration
public class RabbitMQConfig {

   public static final String EXCHANGE_NAME = "TASKS_TRACKER_EXCHANGE";

   @Bean
   public DirectExchange directExchange() {
      return new DirectExchange(EXCHANGE_NAME);
   }

   @Bean
   public Queue taskCreatedQueue() {
      return new Queue(TASK_CREATED_QUEUE_NAME);
   }

   @Bean
   public Queue taskUpdatedQueue() {
      return new Queue(TASK_UPDATED_QUEUE_NAME);
   }

   @Bean
   public Binding bindingTaskCreated(Queue taskCreatedQueue, DirectExchange exchange) {
      return BindingBuilder.bind(taskCreatedQueue).to(exchange).with(TASK_CREATED);
   }

   @Bean
   public Binding bindingTaskUpdated(Queue taskUpdatedQueue, DirectExchange exchange) {
      return BindingBuilder.bind(taskUpdatedQueue).to(exchange).with(TASK_UPDATED);
   }

   @Bean
   public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
      RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
      rabbitTemplate.setMessageConverter(jsonMessageConverter());
      return rabbitTemplate;
   }

   @Bean
   public Jackson2JsonMessageConverter jsonMessageConverter() {
      return new Jackson2JsonMessageConverter();
   }
}
