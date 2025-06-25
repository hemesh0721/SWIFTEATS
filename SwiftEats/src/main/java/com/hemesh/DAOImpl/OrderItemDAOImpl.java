package com.hemesh.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hemesh.Connection.Connections;
import com.hemesh.DAO.OrderItemDAO;
import com.hemesh.Model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {

	@Override
	public void addOrderItem(OrderItem orderItem) {
		
		try {
			Connection conn = Connections.getConnection();
			String query = "insert into order_item(orderId,menuId,quantity,totalPrice) values(?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getMenuId());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setInt(4, orderItem.getTotalPrice());

			
			pstmt.executeUpdate();
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			
		
	}

	@Override
	public void deleteOrderItem(int orderId) {
		
		try {
			Connection conn = Connections.getConnection();
			String query = "delete from order_item where orderId='"+orderId+"';";
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateOrderItem(int quantity,int orderId) {
		
		try {
			Connection conn = Connections.getConnection();
			String query = "UPDATE order_item SET quantity = ? WHERE orderId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, quantity);
	        pstmt.setInt(2, orderId);

			pstmt.executeUpdate();
			
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public OrderItem getOrderItem(int orderId) {
		try {
			Connection conn = Connections.getConnection();
			String query = "select * from order_item where orderId='"+orderId+"';";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			if(res.next()) {
				int orderItemId = res.getInt(1);
				int ordersId = res.getInt(2);
				int menuId = res.getInt(3);
				int quantity = res.getInt(4);
				int totalPrice = res.getInt(5);
				
				OrderItem item = new OrderItem(orderItemId, ordersId, menuId, quantity, totalPrice);
				
				return item;
				
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	


	@Override
	public List<OrderItem> getOrderItems(int orderId) {
		List<OrderItem> list = new ArrayList<>();
		try {
			Connection con = Connections.getConnection();
			String query = "select * from order_item where orderId =?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, orderId);
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				int orderItemId = res.getInt(1);
				int ordersId = res.getInt(2);
				int menuId = res.getInt(3);
				int quantity = res.getInt(4);
				int totalPrice = res.getInt(5);
				OrderItem item = new OrderItem(orderItemId, ordersId, menuId, quantity, totalPrice);
				
				list.add(item);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	@Override
	public List<OrderItem> getAllOrderItems() {
		
		List<OrderItem> list = new ArrayList<OrderItem>();
		
		try {
			Connection conn = Connections.getConnection();
			String query = "select * from order_item;";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			while(res.next()) {
				int orderItemId = res.getInt(1);
				int ordersId = res.getInt(2);
				int menuId = res.getInt(3);
				int quantity = res.getInt(4);
				int totalPrice = res.getInt(5);
				
				OrderItem item = new OrderItem(orderItemId, ordersId, menuId, quantity, totalPrice);
				
				list.add(item);
				
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
