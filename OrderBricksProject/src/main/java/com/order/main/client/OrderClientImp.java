package com.order.main.client;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.order.main.controller.OrderController;
import com.order.main.controller.OrderURIConstants;
import com.order.main.model.Order;

public class OrderClientImp implements OrderClient{

	public static final String SERVER_URI = "http://localhost:8080/OrderBricksProject";
	OrderController boc = new OrderController();
	
	public static void main(String args[]){
		OrderClientImp boc = new OrderClientImp();
		System.out.println("---Create Order---");
		boc.createOrder(10000);
		boc.createOrder(12000);
		System.out.println("---Get Order---");
		boc.getOrder("1");
		System.out.println("---Update Order---");
		boc.updateOrder("1", 14000);
		System.out.println("---Update Order with Invalid order number---");
		boc.updateOrder("7", 18000);
		System.out.println("---FulFil Orders---");
		boc.fulFilOrder("1");
		System.out.println("---Update Order with fulfil order number---");
		boc.updateOrder("1", 18000);
		System.out.println("---Get All Orders---");
		boc.getAllOrders();
	}
	
	/*
	 * Create Order
	 */
	@Override
	public void createOrder(int qty) {
		if(qty > 0) {
			RestTemplate restTemplate = new RestTemplate();
			Order ord = new Order();
			ord.setOrderQty(qty);
			Order response = restTemplate.postForObject(SERVER_URI+OrderURIConstants.CREATE_ORD, ord, Order.class);
			printOrderData(response);
		}else {
			System.out.println("Required valid qty number..");
		}

	}

	/*
	 * Get 0rder by giving order ref no
	 */
	@Override
	public void getOrder(String ordRefNo) {
		RestTemplate restTemplate = new RestTemplate();
		Order ord = restTemplate.getForObject(SERVER_URI+"/rest/ord/"+ordRefNo, Order.class);
		if(ord != null) {
			printOrderData(ord);
		}else {
			System.out.println("400 bad request");
		}
	}
	
	/*
	 * Update order by giving order ref no
	 */
	@Override
	public  void updateOrder(String ordRefNo, int qty) {
		RestTemplate restTemplate = new RestTemplate();
		Order ord = restTemplate.getForObject(SERVER_URI+"/rest/ord/"+ordRefNo, Order.class);
		if(ord == null) {
			System.out.println("400 bad request");
		}else if(ord.getStatus().equals("Dispatched")){
			System.out.println("400 bad request");
		}else {
			ord.setOrderRefNo(ordRefNo);ord.setOrderQty(qty);
			Order response = restTemplate.postForObject(SERVER_URI+OrderURIConstants.UPDATE_ORD, ord, Order.class);
			printOrderData(response);
		}
	}

	/*
	 * Get All orders 
	 */
	@Override
	public void  getAllOrders() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<BricksOrder> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> ords =  restTemplate.getForObject(SERVER_URI+OrderURIConstants.GET_ALL_ORD, List.class);
		for(LinkedHashMap map : ords){
			//System.out.println("orderRefNo="+map.get("orderRefNo")+",orderQty="+map.get("orderQty")+",CreatedDate="+map.get("createdDate"));
			System.out.println("orderRefNo="+map.get("orderRefNo")+",orderQty="+map.get("orderQty")+",Status="+map.get("status")+",CreatedDate="+map.get("createdDate"));
		}
		
	}

	/*
	 * FulFil order by giving order
	 */
	@Override
	public void  fulFilOrder(String ordRefNo) {
		RestTemplate restTemplate = new RestTemplate();
		Order ord = restTemplate.getForObject(SERVER_URI+"/rest/ord/"+ordRefNo, Order.class);
		if(ord != null) {
			ord.setStatus("Dispatched");
			Order response = restTemplate.postForObject(SERVER_URI+OrderURIConstants.UPDATE_ORD, ord, Order.class);
			printOrderData(response);
		}else {
			System.out.println("400 bad request");
		}
	}
	
	@Override
	public void printOrderData(Order ord){
		System.out.println("Order Ref no ="+ord.getOrderRefNo()+",Order Qty="+ord.getOrderQty()+",Status="+ord.getStatus()+",Created Date="+ord.getCreatedDate());
	}
}
