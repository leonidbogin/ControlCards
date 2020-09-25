<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" contentType="text/html; charset=utf-8"   language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Добавить карточки | Контроль исполнительной дисциплины</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10">
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css" />">
	<link rel="icon" href="<c:url value="/resources/img/favicon.ico" />" type= "image/x-icon"> 
	<link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />" type="image/x-icon">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/add.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-1.12.4.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>	
	<script src="<c:url value="/resources/js/datepicker-ru.js" />"></script>
</head>
<body>
	<div class="section header">
		<div class="container">	
			<div class="logo"> 
				<a href="<c:url value="/" />"><img src="<c:url value=	"/resources/img/logo.png" />" alt="" title=""></a>
				<a class="logo_text" href="<c:url value="/" />">Контроль исполнительной дисциплины</a>
			</div>	
			<nav>	
				<input name="search_open" id="search_open" value="${sessionJSP.search_open}" type="hidden" />
				<ul class="list-unstyled">
					<li class="with_hide_img">
						<a href="<c:url	value="valid" />" class="a-valid"> 
							<img src="<	c:url value='/resources/img/menu/valid.png' />" alt="Действующие карточки" />
						</a>
						<p><a href="<c:url	value="valid" />" class="a-valid">Действующие карточки</a></p>
					</li>
					<li class="with_hide_img">
						<a href="<c:url value="deleted" />" class="a-deleted">
							<img src="<	c:url value='/resources/img/menu/deleted.png' />" alt="Снятые карточки" />
						</a>
						<p><a href="<c:url value="deleted" />" class="a-deleted">Снятые карточки</a></p>
					</li>
					<li class="with_img add ${sessionJSP.login}">
						<a href="<c:url value="add" />">
							<img src="<	c:url value='/resources/img/menu/add_active.png' />" alt="Добавить карточку" title="Добавить карточку" />
						</a>
						<p><a href="<c:url value="add" />">Добавить</a></p>
					</li>
					<li class="with_img users ${sessionJSP.login}">
						<a href="<c:url value="users" />">
							<img src="<	c:url value='/resources/img/menu/admin.png' />" alt="Исполнительные лица" title="Исполнительные лица">
						</a>
						<p><a href="<c:url value="users" />">Админ панель</a></p>
					</li>
					<li class="with_img login ${sessionJSP.login}">
						<form id="loginForm" action="login" method="POST" style="display: none;"></form>
						<a href="" onclick="event.preventDefault(); document.getElementById('loginForm').submit();">
							<img src="<	c:url value='/resources/img/menu/login.png' />" alt="Авторизация администратора" title="Войти">
						</a>
						<p><a href="" onclick="event.preventDefault(); document.getElementById('loginForm').submit();">Войти</a></p>
					</li>
					<li class="with_img exit ${sessionJSP.login}">
						<a href="" onclick="event.preventDefault(); document.getElementById('loginForm').submit();">
							<img src="<	c:url value='/resources/img/menu/login.png' />" alt="Выйти из аккаунта" title="Выйти из аккаунта">
						</a>
						<p><a href="" onclick="event.preventDefault(); document.getElementById('loginForm').submit();">Выйти</a></p>
					</li>
				</ul>	
			</nav>	
		</div>
	</div>
	<div class="section add">
		<div class="container">
			<div id="errors" class="errors">
			</div>
			<h3>Добавление новой карточки</h3>
			<table>
				<tr><td class="name">Наименование документа</td><td><textarea rows="3" id="addNAME_DOC" class="input-textarea" placeholder="Наименование документа" maxlength="256"></textarea></td></tr>
				<tr><td class="name">Описание документа</td><td><textarea rows="3" id="addDESC_DOC" class="input-textarea" placeholder="Описание документа" maxlength="256"></textarea></td></tr>
				<tr><td class="name">Содержание поручения</td><td><textarea rows="6" id="addMISSION" class="input-textarea" placeholder="Содержание поручения" maxlength="512"></textarea></td></tr>
				<tr><td class="name">Контролирующее лицо</td><td><input autocomplete="off" id="addCTRL_FACE" list="data-ctrls" type="text" class="input-text" placeholder="Контролирующее лицо" maxlength="256" /></td></tr>
				<tr><td class="name executors">Исполнители</td>
					<td>
						<table class="executors">
							<tr>
								<td><input autocomplete="off" id="addEXECUTOR_1" placeholder="Исполнитель 1" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
								<td><input autocomplete="off" id="addEXECUTOR_2" disabled placeholder="Исполнитель 2" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
								<td><input autocomplete="off" id="addEXECUTOR_3" disabled placeholder="Исполнитель 3" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
								<td class="last"><input autocomplete="off" id="addEXECUTOR_4" disabled placeholder="Исполнитель 4" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td class="name">Поставлена на контроль</td>
					<td>
						<input autocomplete="off" id="addDATE_EXEC" type="text" class="input-date" placeholder="Поставлена на контроль" maxlength="10" />
					</td>
				</tr>
				<tr><td class="name">Срок выполнения</td>
					<td>
						<input autocomplete="off" id="addDATE_CTRL" type="text" class="input-date" placeholder="Срок выполнения" maxlength="10" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="button" class="buttonAdd btn-class" onclick="Add();">Добавить новую карточку</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<datalist id="data-ctrls"> 
		<c:forEach items="${executorsExecJSP}" var="Executor">
        	<option class="exec${Executor.id}" value="${Executor.name}" label="${Executor.name}" />
        </c:forEach>
	</datalist>	
	<datalist id="data-executors"> 
		<c:forEach items="${executorsCtrlJSP}" var="Executor">
        	<option class="exec${Executor.id}" value="${Executor.name}" label="${Executor.name}" />
        </c:forEach>
	</datalist>
</body>
</html>