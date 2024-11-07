package com.topkey.salesorder.dto;

import java.util.List;

import lombok.Data;


@Data
public class SalesOrderResponse extends StandardResponse {
	private List<SalesOrderDetail> data; // 將 data 改為 List<SalesOrder> 類型
}