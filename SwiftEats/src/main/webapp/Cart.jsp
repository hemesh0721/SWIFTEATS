<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.hemesh.Model.Cart,com.hemesh.Model.CartItem" %>
<html>
<head>
<meta charset="UTF-8">
<title>SWIFTEATS</title>
<link rel="stylesheet" href="Cart.css">
</head>
<body>
	<header class="header">
		<div class="logo">
			<h1><a class="anchor" href="home">SWIFTEATS</a></h1>
		</div>
		<div class="menu">
			<a href=""><img src="Images/search.jpg">Search</a>
			<a href=""><img src="Images/offer.png">Offers</a>
			<a href=""><img src="Images/help.png">Help</a>
			<a href="SignIn.jsp"><img src="Images/user.png">
			<%
			try{
			if((boolean)(session.getAttribute("Invalid"))== true){
				%>
				<%= session.getAttribute("Username") %>
			<%} 
			
			else{%>
			Sign In
			<%}
			}%><%catch(Exception e)
			{e.printStackTrace();} %></a>
			<a href="Cart.jsp"><img src="Images/cart.png">Cart</a>
		</div>
	</header>
	<div class="cart-container">
	<%
 
	Cart cart = (Cart) session.getAttribute("cart");
	if(cart != null && !cart.getItems().isEmpty()){
		for(CartItem cartitem:cart.getItems().values()){
	%>
	<div class="cart">
		<div class="cart-item">
			<h3><%= cartitem.getName() %></h3>
			<p>₹<%= cartitem.getPrice() %></p>
			<form action="cartServlet">
			<input type="submit" class="btn" name="action" value="Remove">
			<input type="hidden" name="itemId" value="<%= cartitem.getId() %>">
			<input type="hidden" name="restaurantId" value="<%= cartitem.getRestaurantId() %>">
			</form>
		</div>
		<div class="cart-img">
			<img src="<%= cartitem.getImagePath() %>" style="width:100px;height:100px;border-radius:10px">
			<form action="cartServlet" class="cart-quantity">
			<input type="hidden" name="restaurantId" value="<%= cartitem.getRestaurantId() %>">
			<input type="hidden" name="itemId" value="<%= cartitem.getId() %>">
			<button type="submit" name="action" value="update" class="quantity"> - </button>
			<input class="quan" name="quantity" value="<%= cartitem.getQuantity() %>" min=1>
			<button type="submit" name="action" value="add" class="quantity"> + </button>
			
			</form>
		</div>
		</div>
		<hr style=" margin-left:20%; width: 60%; height: 0.5px; background-color: #e6e6e6; border: none;">
		<%
			}
	}
		%>
	</div>
	<br><br>
	<%
	if(session.getAttribute("cart")!=null) 
	{
	%>
	<a href="menu?restaurantId=<%=session.getAttribute("restaurantId") %>"><input type="submit" style="margin-left:43%; width:15%;" class="more_items" value="Add More Items"></a>
	
	<form action="Checkout.jsp">
	<br><input type="submit" style="margin-left:43%; width:15%;" class="more_items" value="Checkout">
	</form>
	<%} %>
</body>
</html>