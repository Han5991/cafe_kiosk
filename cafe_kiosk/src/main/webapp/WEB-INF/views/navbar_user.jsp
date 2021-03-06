<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<style>
@font-face {
	font-family: hzStyleFont;
	src: url("<spring:url value='/resources/font/ImcreSoojin.ttf'/>");
}

* {
	font-family: hzStyleFont;
	font-size: 25px;
}

#navbar {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #D9CDBC;
}

#navbar>li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

#navbar>li a:hover {
	background-color: #333;
}

#navbar>li {
	float: left;
	text-align: left;
	padding: 5px;
}

body {
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<nav>
		<ul id="navbar">
			<li><a href="#">메뉴 선택</a></li>
			<li><a href="#">장바구니</a></li>
			<li><a href="#">결제 정보</a></li>
			<li><a href="#">영수증 출력</a></li>
			<li><a href="#">결제 완료</a></li>
		</ul>
	</nav>
</body>
</html>