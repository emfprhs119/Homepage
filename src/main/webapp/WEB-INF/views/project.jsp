<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" language="java"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>

<head>
<title>${title}</title>
<c:forEach var="cssData" items="${cssList}">
	<link rel="stylesheet" href=resources/css/${cssData}?var=13 type="text/css">
</c:forEach>
</head>

<body background="<c:url value='/resources/img/bg.jpg' />">
	<div id="page">${headerhtml}
		<div id="body">
			<div class="header">
				<div>
					<h1>Project</h1>
				</div>
			</div>
			<div class="blog">
				<div class="featured">
					<ul>
						<c:forEach var="project" items="${projectList}">
							<li><img src=${project.img } alt="">
								<div>
									<h1>${project.name}</h1>
									<span>${project.makeby}</span>
									<p>${project.describe}</p>
									<a href=${project.url } class="more">Read More</a>
								</div></li>
						</c:forEach>
					</ul>
					<a href="https://github.com/emfprhs119" class="load">Load More</a>
				</div>
				<div class="sidebar">
					<h1>Recent Project</h1>
					<img src=${projectList[0].img } alt="">
					<h1>${projectList[0].name}</h1>
					<span>${projectList[0].makeby}</span>
					<p>${projectList[0].describe}</p>
					<a href=${projectList[0].url } class="more">Read More</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>