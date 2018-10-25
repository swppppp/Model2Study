<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
<h2>메세지: ${requestScope.message }</h2><br>

<h2>팀 이름 리스트</h2>
<ul>
 <c:forEach items="${list }" var="team">
  <li>${team }</li>
 </c:forEach>
</ul>
</body>
</html>