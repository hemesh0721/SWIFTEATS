package com.hemesh.Application;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hemesh.DAOImpl.MenuDAOImpl;
import com.hemesh.DAOImpl.RestaurantDAOImpl;
import com.hemesh.Model.Menu;
import com.hemesh.Model.Restaurant;

@WebServlet("/menu")
public class MenuAddServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MenuDAOImpl menuDAO = new MenuDAOImpl();
		
		
		int id = Integer.parseInt(req.getParameter("restaurantId"));
		
		RestaurantDAOImpl res = new RestaurantDAOImpl();
		
		Restaurant restaurant = res.getRestaurant(id);
		
		float rating = restaurant.getRating();
		
		String restaurantName = restaurant.getName();
		
		String deliveryTime = restaurant.getDeliveryTime();

		List<Menu> menus = menuDAO.getAllMenus(id);
		
		req.setAttribute("Menus",menus);
		
		req.setAttribute("Restaurant",restaurantName);
		
		req.setAttribute("Rating", rating);
		
		req.setAttribute("Time", deliveryTime);
		
		RequestDispatcher rd = req.getRequestDispatcher("Menu.jsp");
		
		rd.forward(req,resp);
		
		
	}
}
