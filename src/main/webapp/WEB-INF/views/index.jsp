<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" language="java"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%> 
<html>

<head>
<title>${title}</title>
<c:forEach var="cssData" items="${cssList}">
	<link rel="stylesheet" href=resources/css/${cssData}?a=5 type="text/css">
</c:forEach>
</head>

<body background="<c:url value='/resources/img/bg.jpg' />">
	<div id="page">${headerhtml} ${bodyhtml}</div>
</body>
</html>