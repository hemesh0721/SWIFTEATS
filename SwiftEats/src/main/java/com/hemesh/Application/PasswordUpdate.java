package com.hemesh.Application;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hemesh.DAOImpl.UserDAOImpl;
import com.hemesh.Model.User;

@WebServlet("/updatePassword")
public class PasswordUpdate extends HttpServlet{
	User user=null;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String username = req.getParameter("name");
		String password = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		UserDAOImpl userDao = new UserDAOImpl();
		
			user = userDao.getUser(username);
			if(user.getPassword().equals(password)) {
				int i = userDao.updateUser(user.getUsername(), newPassword);
				if(i==1) {out.println("Password Updated!");}
				else {out.println("Please check username and password!");
				}
				
			}
			else{
				out.println("Please check username and password!");
			}
			
		
	}
}
