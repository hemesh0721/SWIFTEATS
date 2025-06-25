package com.hemesh.Application;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hemesh.DAOImpl.RestaurantDAOImpl;
import com.hemesh.Model.Restaurant;


@WebServlet("/home")
public class HomeServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		RestaurantDAOImpl restaurant = new RestaurantDAOImpl();
		
		List<Restaurant> restaurants = restaurant.allRestaurants();
		
		HttpSession session = req.getSession();
		session.removeAttribute("confirm");
		boolean invalid = false;
		String username=null;
		try {
		invalid = (boolean)session.getAttribute("Invalid");
		username = (String)session.getAttribute("Username");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("Restaurants", restaurants);
		session.setAttribute("Username", username);
		session.setAttribute("Invalid", invalid);
		RequestDispatcher rd = req.getRequestDispatcher("Home.jsp");
		
		rd.forward(req, resp);
		
	}

}
