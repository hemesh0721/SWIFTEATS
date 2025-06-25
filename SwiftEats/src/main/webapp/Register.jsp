<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>SIGN UP</title>
</head>
<body>
<h1><a class="logo" href="home">SWIFTEATS</a></h1>

<div class="div2">
<h2>Sign Up</h2>
	<form action="registration">
	<label>Username:</label><br><br>
	<input type="text" name="username" class="input" required><br><br>
	<label>Password:</label><br><br>
	<input type="password" name="password" class="input" required><br><br>
	<label>Email:</label><br><br>
	<input type="email" name="email" class="input" required><br><br>
	<label>Address</label><br><br>
	<input type="text" name="address" class="input" required><br><br>
	<label>Role</label><br><br>
	<input type="text" name="role" class="input" required><br><br>
	<input type="submit" class="submit" value="Sign Up"><br><br>
	<%
	try{if(session.getAttribute("Success").equals("No")){ %>
		<p>Sorry, username is already taken! Try different username.</p>
	<%} 
	}
	catch(Exception e){
	e.printStackTrace();}%>
	</form>
	</div>
</body>
</html>