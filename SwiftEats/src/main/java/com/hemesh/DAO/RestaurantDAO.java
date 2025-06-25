package com.hemesh.DAO;

import java.util.List;

import com.hemesh.Model.Restaurant;

public interface RestaurantDAO {
	
	public void addRestaurant(Restaurant restaurant);
	
	public void updateRestaurant(String isActive,int restaurantId);
	
	public void deleteRestaurant(int id);
	
	public Restaurant getRestaurant(int id);
	
	public List<Restaurant> allRestaurants();
}
	
	
