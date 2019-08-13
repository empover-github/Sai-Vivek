package com.order.test.client;

import java.util.List;

import org.springframework.web.client.RestTemplate;
import com.order.main.model.Order;
import com.order.test.controller.TestOrderController;

public class TestOrderClientImp implements TestOrderClient{
	
	TestOrderController boc = new TestOrderController();
	
	public static void main(String args[]){
		TestOrderClientImp boc = new TestOrderClientImp();
		System.out.println("*****Create Order****");
		boc.createOrder(5000);
		boc.createOrder(8000);
		System.out.println("*****Get Order******");
		boc.getOrder("2");
		System.out.println("*****Update Order******");
		boc.updateOrder("2", 6000);
		boc.updateOrder("3", 6000);
		System.out.println("*****All Orders******");
		boc.getAllOrders();
		System.out.println("*****FulFil Orders******");
		boc.fulFilOrder("2");
		boc.updateOrder("2", 9000);
	}
	
	/*
	 * Create Order
	 */
	@Override
	public boolean createOrder(int qty) {
		if(qty > 0) {
			RestTemplate restTemplate = new RestTemplate();
			Order ord = new Order();
			ord.setOrderQty(qty);
			ord.setStatus("Pending");
			Order response = boc.createOrder(ord);
			printOrderData(response);
			return true;
		}else {
			return false; 
		}

	}

	/*
	 * Get order by giving order ref no
	 */
	@Override
	public boolean getOrder(String ordRefNo) {
		RestTemplate restTemplate = new RestTemplate();
		Order ord = boc.getOrder(ordRefNo);
		if(ord != null) {
			printOrderData(ord);
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * Update order by giving order ref no
	 */
	@Override
	public  String updateOrder(String ordRefNo, int qty) {
		RestTemplate restTemplate = new RestTemplate();
		Order ord = boc.getOrder(ordRefNo);
		if(ord == null || !ord.getOrderRefNo().equals(ordRefNo)) {
			return "400 bad request";
		}else if(ord.getStatus().equals("Dispatched")){
			return "400 bad request";
		}else {
			ord.setOrderRefNo(ordRefNo);ord.setOrderQty(qty);
			Order response = boc.updateOrder(ord);
			printOrderData(response);
			return "true";
		}
	}

	/*
	 * Get All orders 
	 */
	@Override
	public boolean  getAllOrders() {
		RestTemplate restTemplate = new RestTemplate();
		List<Order> ords = boc.getAllOrders();
		for(Order map : ords){
			System.out.println("orderRefNo="+map.getOrderRefNo()+",orderQty="+map.getOrderQty()+",CreatedDate="+map.getCreatedDate());
		}
		if(ords.size() > 0) {
			return true;
		}else {
			return false;
		}
	}

	/*
	 * FulFil order by giving order
	 */
	@Override
	public boolean fulFilOrder(String ordRefNo) {
		RestTemplate restTemplate = new RestTemplate();
		Order ord = boc.getOrder(ordRefNo);
		if(ord != null) {
			ord.setStatus("Dispatched");
			Order response= boc.updateOrder(ord);
			printOrderData(response);
			return true;
		}else {
			return false;
		}
	}
	@Override
	public void printOrderData(Order ord){
		System.out.println("Order Ref no ="+ord.getOrderRefNo()+",Order Qty="+ord.getOrderQty()+",Status="+ord.getStatus()+",Created Date="+ord.getCreatedDate());
	}

}
