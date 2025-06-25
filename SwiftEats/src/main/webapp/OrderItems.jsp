<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="com.hemesh.Model.Orders,java.sql.Date,com.hemesh.DAOImpl.MenuDAOImpl,com.hemesh.DAOImpl.OrderDAOImpl,com.hemesh.Model.Menu,com.hemesh.Model.OrderItem,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Orders</title>
<link rel="stylesheet" href="OrderItems.css">
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
	<div class="OrderItem-Container">
		<%	
			MenuDAOImpl menuDao = new MenuDAOImpl();
			OrderDAOImpl orderDao = new OrderDAOImpl();
			List<OrderItem> orderItems = (List<OrderItem>)session.getAttribute("OrderItems");
		try{
			if(!orderItems.isEmpty()){
				int totalAmount=0;
				Date date = null;
				for(OrderItem orderItem:orderItems){
					Menu menu = menuDao.getMenu(orderItem.getMenuId());
					Orders order = orderDao.getOrder(orderItem.getOrderId());%>
				<div class="OrderItem">
					<div class="OrderItem-Left">
						<p><%=menu.getItemName() %> </p>
						<p>Quantity: <%= orderItem.getQuantity() %></p>
						<% totalAmount+=orderItem.getTotalPrice(); %>
						<% date=order.getOrderDate(); %>
					</div>
					<div class="OrderItem-Right">
						<img style="width:75px;height:75px;border-radius:10px" src= <%=menu.getImagePath() %>>
					</div>
				</div>
				<hr style="width: 100%; height: 0.5px; background-color: #e6e6e6; border: none;">
		
			<%
				}
				%>
				<div class="Summary">
					<p style="text-align:center"><b>Total Summary</b></p><br>
					<p>Total Paid: ₹<b><%= totalAmount%></b></p>
					<p>Delivered on <b><%= date %></b>
				</div>
			<% }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
			%>
	</div>
</body>
</html>