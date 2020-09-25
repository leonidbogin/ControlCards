<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=utf-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
	<title>Вход - Контроль исполнительной дисциплины</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10">
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/login.css" />" />
	
	<link rel="icon" href="<c:url value="/resources/img/favicon.ico" />" type= "image/x-icon"> 
	<link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />" type="image/x-icon">
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/login.js" />"></script>
</head>
<body>
	<div class="wrapper">
		<div class="content">
			<div class="container">
				<h1>Авторизация администратора</h1>
				<form:form method="POST" modelAttribute="loginJSP" action="login" class="login">
    				<fieldset>
      					<form:input type="text" path="login" AUTOCOMPLETE="off" placeholder="Логин"/>
      					<button>Войти</button>
      					<button type="button" class="back" onclick="document.location.href = '${sessionJSP.page_name}';">Назад</button>
      				</fieldset>	      			
      			</form:form>	
			</div>
			<div class="error-box error hide" id="error-box">
				<p id="error-text">${loginJSP.error}</p>
			</div>
		</div>
	</div>
</body>
</html>