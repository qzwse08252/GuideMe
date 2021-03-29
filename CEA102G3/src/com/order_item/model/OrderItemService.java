package com.order_item.model;

import java.util.List;

import com.order.model.OrderVO;
import com.product.model.ProductVO;

public class OrderItemService {
	private OrderItemDAO_interface dao;
	public OrderItemService() {
		dao = new OrderItemDAO();
		}
	
	public OrderItemVO addOrderItem(OrderVO orderVO, ProductVO productVO,Integer productCount,
			Integer sellingPrice) {

		OrderItemVO orderItemVO = new OrderItemVO();
		
		orderItemVO.setOrderVO(orderVO);
		orderItemVO.setProductVO(productVO);
		orderItemVO.setProductCount(productCount);
		orderItemVO.setSellingPrice(sellingPrice);
		
		dao.insert(orderItemVO);
		
		return orderItemVO;
		}
	
	public OrderItemVO updateOrderItem(OrderVO orderVO, ProductVO productVO,Integer productCount,
			Integer sellingPrice) {
		OrderItemVO orderitemVO = new OrderItemVO();
		
		orderitemVO.setOrderVO(orderVO);
		orderitemVO.setProductVO(productVO);
		orderitemVO.setProductCount(productCount);
		orderitemVO.setSellingPrice(sellingPrice);
		dao.update(orderitemVO);
		return orderitemVO;
		}
	public void deleteOrderItem(Integer orderNo,Integer productNo) {
		dao.delete(orderNo,productNo);
		}
	
	public OrderItemVO getOneOrderItem(Integer orderNo) {
		return dao.findByPrimaryKey(orderNo);
	}
	public OrderItemVO getOneOrderItemByOrderNo(Integer orderNo) {
		return dao.findByPrimaryKey(orderNo);
	}
	public OrderItemVO getOneOrderItem1(Integer orderNo,Integer productNo) {
		return dao.findByPrimaryKey1(orderNo,productNo);
	}
	public OrderItemVO getOneOrderItemByProductNo(Integer productNo) {
		return dao.findByPrimaryKeyByProductNo(productNo);
	}
	
	public List<OrderItemVO>getOnePrimaryKeyByOrderNo(Integer orderNo){
		return dao.findByPrimaryKeyByOrderNo(orderNo);
	}
	
	public List<OrderItemVO> getAll(){
		return dao.getAll();
	}

}
