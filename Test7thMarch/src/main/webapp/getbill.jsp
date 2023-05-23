<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <% double finalAmount=(double)request.getAttribute("finalAmount"); %>
   <% String movieName=(String)request.getAttribute("movieName"); %>   
   <h1 style="color: green;">Movie Name:<%= movieName %></h1>
   <h1 style="color: green;">YOUR FINALLY Bill:<%= finalAmount %></h1>


</body>
</html>