package com.order.test;



import org.junit.Assert;
import org.junit.Test;

import com.order.test.client.TestOrderClientImp;


public class TestOrder {
	private TestOrderClientImp boc = new TestOrderClientImp();
	String args[] =null;
	/*
	 *Order creation with valid quantity
	 */
	@Test
	public void tc_01()
	{
		boolean expectedResult = true;
		boolean ActuaResult = boc.createOrder(4500);
		Assert.assertEquals(expectedResult , ActuaResult);
		System.out.println("tc_01 : " + "Order created successfully");
	}
	
	/*
	 * Order creation with invalid quantity
	 */
	@Test
	public void tc_02()
	{
		boolean expectedResult = false;
		boolean ActuaResult = boc.createOrder(-3000);
		Assert.assertEquals(expectedResult , ActuaResult);
		System.out.println("tc_02 : " + "Order creation failed");
	}
	
	/*
	 * Get Order details with valid Order number as input
	 */
	@Test
	public void tc_03()
	{
		boolean ActuaResult = boc.getOrder("1");
		Assert.assertEquals(true , ActuaResult);
		System.out.println("tc_03 : " + "Get order details with order ref number");
	}
	
	/*
	 * Get Order details with invalid order number as input
	 */
	@Test
	public void tc_04()
	{
		boolean ActuaResult = boc.getOrder("3c");
		Assert.assertEquals(false , ActuaResult);
		System.out.println("tc_04 : " + "No Order details returned");
	}
	
	/*
	 * Get all orders
	 */
	@Test
	public void tc_05()
	{
		boolean ActuaResult = boc.getAllOrders();
		Assert.assertEquals(true , ActuaResult);
		System.out.println("tc_05 : " + "Get all Order details");
	}
	
	/*
	 * Update Order with valid order number and qty as input
	 */
	@Test
	public void tc_06()
	{
		String ActuaResult = boc.updateOrder("1", 9000);
		Assert.assertEquals("true" , ActuaResult);
		System.out.println("tc_06 : " + "Order details updated");
	}
	
	/*
	 *  Fulfil/Dispatch Order with valid order number as input
	 */
	@Test
	public void tc_07()
	{
		boolean ActuaResult = boc.fulFilOrder("1");
		Assert.assertEquals(true , ActuaResult);
		System.out.println("tc_07 : " + "Order dispatched successfully");
	}
	
	/*
	 *Fulfil/Dispatch Order  with invalid input
	 */
	@Test
	public void tc_08()
	{
		boolean ActuaResult = boc.fulFilOrder("3c");
		Assert.assertEquals(false , ActuaResult);
		System.out.println("tc_08 : " + "400 bad request");
	}
	
	/*
	 * update order for dispatched order
	 */
	@Test
	public void tc_09()
	{
		String ActuaResult = boc.updateOrder("1", 10000);
		Assert.assertEquals("400 bad request" , ActuaResult);
		System.out.println("tc_09 : " + "400 bad request");
	}
	
	/*
	 * Update Order with invalid order number and qty as input
	 */
	@Test
	public void tc_10()
	{
		String ActuaResult = boc.updateOrder("3c", 10000);
		Assert.assertEquals("400 bad request" , ActuaResult);
		System.out.println("tc_10 : " + "400 bad request");
		
	}
}
