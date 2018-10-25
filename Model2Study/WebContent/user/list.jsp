<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
<h2>회원 목록 페이지입니다.</h2>

<table border="1px solid black">

  <tr>
    <td>NO.</td>
    <td>ID</td>
    <td>NAME</td>
    <td>PASS</td>
    <td>EMAIL</td>
  </tr>
  
<c:choose>
<c:when test="${not empty list }">
  <c:forEach items="${list }" var="user" varStatus="status">
  
    <tr>
      <td>${status.count }</td>
      <td>${user.id }</td>
      <td>${user.name }</td>
      <td>${user.passwd }</td>
      <td>${user.email }</td>
    </tr>
  </c:forEach>
</c:when>
<c:otherwise>
  <tr>
    <td colspan="5">회원이 존재하지 않습니다.</td>
  </tr>
</c:otherwise>
</c:choose>
</table>

</body>
</html>