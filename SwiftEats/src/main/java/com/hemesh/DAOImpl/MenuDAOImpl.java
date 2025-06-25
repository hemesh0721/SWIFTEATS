package com.hemesh.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hemesh.Connection.Connections;
import com.hemesh.DAO.MenuDAO;
import com.hemesh.Model.Menu;

public class MenuDAOImpl implements MenuDAO{

	@Override
	public boolean addMenu(Menu menu) {
		try {
			Connection conn = Connections.getConnection();
			String query = "insert into menu(restaurantId,itemName,description,price,isAvailable,imagePath) values(?,?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setInt(4, menu.getPrice());
			pstmt.setString(5, menu.getIsAvailable());
			pstmt.setString(6, menu.getImagePath());
			int n =pstmt.executeUpdate();
			if(n==1) {
				return true;
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public void updateMenu(int restaurantId,String isAvailable) {
		
		try {
			Connection conn = Connections.getConnection();
			String query = "UPDATE menu SET isAvailable = ? WHERE restaurantId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, isAvailable);
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
	public void deleteMenu(int restaurantId) {
		
		try {
			Connection conn = Connections.getConnection();
			String query = "delete from menu WHERE restaurantId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, restaurantId);

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
	public Menu getMenu(int menuId) {
		
		
		try {
			Connection conn = Connections.getConnection();
			String query = "select * from menu WHERE menuId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, menuId);

			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				int menuID=res.getInt(1);
				int restaurantId = res.getInt(2);
				String name = res.getString(3);
				String description = res.getString(4);
				int price = res.getInt(5);
				String isAvailable=res.getString(6);
				String imagePath = res.getString(7);
				Menu menu = new Menu(menuID, restaurantId, name, description, price, isAvailable, imagePath);
				 return menu;
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
	
	public List<Menu> getAllMenus(int restaurantId){
		
		List<Menu> menus = new ArrayList<>();
		
		try {
			Connection conn = Connections.getConnection();
			String query = "select * from menu WHERE restaurantId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, restaurantId);

			 ResultSet res = pstmt.executeQuery();
			 while(res.next()) {
				 int menuId = res.getInt(1);
				 int resId = res.getInt(2);
				 String item = res.getString(3);
				 String desc = res.getString(4);
				 int price = res.getInt(5);
				 String isAvailable = res.getString(6);
				 String imagePath = res.getString(7);
				 
				 Menu menu = new Menu(menuId, resId, item, desc, price, isAvailable, imagePath);
				 
				 menus.add(menu);
				 
			 }
			
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return menus;
		
	}

	

}
