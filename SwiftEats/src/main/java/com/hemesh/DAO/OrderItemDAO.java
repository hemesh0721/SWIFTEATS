package com.hemesh.DAO;

import java.util.List;

import com.hemesh.Model.OrderItem;

public interface OrderItemDAO {
	
	public void addOrderItem(OrderItem orderItem);
	
	public void deleteOrderItem(int orderId);
	
	public void updateOrderItem(int quantity,int orderId);
	
	public OrderItem getOrderItem(int orderId);
	
	public List<OrderItem> getOrderItems(int orderId);
	
	public List<OrderItem> getAllOrderItems();
}
