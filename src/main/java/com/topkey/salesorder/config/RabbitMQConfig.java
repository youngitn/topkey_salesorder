package com.topkey.salesorder.config;

import java.util.UUID;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.topkey.salesorder.converter.GsonSalesOrderMessageConverter;
import brave.Tracing;
import brave.spring.rabbit.SpringRabbitTracing;

@Configuration
public class RabbitMQConfig {

	@Bean
	public MessageConverter gsonSalesOrderMessageConverter() {
		return new GsonSalesOrderMessageConverter();
	}
	
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, SpringRabbitTracing tracing) {
       // RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
       final RabbitTemplate rabbitTemplate = tracing.newRabbitTemplate(connectionFactory);
       //RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
       //rabbitTemplate.setMessageConverter(gsonExpenseListMessageConverter());
       return rabbitTemplate;
   }

   @Bean
    SpringRabbitTracing springRabbitTracing(Tracing tracing) {
       return SpringRabbitTracing.newBuilder(tracing).build();
   }

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
			SpringRabbitTracing springRabbitTracing) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(gsonSalesOrderMessageConverter());
		factory.setConsumerTagStrategy(queue -> "travel-expenses-consumer-" + UUID.randomUUID().toString());
        // 啟用追蹤功能，讓消費者可以從消息的標頭中提取追蹤信息
        springRabbitTracing.decorateSimpleRabbitListenerContainerFactory(factory);
		return factory;
	}

}
