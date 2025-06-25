package com.hemesh.Application;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hemesh.DAOImpl.OrderItemDAOImpl;
import com.hemesh.Model.OrderItem;

@WebServlet("/OrderItems")
public class OrderItems extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		OrderItemDAOImpl orderItems = new OrderItemDAOImpl();
		
		int orderId = Integer.parseInt(req.getParameter("orderId"));
		List<OrderItem> list = orderItems.getOrderItems(orderId);
		
		session.setAttribute("OrderItems", list);
		resp.sendRedirect("OrderItems.jsp");
	}
}
