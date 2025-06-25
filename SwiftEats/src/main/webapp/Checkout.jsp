<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Checkout.css">
</head>
<body>
	
	<%
    	boolean orderPlaced = false;
    	if(session.getAttribute("confirm") != null) {
        	orderPlaced = true;
        	session.removeAttribute("confirm"); 
    }
	%>

	<% if(orderPlaced) { %>
    	<div class="order-confirmation show-confirmation">
        	Your order has been confirmed
    	</div>
	<% } %>
	<div class="checkout">

	<form action="Checkout" >
		<label>Name</label><br><br>
		<input type="text" name="Name"><br><br>
		<label>Phone</label><br><br>
		<input type="tel" name="Phone"><br><br>
		<label>Address</label><br><br>
		<textarea name="Address" required></textarea><br><br>
		<label>Payment Mode</label><br><br>
		<select name="Payment" style="width:50%;height:30px;text-align:center;">
		<option value="Online">Credit Card</option>
		<option value="Online">Debit Card </option>
		<option value="Offline">Cash On Delivery </option>
		</select><br><br>
		<input type="submit" value="Place Order" style="cursor:pointer;">
		
	</form>
	</div>
</body>
</html>