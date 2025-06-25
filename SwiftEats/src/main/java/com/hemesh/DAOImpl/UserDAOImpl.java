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
import com.hemesh.DAO.UserDAO;
import com.hemesh.Model.User;

public class UserDAOImpl implements UserDAO{
	
	
	
	@Override
	public void addUser(User user) {
		try {
		Connection conn = Connections.getConnection();
		String query = "insert into users(username,password,email,address,role,createDate,lastLoginDate) values(?,?,?,?,?,?,?);";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getEmail());
		pstmt.setString(4, user.getAddress());
		pstmt.setString(5, user.getRole());
		pstmt.setDate(6, user.getCreateDate());
		pstmt.setDate(7, user.getLastLoginDate());
		
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
	public int updateUser(String username,String newPassword){
		int i=0;
		try {
			Connection conn = Connections.getConnection();
			String query = "UPDATE users SET Password = ? WHERE Username = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, newPassword);
	        pstmt.setString(2, username);

			i = pstmt.executeUpdate();
			
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public void deleteUser(String  username) {
		
		try {
			Connection conn = Connections.getConnection();
			String query = "delete from users where username="+username+";";
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
	public User getUser(String username) {
		try {
			Connection conn = Connections.getConnection();
			String query = "select * from users where Username='"+username+"';";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			if(res.next()) {
				int userid = res.getInt(1);
				String un = res.getString(2);
				String pwd = res.getString(3);
				String email = res.getString(4);
				String address = res.getString(5);
				String role = res.getString(6);
				Date created=res.getDate(7);
				Date lastLogin = res.getDate(8);
				
				User user = new User(userid,un,pwd,email,address,role,created,lastLogin);
				return user;
				
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
	public List<User> getAllUsers(){
		List<User> userList = new ArrayList<>();
		try {
			
			
			Connection conn = Connections.getConnection();
			String query = "select * from users;";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			while(res.next()) {
				int userid = res.getInt(1);
				String un = res.getString(2);
				String pwd = res.getString(3);
				String email = res.getString(4);
				String address = res.getString(5);
				String role = res.getString(6);
				Date created=res.getDate(7);
				Date lastLogin = res.getDate(8);
				
				User user = new User(userid,un,pwd,email,address,role,created,lastLogin);
				userList.add(user);
				
			}
			
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
}
