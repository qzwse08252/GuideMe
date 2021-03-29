package com.product_rate.model;

import java.util.List;


public interface ProductRateDAO_interface {
	public void insert(ProductRateVO productRateVO);
	public void update(ProductRateVO productRateVO);
	public void delete(Integer memberNo,Integer productNo);
	public ProductRateVO findByPrimaryKey(Integer memberNo);
	public ProductRateVO findByPrimaryNo(Integer productNo);
	public ProductRateVO findByPrimaryRate(Integer productNo);
	public Integer getByRate(Integer productNo);
	public Integer getByRateCount(Integer productNo);


	public List<ProductRateVO>getAll();


}
