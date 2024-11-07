package com.topkey.salesorder.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQHealthIndicator implements HealthIndicator {

	private final RabbitTemplate rabbitTemplate;

	public RabbitMQHealthIndicator(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public Health health() {
		try {
			rabbitTemplate.execute(channel -> {
				channel.queueDeclarePassive("travel-expense-queue");
				return null;
			});
			return Health.up().withDetail("message", "RabbitMQ is connected").build();
		} catch (Exception e) {
			return Health.down().withDetail("message", "RabbitMQ is not connected").withException(e).build();
		}
	}
}