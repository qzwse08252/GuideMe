package com.product.model;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtil;

public class ProductDAO implements ProductDAO_interface {

	@Override
	public void insert(ProductVO productVO) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			session.saveOrUpdate(productVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void update(ProductVO productVO) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			session.saveOrUpdate(productVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void updateNoImg(ProductVO productVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer productNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStatus(ProductVO ProductVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProductVO findByPrimaryKey(Integer productNo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ProductVO productVO;
		try {
			session.beginTransaction();
			productVO = (ProductVO) session.get(ProductVO.class, productNo);
			
			session.getTransaction().commit();
		}catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return productVO;
	}

	@Override
	public List<ProductVO> findByPrimaryKeyName(String productName) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ProductVO> list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ProductVO where productName like ?0");
			query.setParameter(0, "%"+productName+"%");
			list = query.getResultList();
			
			session.getTransaction().commit();
		}catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return list;
	}

	@Override
	public Set<ProductVO> getProductByStatus(Boolean productStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getAll() {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ProductVO> list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ProductVO order by productNo");
			list = (List<ProductVO>)query.getResultList();
			session.getTransaction().commit();
		}catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return list;
	}
	
	public static void main(String args[]) {
		ProductDAO dao = new ProductDAO();
		
		// insert one
//		ProductVO productVO = new ProductVO();
//		productVO.setProductName("GOOD");
//		productVO.setListPrice(100);
//		productVO.setDescr("good");
//		dao.insert(productVO);
		
		// update one
//		ProductVO productVO = new ProductVO();
//		productVO.setProductNo(12);
//		productVO.setDescr("超好玩");
//		productVO.setListPrice(1000);
//		productVO.setProductName("迪士尼樂園");
//		dao.update(productVO);
		
		//get one 
//		ProductVO productVO = dao.findByPrimaryKey(12);
//		System.out.println("name : "+ productVO.getProductName());
		
		//get all
		List<ProductVO> list = dao.getProductOnShelfByName("台");
		for(ProductVO aVO :list) {
			System.out.println(aVO.getProductName());
		}
	}

	@Override
	public List<ProductVO> getProductOnShelfByName(String productName) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ProductVO> list = null;
		
		try {
			session.beginTransaction();
			Query query = session.createQuery("SELECT productVO from ProductVO productVO where productVO.productStatus = true and productVO.productName like '%"+productName+"%'");
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			session.getTransaction().rollback();
			throw e;
		}
		
		
		return list;
	}

	
}
