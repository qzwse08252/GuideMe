package com.order.model;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.order_item.model.OrderItemVO;

import util.HibernateUtil;

public class OrderDAO implements OrderDAO_interface{

	@Override
	public void insert(OrderVO orderVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(orderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		
	}

	@Override
	public void update(OrderVO orderVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			session.saveOrUpdate(orderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		
		
	}

	@Override
	public void delete(Integer orderNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderVO findByPrimaryKey(Integer orderNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		OrderVO orderVO;
		try {
			session.beginTransaction();
			orderVO = (OrderVO)session.get(OrderVO.class, orderNo);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		
		return orderVO;
	}

	@Override
	public List<OrderVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<OrderVO> list = null;
		
		try {
			session.beginTransaction();
			Query query = session.createQuery("from OrderVO orderVO join fetch orderVO.orderItems",OrderVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		
		
		
		return list;
	}

	@Override
	public void insertWithOrderItem(OrderVO orderVO, List<OrderItemVO> list) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) {
		OrderDAO dao = new OrderDAO();
		
		
		
		List<OrderVO>list = dao.getAll();
		for(OrderVO orderVO : list) {
			System.out.print("orderNo : "+ orderVO.getOrderNo()+", ");
			System.out.print("memberNo : "+ orderVO.getMemberNo()+", ");
			System.out.print("orderDate : "+ orderVO.getOrderDate()+", ");
			System.out.print("sum : "+ orderVO.getSum()+", ");
			System.out.print("creditCardNo : "+ orderVO.getCreditCardNo()+", ");
			System.out.println();
			Set<OrderItemVO> orderItems = orderVO.getOrderItems();
			for(OrderItemVO orderItemVO : orderItems) {
				System.out.println(orderItemVO.getProductCount()+", ");
				System.out.println(orderItemVO.getSellingPrice()+", ");
				System.out.println("---");
			}
			System.out.println("=================");
		}
		
	}

	@Override
	public List<OrderVO> getMemberOrders(Integer memberNo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<OrderVO> list = null;
		
		try {
			session.beginTransaction();
			Query query = session.createQuery("from OrderVO orderVO join fetch orderVO.orderItems where orderVO.memberNo="+memberNo +" order by orderVO.orderNo desc",OrderVO.class);
//			Query query = session.createQuery("from OrderVO orderVO where orderVO.memberNo="+memberNo +" order by orderVO.orderNo desc",OrderVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		
		
		
		return list;
		
	}

}
