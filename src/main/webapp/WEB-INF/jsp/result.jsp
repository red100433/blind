<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL</title>
</head>
<body>
<div id="header"> </div>
<h1>Result</h1>

  <c:forEach items="${menulist}" var="menu">
       <B><c:out value="${menu}" /></B>
       <br>
   </c:forEach>

</body>
<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script>
$("#header").load("/html/fragments/header.html");
</script>
</html>