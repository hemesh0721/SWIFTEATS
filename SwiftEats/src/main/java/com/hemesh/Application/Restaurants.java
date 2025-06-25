package com.hemesh.Application;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hemesh.DAOImpl.RestaurantDAOImpl;
import com.hemesh.Model.Restaurant;

@WebServlet("/restaurants")
public class Restaurants extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RestaurantDAOImpl resDAO = new RestaurantDAOImpl();
		PrintWriter out = resp.getWriter();
		String restaurantName = req.getParameter("name");
		String cuisine = req.getParameter("cuisine");
		String deliveryTime=req.getParameter("delivery");
		int adminId = Integer.parseInt(req.getParameter("admin"));
		float rating = Float.parseFloat(req.getParameter("rating"));
		String isActive = req.getParameter("isActive");
		String imagePath = req.getParameter("imagepath");
		
		Restaurant restaurant = new Restaurant(restaurantName, cuisine, deliveryTime, adminId, rating, isActive, imagePath);
		
		resDAO.addRestaurant(restaurant);
		
		out.println("Restaurant Added successfully!");
	}
}
