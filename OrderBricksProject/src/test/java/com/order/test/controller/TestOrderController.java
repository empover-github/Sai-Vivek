
package com.order.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import com.order.main.model.Order;
public class TestOrderController {
	
	private AtomicLong nextId = new AtomicLong(1);
	
	    //Map to store Bricks Orders, ideally we should use database
		public static Map<String, Order> orderData = new HashMap<String, Order>();
		
		public  Order createOrder(Order ord) {
			ord.setCreatedDate(new Date());
			ord.setOrderRefNo(String.valueOf(nextId.getAndIncrement()));
			orderData.put(ord.getOrderRefNo(), ord);
			return ord;
		}
		
		public Order getOrder(String orderId) {
			return orderData.get(orderId);
		}
		
		public List<Order> getAllOrders() {
			List<Order> orders = new ArrayList<Order>();
			Set<String> orderIdKeys = orderData.keySet();
			for(String i : orderIdKeys){
				orders.add(orderData.get(i));
			}
			return orders;
		}
		
		public Order updateOrder(Order ord) {
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
