package com.hemesh.DAOImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hemesh.Connection.Connections;
import com.hemesh.DAO.OrdersDAO;
import com.hemesh.Model.Orders;

public class OrderDAOImpl implements OrdersDAO {

	@Override
	public int addOrder(Orders order) {
		int orderId=0;
		
		try {
			Connection conn = Connections.getConnection();
			String query = "insert into orders(restaurantId,userId,orderDate,totalAmount,status,paymentMode,address) values(?,?,?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1,order.getRestaurantId());
			pstmt.setInt(2, order.getUserId());
			pstmt.setDate(3, order.getOrderDate());
			pstmt.setInt(4, order.getTotalAmount());
			pstmt.setString(5, order.getStatus());
			pstmt.setString(6, order.getPaymentMode());
			pstmt.setString(7,order.getAddress());
			
			pstmt.executeUpdate();
			
			ResultSet keys = pstmt.getGeneratedKeys();
			
			while(keys.next()) {
				 orderId = keys.getInt(1);
			}
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		return orderId;
	}

	@Override
	public void deleteOrder(int orderId) {
		try {
			Connection conn = Connections.getConnection();
			String query = "delete from orders WHERE orderId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, orderId);

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
	public Orders getOrder(int orderId) {
			
		Orders order = null;
		
		try {
			Connection conn = Connections.getConnection();
			String query = "select * from orders WHERE orderId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, orderId);

			 ResultSet res = pstmt.executeQuery();
			 
			 if(res.next()) {
				 int orderID = res.getInt(1);
					int restaurantID = res.getInt(2);
					int userID = res.getInt(3);
					Date orderDate = res.getDate(4);
					int totalAmount = res.getInt(5);
					String status = res.getString(6);
					String paymentMode = res.getString(7);
					String address = res.getString(8);
					
					 order = new Orders(orderID, restaurantID, userID, orderDate, totalAmount, status, paymentMode,address);
					
					return order;
			 }
			 
			
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return order;
		
	}

	@Override
	public List<Orders> getAllOrders(int userId) {
		
		List<Orders> list = new ArrayList<Orders>();
		try {
			
			Connection conn = Connections.getConnection();
			String query = "select * from orders where userId='"+userId+"';";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			while(res.next()) {
				int orderID = res.getInt(1);
				int restaurantID = res.getInt(2);
				int userID = res.getInt(3);
				Date orderDate = res.getDate(4);
				int totalAmount = res.getInt(5);
				String status = res.getString(6);
				String paymentMode = res.getString(7);
				String address = res.getString(8);
				
				Orders order = new Orders(orderID, restaurantID, userID, orderDate, totalAmount, status, paymentMode,address);
				
				list.add(order);
				
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
