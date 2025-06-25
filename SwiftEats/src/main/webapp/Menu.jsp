<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ page import="java.util.List,com.hemesh.Model.Menu,com.hemesh.Model.Cart" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SWIFTEATS</title>
<link rel="stylesheet" href="Menu.css">
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
	<h2 style="text-align:center"><%= request.getAttribute("Restaurant") %></h2><br><br>
	<div class="Menu-Container">
		<%
		List<Menu> menus = (List<Menu>) request.getAttribute("Menus");
		for(Menu menu:menus){
	%>
		
	
	<div class="Menu">
	
	<div class="Menu-Item">
	
		<h3><%= menu.getItemName() %></h3>		
		<p><b>₹ <%= menu.getPrice() %></b></p>
		<p style="font-size:15px;"><img style="vertical-align: middle;height:15px;width:15px;" src="Images/star.png"> <%= request.getAttribute("Rating") %></p>
		<p><%=request.getAttribute("Time") %></p>
		<p style="font-size:14px"><%= menu.getDescription() %></p>	
	</div>
	<div >
	<img class="Menu-Image" style="width:150px;height:150px;border-radius:10px" src="<%= menu.getImagePath() %>" alt="Menu">
		<form action="cartServlet">
			<input type="hidden" name="itemId" value="<%= menu.getMenuId()%>">
			<input type="hidden" name="restaurantId" value="<%=menu.getRestaurantId() %>">
			<input type="hidden" name="price" value="<%= menu.getPrice() %>">
			<input type="hidden" name="quantity" value="1">
			<input type="hidden" name="image" value="<%=menu.getImagePath() %>">
			<input type="hidden" name="action" value="add">
			<input type="submit" class="btn" value="Add to Cart">
		</form>
	
	</div>
	</div>

		<hr style="margin-left:10%; width: 80%; height: 0.5px; background-color: #e6e6e6; border: none;">
		
	<%
	}
	%>
	</div>
	
</body>
</html>