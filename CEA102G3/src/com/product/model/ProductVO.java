package com.product.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.order_item.model.OrderItemVO;



public class ProductVO implements java.io.Serializable{
	private Integer productNo;
	private String productName;
	private Integer listPrice;
	private String descr;
	private Integer totalRateCount;
	private Integer totalRate;
	private Boolean productStatus;
	private byte[] productPic1;
	private byte[] productPic2;
	private byte[] productPic3;
	private Set<OrderItemVO> orderItems = new HashSet<OrderItemVO>();
	
	
//	public String getPicSrc1() {
//		String picSrc ="?productPicCol=product_pic1\r\n" + 
//				"	&table=guideme.product&productNoCol=product_No&PKNumber="+this.productNo;
//		return picSrc;
//	}
//	public String getPicSrc2() {
//		String picSrc ="?productPicCol=product_pic2\r\n" + 
//				"	&table=guideme.product&productNoCol=product_No&PKNumber="+this.productNo;
//		return picSrc;
//	}
//	public String getPicSrc3() {
//		String picSrc ="?productPicCol=product_pic3\r\n" + 
//				"	&table=guideme.product&productNoCol=product_No&PKNumber="+this.productNo;
//		return picSrc;
//	}
	
	


	public Set<OrderItemVO> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(Set<OrderItemVO> orderItems) {
		this.orderItems = orderItems;
	}


	public ProductVO() {}
	

	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Integer getListPrice() {
		return listPrice;
	}
	public void setListPrice(Integer listPrice) {
		this.listPrice = listPrice;
	}
	
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	public Integer getTotalRateCount() {
		return totalRateCount;
	}
	public void setTotalRateCount(Integer totalRateCount) {
		this.totalRateCount = totalRateCount;
	}
	
	public Integer getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(Integer totalRate) {
		this.totalRate = totalRate;
	}
	
	public Boolean getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Boolean productStatus) {
		this.productStatus = productStatus;
	}
	
	public byte[] getProductPic1() {
		return productPic1;
	}
	public void setProductPic1(byte[] productPic1) {
		this.productPic1 = productPic1;
	}
	
	public byte[] getProductPic2() {
		return productPic2;
	}
	public void setProductPic2(byte[] productPic2) {
		this.productPic2 = productPic2;
	}
	
	public byte[] getProductPic3() {
		return productPic3;
	}
	public void setProductPic3(byte[] productPic3) {
		this.productPic3 = productPic3;
	}
	
	
	
	
	
	
	
	

}
