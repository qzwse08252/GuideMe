package com.product.model;

import java.util.List;
import java.util.Set;


public interface ProductDAO_interface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void updateNoImg(ProductVO productVO);
	public void delete(Integer productNo);
	public void changeStatus(ProductVO ProductVO);
	public ProductVO findByPrimaryKey(Integer productNo);
	public List<ProductVO> findByPrimaryKeyName(String productName);
	public Set<ProductVO> getProductByStatus(Boolean productStatus);
	public List<ProductVO> getAll();
	public List<ProductVO> getProductOnShelfByName(String productName);

}
