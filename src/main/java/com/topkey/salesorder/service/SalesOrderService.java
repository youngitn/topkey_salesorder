package com.topkey.salesorder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topkey.salesorder.dto.SalesOrderDetail;
import com.topkey.salesorder.repo.SalesOrderRepository;

@Service
public class SalesOrderService {

	@Autowired
	private SalesOrderRepository salesChangeOrderRepository;

	public List<SalesOrderDetail> getF4211RecordsByUkid(String fnukid) {
		return salesChangeOrderRepository.findByUkid(fnukid);
	}
}