package com.hemesh.DAO;

import java.util.List;

import com.hemesh.Model.Orders;

public interface OrdersDAO {
	
	public int addOrder(Orders order) ;
	
	public void deleteOrder(int orderId);
	
	public Orders getOrder(int orderId);
	
	public List<Orders> getAllOrders(int userId);
	
	
	
	
	

}
