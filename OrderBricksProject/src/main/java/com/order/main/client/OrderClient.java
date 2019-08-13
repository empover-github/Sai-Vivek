package com.order.main.client;

import com.order.main.model.Order;

public interface OrderClient {
	public void createOrder(int qty);
	public void getOrder(String ordRefNo);
	public void updateOrder(String ordRefNo, int qty);
	public void  getAllOrders();
	public void fulFilOrder(String ordRefNo);
	public void printOrderData(Order ord);

}
