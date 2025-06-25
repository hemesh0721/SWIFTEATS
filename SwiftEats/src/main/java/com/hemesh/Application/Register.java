package com.hemesh.Application;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hemesh.DAOImpl.UserDAOImpl;
import com.hemesh.Model.User;

@WebServlet("/registration")
public class Register extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String role = req.getParameter("role");
		LocalDate createDate = LocalDate.now();
		LocalDate loginDate = LocalDate.now(); 
		
		Date createdDate = Date.valueOf(createDate);
		Date lastLogin = Date.valueOf(loginDate);
		
		String success=null;
		
		
		
		
		UserDAOImpl userDao = new UserDAOImpl();
			
		User user = userDao.getUser(username);
		
		if(user==null) {
			user = new User(username, password, email, address, role, createdDate, lastLogin);
			userDao.addUser(user);
			out.println("You have succesfully registered");
		}
		else {
			success="No";
			HttpSession session =req.getSession();
			session.setAttribute("Success", success);
			resp.sendRedirect("Register.jsp");
		}
			
		
		
			
		
	}
}
