package com.product_rate.model;
import java.util.List;
public class ProductRateService {
	
	private ProductRateDAO_interface dao;
	
	public ProductRateService() {
		dao = new ProductRateJNDIDAO();
	}
	
	public ProductRateVO addProductRate(Integer memberNo,Integer productNo,Integer rate,String comment) {
		ProductRateVO productRateVO = new ProductRateVO();
		
		productRateVO.setMemberNo(memberNo);
		productRateVO.setProductNo(productNo);
		productRateVO.setRate(rate);
		productRateVO.setComment(comment);
		
		dao.insert(productRateVO);
		return productRateVO;
		
	}
	
	public ProductRateVO updateProductRate(Integer memberNo,Integer productNo,Integer rate,String comment) {
		ProductRateVO productRateVO = new ProductRateVO();
		
		productRateVO.setMemberNo(memberNo);
		productRateVO.setProductNo(productNo);
		productRateVO.setRate(rate);
		productRateVO.setComment(comment);
		
		dao.update(productRateVO);
		return productRateVO;
		}
	
	public void deleteProductRate(Integer memberNo,Integer productNo) {
		dao.delete(memberNo,productNo);
	}
	
	public ProductRateVO getOneProductMemberNo(Integer memberNo) {
		return dao.findByPrimaryKey(memberNo);
	}
	public ProductRateVO getOneProductNo(Integer productNo) {
		return dao.findByPrimaryNo(productNo);
	}
	public ProductRateVO getOneProductRate(Integer rate) {
		return dao.findByPrimaryRate(rate);
	}
	
	public Integer getbyRate(Integer productNo) {
		return dao.getByRate(productNo);
	}
	public Integer getbyRateCount(Integer productNo) {
		return dao.getByRateCount(productNo);
	}
	public List<ProductRateVO>getAll(){
		return dao.getAll();
	}

}
