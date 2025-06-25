package com.hemesh.Application;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hemesh.DAOImpl.OrderDAOImpl;
import com.hemesh.Model.Orders;

@WebServlet("/Orders")
public class OrderServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userId = Integer.parseInt(req.getParameter("userId"));
		OrderDAOImpl orderDao = new OrderDAOImpl();
		List<Orders> list = orderDao.getAllOrders(userId);
		
		session.setAttribute("Orders",list);
		
		resp.sendRedirect("Orders.jsp");
		
		
	}
}
