<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String tmp = request.getParameter("cars");
	String carname = new String(tmp.getBytes("8859_1"), "UTF-8");
%>
<html>
<head>
<title><%= carname %></title>
</head>
<body>
<center>
<h2><%= carname %></h2>
<%= carname %>
のお買い上げありがとうございました。<br/>
</center>
</body>
</html>

