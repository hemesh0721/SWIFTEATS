package com.hemesh.DAO;

import java.util.List;

import com.hemesh.Model.Menu;

public interface MenuDAO {
	
	public boolean addMenu(Menu menu) ;
	
	public void updateMenu(int restaurantId,String isAvailable) ;
	
	public void deleteMenu(int id) ;
	
	public  Menu getMenu(int id) ;
	
	public List<Menu> getAllMenus(int id);

}
