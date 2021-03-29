package com.order.model;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.order_item.model.OrderItemVO;

public class OrderVO {
	
	
	private Integer orderNo;
	private Integer memberNo;
	private Date orderDate;
	private Integer sum;
	private String creditCardNo;
	private Set<OrderItemVO> orderItems = new HashSet<OrderItemVO>();
	
	public Set<OrderItemVO> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItemVO> orderItems) {
		this.orderItems = orderItems;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	
	
	
	
	
	



}
	