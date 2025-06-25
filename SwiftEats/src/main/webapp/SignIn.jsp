<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.hemesh.Model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In</title>
<link rel="Stylesheet" href="style.css">
</head>
<body>
<h1><a class="logo" href="home">SWIFTEATS</a></h1>

<div class="div3">
<%try{ %>
<% if(session.getAttribute("User")==null){ %>
	<form action="signIn">
	<%String fromCheckout = (String) session.getAttribute("FromCheckout");
	if("NOT".equals(fromCheckout)) {%>
	<h3 style="text-align:center">Please Sign-in to place your order.</h3><br>
	<%} %>
	
		<label>Username</label><br><br>
		<input type="text" name="Username" class="input"><br><br>
		<label>Password</label><br><br>
		<input type="password" name="Password" class="input"><br><br>
		<input type="submit" class="submit" value="Sign In"><br><br>
		<p style="padding-left:13%;">Do not have an account? <a href="Register.jsp"><u>Sign Up</u></a></p>
	</form>
	<%} 
	else{
		User user = (User) session.getAttribute("User");%>
	
	<div class="Orders"><a href="Orders?userId=<%=user.getUserId() %>">Your Orders</a></div>
	<div class="SignOut"><a href="Signout?user=<%= user%>">Sign Out</a></div>
	<div class="Password"><a href="PasswordUpdate.html">Change Password?</a></div>
	<%} %>
	
	<%}
		catch(Exception e){e.printStackTrace();}%>
	
</div>
</body>
</html>