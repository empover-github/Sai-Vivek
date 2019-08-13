package com.order.test.client;

import com.order.main.model.Order;

public interface TestOrderClient {
	public boolean createOrder(int qty);
	public boolean getOrder(String ordRefNo);
	public  String updateOrder(String ordRefNo, int qty);
	public boolean  getAllOrders();
	public boolean fulFilOrder(String ordRefNo);
	public void printOrderData(Order ord);
}
