package com.hemesh.Application;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hemesh.DAOImpl.OrderDAOImpl;
import com.hemesh.DAOImpl.OrderItemDAOImpl;
import com.hemesh.Model.Cart;
import com.hemesh.Model.CartItem;
import com.hemesh.Model.OrderItem;
import com.hemesh.Model.Orders;
import com.hemesh.Model.User;


@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		User user = (User)session.getAttribute("User");
		OrderDAOImpl orderDaoImpl = new OrderDAOImpl();
		OrderItemDAOImpl orderItemDAOImpl = new OrderItemDAOImpl();
		
		if(cart!=null && user!=null && !cart.getItems().isEmpty()) {
			String paymentMode = req.getParameter("Payment");
			
			Orders order = new Orders();
			LocalDate current = LocalDate.now();
			Date date = Date.valueOf(current);
			order.setUserId(user.getUserId());
			order.setRestaurantId((int)session.getAttribute("restaurantId"));
			order.setOrderDate(date);
			order.setStatus("Pending");
			order.setPaymentMode(paymentMode);
			
			int totalAmount=0;
			for(CartItem item : cart.getItems().values()) {
				totalAmount = item.getPrice()*item.getQuantity();
				
			}
			order.setTotalAmount(totalAmount);
			
			int orderId = orderDaoImpl.addOrder(order);
			for(CartItem item : cart.getItems().values()) {
				int menuId = item.getId();
				int quantity = item.getQuantity();
				int totalprice = item.getPrice()*item.getQuantity();
				
				OrderItem orderItem = new OrderItem(orderId, menuId, quantity, totalprice);
				orderItemDAOImpl.addOrderItem(orderItem);
			}
			session.removeAttribute("Cart");
			session.setAttribute("Order", order);
			
			String confirmation = "Confirmed";
			session.setAttribute("confirm", confirmation);
			resp.sendRedirect("Checkout.jsp");
			
		}
		else if(user==null) {
			String userr = "NOT";
			session.setAttribute("FromCheckout", userr);
			resp.sendRedirect("SignIn.jsp");
		}
		else {
			resp.sendRedirect("Cart.jsp");
		}
	}
}
