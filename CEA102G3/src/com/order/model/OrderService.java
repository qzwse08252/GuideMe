package com.order.model;

import java.util.List;
import java.util.Set;

import com.order_item.model.OrderItemVO;

public class OrderService {
	
	private OrderDAO_interface dao;
	
	public OrderService() {
		dao = new OrderDAO();
	}
	
	public OrderVO addOrder(Integer memberNo,
			 Integer Sum, String creditCardNo,Set<OrderItemVO>orderItems) {

		OrderVO orderVO = new OrderVO();
		
		
		orderVO.setMemberNo(memberNo);
		orderVO.setSum(Sum);
		java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
		orderVO.setOrderDate(now);
		orderVO.setCreditCardNo(creditCardNo);
		for(OrderItemVO orderItemVO : orderItems) {
			orderItemVO.setOrderVO(orderVO);
		}
		orderVO.setOrderItems(orderItems);
		
		dao.insert(orderVO);
		
		return orderVO;
		}
	
	public OrderVO addOrderwithOrderItem(Integer memberNo,java.sql.Date orderDate,
			 Integer Sum, String creditCardNo) {

		OrderVO orderVO = new OrderVO();
		
		
		orderVO.setMemberNo(memberNo);
		orderVO.setOrderDate(orderDate);
		orderVO.setSum(Sum);
		orderVO.setCreditCardNo(creditCardNo);
		dao.insert(orderVO);
		
		return orderVO;
		}
	
	public OrderVO updateOrder( java.sql.Date orderDate,
			 Integer Sum, String creditCardNo,Integer orderNo) {

		OrderVO orderVO = new OrderVO();
		
		
		
		orderVO.setOrderDate(orderDate);
		orderVO.setSum(Sum);
		orderVO.setCreditCardNo(creditCardNo);
		orderVO.setOrderNo(orderNo);
		dao.update(orderVO);
		
		return orderVO;
		}
	
	
	public void deleteOrder(Integer orderNo) {
		dao.delete(orderNo);
	}
	
	public OrderVO getOneOrder(Integer orderNo) {
		return dao.findByPrimaryKey(orderNo);
	}
	
	public List<OrderVO> getAll(){
		return dao.getAll();
	}
	
	public List<OrderVO> getMemberOrders(Integer memberNo){
		return dao.getMemberOrders(memberNo);
	}
	

}
