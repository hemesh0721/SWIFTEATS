package com.hemesh.DAO;

import java.util.List;

import com.hemesh.Model.User;

public interface UserDAO {
	
	public void addUser(User user) ;
	 
	 public int updateUser(String username,String newPassword);
	 
	 public void deleteUser(String username) ;
	 
	 public User getUser(String username) ;
	 
	 public List<User> getAllUsers() ;
	 

}
