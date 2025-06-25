package com.hemesh.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hemesh.Connection.Connections;
import com.hemesh.DAO.RestaurantDAO;
import com.hemesh.Model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO{

	@Override
	public void addRestaurant(Restaurant restaurant) {
		try {
			Connection conn = Connections.getConnection();
			String query = "insert into restaurant(name,cuisineType,deliveryTime,AdminUserId,Rating,IsActive,ImagePath) values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getCuisineType());
			pstmt.setString(3, restaurant.getDeliveryTime());
			pstmt.setInt(4, restaurant.getAdminUserId());
			pstmt.setFloat(5, restaurant.getRating());
			pstmt.setString(6, restaurant.getIsActive());
			pstmt.setString(7, restaurant.getImagePath());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateRestaurant(String isActive,int restaurantId) {
		try {
			Connection conn = Connections.getConnection();
			String query = "update restaurant set isActive=? where restaurantId=?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,isActive);
			pstmt.setInt(2, restaurantId);
			
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
	public void deleteRestaurant(int id) {
		
		try {
			Connection conn = Connections.getConnection();
			String query = "delete from restaurant where restaurantId=?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
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
	public Restaurant getRestaurant(int id) {
		try {
			Connection conn = Connections.getConnection();
			String query ="select * from restaurant where restaurantId=?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			ResultSet res = pstmt.executeQuery();
			
			if(res.next()) {
				int restaurantId = res.getInt(1);
				String name = res.getString(2);
				String cuisine = res.getString(3);
				String deliveryTime = res.getString(4);
				int adminUserId = res.getInt(5);
				float rating = res.getFloat(6);
				String isActive = res.getString(7);
				String imagePath = res.getString(8);
				
				Restaurant restaurant = new Restaurant(restaurantId,name,cuisine,deliveryTime,adminUserId,rating,isActive,imagePath);
				
				return restaurant;
				
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
	public List<Restaurant> allRestaurants() {
		List<Restaurant> restaurants = new ArrayList<>();
		try {
			Connection conn = Connections.getConnection();
			String query ="select * from restaurant;";
			Statement stmt = conn.createStatement();		
			ResultSet res = stmt.executeQuery(query);
			
			while(res.next()) {
				int restaurantId = res.getInt(1);
				String name = res.getString(2);
				String cuisine = res.getString(3);
				String deliveryTime = res.getString(4);
				int adminUserId = res.getInt(5);
				float rating = res.getFloat(6);
				String isActive = res.getString(7);
				String imagePath = res.getString(8);
				
				Restaurant restaurant = new Restaurant(restaurantId,name,cuisine,deliveryTime,adminUserId,rating,isActive,imagePath);
				restaurants.add(restaurant);
				
				
				
			}
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return restaurants;
	}

}
