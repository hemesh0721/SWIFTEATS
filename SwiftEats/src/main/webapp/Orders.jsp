<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.hemesh.Model.Orders,java.util.List,com.hemesh.Model.Restaurant,com.hemesh.DAOImpl.RestaurantDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Orders.css">
<title>Orders</title>
</head>
<body>

	<header class="header">
		<div class="logo">
			<a class="anchor" href="home"> <h1>SWIFTEATS</h1> </a>
		</div>
		<div class="menu">
			<a href=""><img src="Images/search.jpg">Search</a>
			<a href=""><img src="Images/offer.png">Offers</a>
			<a href=""><img src="Images/help.png">Help</a>
			<a href="SignIn.jsp"><img src="Images/user.png"><%
			try{
			if((boolean)(session.getAttribute("Invalid"))== true){
				%>
				<%= session.getAttribute("Username") %>
			<%} 
			
			else{%>
			Sign In
			<%} 
			}
			catch(Exception e){
			e.printStackTrace();
			}%></a>
			<a href="Cart.jsp"><img src="Images/cart.png">Cart</a>
		</div>
	</header>
	<h3 style="text-align:center">YOUR ORDERS</h3>
	<div class="Order-Container">
		
		<%
		RestaurantDAOImpl res = new RestaurantDAOImpl();
		List<Orders> orders = (List<Orders>)session.getAttribute("Orders");
		try{
		if(!orders.isEmpty()){
		for(Orders order:orders){
			Restaurant restaurant = res.getRestaurant(order.getRestaurantId());%>
			<div class="Order">
				<div class="Order-Image">
				<img style="width:100px;height:100px;border-radius:10px" src= <%=restaurant.getImagePath() %>>
				</div>
				<div class="Order-left">
					<p style="font-size:15px;"><%=restaurant.getName() %></p>
					<p><%=order.getAddress() %></p>
					<p>#<%=order.getOrderId() %> | <%= order.getOrderDate() %></p>
					<br>
					
				</div>
				<div class="Order-right">
					<p>Ordered on <%=order.getOrderDate()%></p>
					<br><br><br>
					<div class="View"><a style="font-size:15px;" href="OrderItems?orderId=<%= order.getOrderId()%>">View Order</a></div>
				</div>
				
			</div>
			<hr style=" margin-left:5%; width: 85%; height: 0.5px; background-color: #e6e6e6; border: none;">	
		<%}
		}
		else{
		%>
		<p style="text-align:center;font-size:20px;">You have no orders</p>
		<%}
		}catch(Exception e){
			e.printStackTrace();
		}%>
		
	</div>
	
</body>
</html>