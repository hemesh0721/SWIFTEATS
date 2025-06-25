<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.hemesh.Model.Restaurant,java.util.List" %>
<html>
<head>
<meta charset="UTF-8">
<title>SWIFTEATS</title>
<link rel="stylesheet" href="Home.css">
</head>
<body>
	<header class="header">
		<div class="logo">
			<h1>SWIFTEATS</h1>
		</div>
		<div class="menu">
			<a href=""><img src="Images/search.jpg">Search</a>
			<a href=""><img src="Images/offer.png">Offers</a>
			<a href=""><img src="Images/help.png">Help</a>
			<a href="SignIn.jsp"><img src="Images/user.png">
			<%
			if((boolean)(session.getAttribute("Invalid"))== true){
				%>
				<%= session.getAttribute("Username") %>
			<%} 
			
			else{%>
			Sign In
			<%} %>
			</a>
			<a href="Cart.jsp"><img src="Images/cart.png">Cart</a>
		</div>
	</header>
	<h2>Top Restaurants in Anantapur....</h2>
	<div class="restaurant-container">
	<% 
	List<Restaurant> restaurants = (List)request.getAttribute("Restaurants");
	for(Restaurant restaurant:restaurants){
	%>
		<div class="restaurant">
		<a class="anchor" href="menu?restaurantId=<%= restaurant.getRestaurantId() %>"
		>
		<img src="<%= restaurant.getImagePath() %>" >
		<h3><%= restaurant.getName() %></h3>
		<p><%= restaurant.getCuisineType() %></p>
		<p><img style="height:20px;width:20px;vertical-align: middle;" src="Images/star.png"> <b><%=restaurant.getRating() %></b> <span><%=restaurant.getDeliveryTime() %></span></p>
		
		</a>
		</div>

	<%
	}
	%>
	</div>
</body>
</html>