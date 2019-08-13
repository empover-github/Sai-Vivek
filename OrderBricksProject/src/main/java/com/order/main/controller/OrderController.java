package com.order.main.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.order.main.model.Order;

/**
 * Controller Handles requests for the Orders.
 */
@Controller
public class OrderController {
	
	//Repository to store  Orders
	Map<String, Order> orderData = new HashMap<String, Order>();
	private AtomicLong nextId = new AtomicLong(1);
	
	@RequestMapping(value = OrderURIConstants.CREATE_ORD, method = RequestMethod.POST)
	public @ResponseBody Order createOrder(@RequestBody Order ord) {
		ord.setCreatedDate(new Date());
		ord.setOrderRefNo(String.valueOf(nextId.getAndIncrement()));
		ord.setStatus("Pending");
		orderData.put(ord.getOrderRefNo(), ord);
		return ord;
	}
	
	@RequestMapping(value = OrderURIConstants.GET_ORD, method = RequestMethod.GET)
	public @ResponseBody Order getOrder(@PathVariable("id") String orderId) {
		return orderData.get(orderId);
	}
	
	@RequestMapping(value = OrderURIConstants.GET_ALL_ORD, method = RequestMethod.GET)
	public @ResponseBody List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<Order>();
		Set<String> orderIdKeys = orderData.keySet();
		for(String i : orderIdKeys){
			orders.add(orderData.get(i));
		}
		return orders;
	}
	
	@RequestMapping(value = OrderURIConstants.UPDATE_ORD, method = RequestMethod.POST)
	public @ResponseBody Order updateOrder(@RequestBody Order ord) {
		ord.setCreatedDate(new Date());
		orderData.put(ord.getOrderRefNo(), ord);
		return ord;
	}
		
/*	@RequestMapping(value = BricksOrderURIConstants.FULFIL_ORD, method = RequestMethod.PUT)
	public @ResponseBody BricksOrder deleteOrder(@PathVariable("id") String ordId) {
		System.out.println("Start deleteEmployee.");
		BricksOrder ord = orderData.get(ordId);
		ord.setStatus("Dispatched");
		return ord;
	}*/
}
