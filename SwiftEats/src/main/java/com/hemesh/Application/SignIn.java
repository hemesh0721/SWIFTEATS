package com.hemesh.Application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hemesh.DAOImpl.UserDAOImpl;
import com.hemesh.Model.User;

@WebServlet("/signIn")
public class SignIn extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("Username");
		String password = req.getParameter("Password");
		
		User user=null;
		UserDAOImpl userDao = new UserDAOImpl();
		user = userDao.getUser(username);
		boolean invalid=false;
		if(user==null) {
			HttpSession session = req.getSession();
			session.setAttribute("Invalid", invalid);
			resp.sendRedirect("SignIn.jsp");
		}
		else if(!user.getPassword().equals(password)) {
			HttpSession session = req.getSession();
			session.setAttribute("Invalid", invalid);
			resp.sendRedirect("SignIn.jsp");
		}
		else if(user.getPassword().equals(password)) {
			invalid=true;
			HttpSession session = req.getSession();
			session.setAttribute("User", user);
			session.setAttribute("Invalid", invalid);
			session.setAttribute("Username", username);
			resp.sendRedirect("home");
		}
	}
}
