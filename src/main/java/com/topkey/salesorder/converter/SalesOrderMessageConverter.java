package com.topkey.salesorder.converter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topkey.salesorder.dto.changeorder.SalesOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SalesOrderMessageConverter implements MessageConverter {

	private final Gson gson = new Gson();

	@Override
	public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
		log.info("object--------------------->" + object.toString());

		return new Message(gson.toJson(object).getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public ArrayList<SalesOrder> fromMessage(Message message) throws MessageConversionException {
		
		String json = new String(message.getBody()); 
		log.info("Converter fromMessage -------->"+json);
		ArrayList<SalesOrder> list = gson.fromJson(json, new TypeToken<SalesOrder>(){}.getType());
//		// 根据消息类型进行反序列化
		return list;
	}
}
