package com.order_item.model;
import java.util.*;

public interface OrderItemDAO_interface {
	public void insert (OrderItemVO orderitemVO);
	public void update (OrderItemVO orderitemVO);
	public void delete (Integer orderno,Integer productNo);
	public OrderItemVO findByPrimaryKey1 (Integer orderNo,Integer productNo);
	public OrderItemVO findByPrimaryKey (Integer orderNo);
	public OrderItemVO findByPrimaryKey (Integer orderNo,Integer productNo);
	public OrderItemVO findByPrimaryKeyByProductNo(Integer productNo);
	public List<OrderItemVO> findByPrimaryKeyByOrderNo(Integer orderNO);

	public List<OrderItemVO> getAll();
	public void insert2 (OrderItemVO orderitemVO , java.sql.Connection con);
}
