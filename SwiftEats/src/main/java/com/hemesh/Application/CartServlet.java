package com.hemesh.Application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hemesh.DAOImpl.MenuDAOImpl;
import com.hemesh.Model.Cart;
import com.hemesh.Model.CartItem;
import com.hemesh.Model.Menu;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet{
	
	private void addToCart(HttpServletRequest req,Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAOImpl menuDao = new MenuDAOImpl();
		
		Menu menu = menuDao.getMenu(itemId);
		
		String name = menu.getItemName();
		
		int restaurantID = menu.getRestaurantId();
		
		String imagePath = menu.getImagePath();
		
		int price = menu.getPrice();
		
		HttpSession session = req.getSession();
		
		session.setAttribute("restaurantId", menu.getRestaurantId());
		
		CartItem cartItem = new CartItem(itemId, restaurantID, name, quantity, price, imagePath);
		
		cart.addCartItem(cartItem);

	}
	
	private void updateCartItem(HttpServletRequest req,Cart cart) {
		int id = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		cart.updateCartItem(id, quantity);
	}
	
	private void removeCartItem(HttpServletRequest req, Cart cart) {
		int id = Integer.parseInt(req.getParameter("itemId"));
		cart.deleteCartItem(id);

	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Cart cart = (Cart)session.getAttribute("cart");
		int oldRestaurantId;
		
		if(session.getAttribute("restaurantId")!=null) {
		 oldRestaurantId = (int)(session.getAttribute("restaurantId"));
		}
		else {
			 oldRestaurantId = 0; 
		}
		
		int newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		if(cart==null || oldRestaurantId!=newRestaurantId) {
			cart = new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("restaurantId", newRestaurantId);
		}
		
		String action = req.getParameter("action");
		if(action.equals("add")) {
			addToCart(req, cart);
		}
		else if(action.equals("update")) {
			updateCartItem(req, cart);
		}
		else if(action.equals("Remove")) {
			removeCartItem(req, cart);
		}

		resp.sendRedirect("Cart.jsp");
	}
}
