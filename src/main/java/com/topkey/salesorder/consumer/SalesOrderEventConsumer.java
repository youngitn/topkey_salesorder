package com.topkey.salesorder.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.topkey.salesorder.dto.SalesOrderDetail;
import com.topkey.salesorder.dto.StandardResponse;
import com.topkey.salesorder.dto.changeorder.SalesOrder;
import com.topkey.salesorder.service.SalesOrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SalesOrderEventConsumer {

	@Autowired
	private SalesOrderService salesOrderService;

	@RabbitListener(queues = "sales-order-queue", containerFactory = "rabbitListenerContainerFactory")
	@SendTo("callbackRoutingKey") // 這個que和回傳生產者無關,有需要的話可以監聽這個que做其他處理
	public StandardResponse handleMessage(ArrayList<SalesOrder> message, Channel channel, Message msg)
			throws IOException {

		StandardResponse res = new StandardResponse();

		try {

			String fnukid = message.get(0).getErpNo();

			List<SalesOrderDetail> orderDetails = salesOrderService.getF4211RecordsByUkid(fnukid);
			
			res.setCode("OK");
			res.setMessage("save sucess,conunt:"+orderDetails.size());
			res.setData(orderDetails);

		} catch (DataIntegrityViolationException e) {
			// 处理失败
			res.setCode("ERROR");
			res.setMessage(e.getMessage());
			channel.basicReject(msg.getMessageProperties().getDeliveryTag(), false);
			log.error(e.getMessage());
		} catch (ListenerExecutionFailedException e) {
			res.setCode("ListenerExecutionFailedException");
			res.setMessage(e.getMessage());
			channel.basicReject(msg.getMessageProperties().getDeliveryTag(), false);
			log.error(e.getMessage());
		} catch (Exception e) {
			res.setCode("Exception");
			res.setMessage(e.getMessage());
			channel.basicReject(msg.getMessageProperties().getDeliveryTag(), false);
			log.error(e.getMessage());
		}

		// 直接返回结果
		return res;

	}
}
