package com.order.main.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	
	private String orderRefNo;
	private Integer orderQty;
	private String status;
	private Date createdDate;
	
	public String getOrderRefNo() {
		return orderRefNo;
	}
	public void setOrderRefNo(String orderRefNo) {
		this.orderRefNo = orderRefNo;
	}
	public Integer getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
