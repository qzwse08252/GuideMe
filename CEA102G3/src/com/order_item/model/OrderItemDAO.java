package com.order_item.model;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtil;

public class OrderItemDAO implements OrderItemDAO_interface {

	@Override
	public void insert(OrderItemVO orderItemVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(orderItemVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;

		}

	}

	@Override
	public void update(OrderItemVO orderItemVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(orderItemVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;

		}
	}

	@Override
	public void delete(Integer orderno, Integer productNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderItemVO findByPrimaryKey1(Integer orderNo, Integer productNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemVO findByPrimaryKey(Integer orderNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemVO findByPrimaryKey(Integer orderNo, Integer productNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemVO findByPrimaryKeyByProductNo(Integer productNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItemVO> findByPrimaryKeyByOrderNo(Integer orderNO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItemVO> getAll() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<OrderItemVO> list = null;
		
		try {
			session.beginTransaction();
			Query query = session.createQuery("from OrderItemVO");
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		
		
		return list;
	}

	@Override
	public void insert2(OrderItemVO orderitemVO, Connection con) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String args[]) {
		OrderItemDAO dao = new OrderItemDAO();
		
		List<OrderItemVO> list = dao.getAll();
		for(OrderItemVO orderItemVO : list) {
			System.out.print("orderNo: "+ orderItemVO.getOrderVO().getOrderNo()+", ");
			System.out.print("productNo: "+ orderItemVO.getProductVO().getProductNo()+", ");
			System.out.print("productCount: "+ orderItemVO.getProductCount()+", ");
			System.out.print("sellingPrice: "+ orderItemVO.getSellingPrice()+", ");
			System.out.println();
		}
	}
	

}
