package com.product.model;
import java.util.*;


public class ProductService {
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public ProductVO addProduct( String productName, Integer listPrice,
			String  descr, byte[] productPic1) {

		ProductVO productVO = new ProductVO();


		productVO.setProductName(productName);
		productVO.setListPrice(listPrice);
		productVO.setDescr(descr);
		productVO.setProductStatus(true);
		productVO.setProductPic1(productPic1);

		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer productNo,String productName, Integer listPrice,
			String  descr,boolean productStatus,byte[] productPic1) {

		ProductVO productVO = dao.findByPrimaryKey(productNo);
		
		productVO.setProductNo(productNo);
		productVO.setProductName(productName);
		productVO.setListPrice(listPrice);
		productVO.setDescr(descr);
		productVO.setProductStatus(productStatus);
		productVO.setProductPic1(productPic1);

	
		dao.update(productVO);

		return productVO;
	}
	public ProductVO updateProductRate(int productNo, int rate) {
		ProductVO productVO = dao.findByPrimaryKey(productNo);
		productVO.setTotalRate(productVO.getTotalRate()+rate);
		productVO.setTotalRateCount(productVO.getTotalRateCount()+1);
		
		dao.update(productVO);
		return productVO;
	}
	
	public ProductVO updateProductNoIMG( String productName, Integer listPrice,
			String  descr) {

		ProductVO productVO = new ProductVO();
		
		productVO.setProductName(productName);
		productVO.setListPrice(listPrice);
		productVO.setDescr(descr);
		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(Integer productNo) {
		dao.delete(productNo);
	}

	public ProductVO getOneProduct(Integer productNo) {
		return dao.findByPrimaryKey(productNo);
	}
	public List<ProductVO> getOneProductByName(String productName) {
		return dao.findByPrimaryKeyName(productName);
	}
	
	public ProductVO getChangeProductStatus(Boolean productStatus,Integer productNo) {

		ProductVO productVO = new ProductVO();
		
		productVO.setProductStatus(productStatus);
		productVO.setProductNo(productNo);
		
		dao.changeStatus(productVO);
		return productVO;
		
	}
	

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	public List<ProductVO> getProductOnShelfByName(String productName) {
		return dao.getProductOnShelfByName(productName);
	}
	
	

}
