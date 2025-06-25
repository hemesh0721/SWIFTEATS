package com.hemesh.Application;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hemesh.DAOImpl.MenuDAOImpl;
import com.hemesh.Model.Menu;

@WebServlet("/menuCard")
public class MenuServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		MenuDAOImpl menuDao = new MenuDAOImpl();
		
		int restaurantId = Integer.parseInt(req.getParameter("RestaurantId"));
		String item = req.getParameter("Item");
		String description = req.getParameter("Description");
		int price = Integer.parseInt(req.getParameter("Price"));
		String available = req.getParameter("Availability");
		String imagePath = req.getParameter("ImagePath");
		
		Menu menu = new Menu(restaurantId, item, description, price, available, imagePath);
		
		if(menuDao.addMenu(menu)) {
			out.println("Menu Item added successfully!");
		}
		else {
			out.println("Failed to add the item");
		}
		
	}
}
