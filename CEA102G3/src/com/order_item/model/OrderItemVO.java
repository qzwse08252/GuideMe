package com.order_item.model;
import javax.persistence.*;

import com.order.model.OrderVO;
import com.product.model.ProductVO;

public class OrderItemVO implements java.io.Serializable{
	
	private OrderVO orderVO;
	private ProductVO productVO;
	private Integer productCount;
	private Integer sellingPrice;
	
	public OrderVO getOrderVO() {
		return orderVO;
	}
	public void setOrderVO(OrderVO orderVO) {
		this.orderVO = orderVO;
	}
	
	public ProductVO getProductVO() {
		return this.productVO;
	}
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	
	public Integer getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	
	
	

}
