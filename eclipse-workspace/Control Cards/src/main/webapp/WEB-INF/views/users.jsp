<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" contentType="text/html; charset=utf-8"   language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
	<title>Список исполнительных лиц | Контроль исполнительной дисциплины</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10">
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css" />">
	<link rel="icon" href="<c:url value="/resources/img/favicon.ico" />" type= "image/x-icon"> 
	<link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />" type="image/x-icon">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/users.js" />"></script>
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
							<img src="<	c:url value='/resources/img/menu/add.png' />" alt="Добавить карточку" title="Добавить карточку" />
						</a>
						<p><a href="<c:url value="add" />">Добавить</a></p>
					</li>
					<li class="with_img users ${sessionJSP.login}">
						<a href="<c:url value="users" />">
							<img src="<	c:url value='/resources/img/menu/admin_active.png' />" alt="Исполнительные лица" title="Исполнительные лица">
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
	<div class="section users">
		<div class="container">
			<h3 class="name">Добавить исполнительное лицо</h3>
			<table class="sortable add">
				<thead>
					<tr>
						<th class="headAddEXEC_USER nosort"><h3>ФИО исполнителя</h3></th><!-- <a href="" onclick="event.preventDefault(); document.getElementById('sort_mode').value='name_doc_ASC'; document.getElementById('valid-select').submit();"></a> -->
						<th class="headAddEXEC_CTRL_FACE_FLAG nosort"><h3>Контрольное лицо</h3></th>
						<th class="headAddEXEC_EXECUTOR_FLAG nosort"><h3>Исполнительное лицо</h3></th>
						<th class="headAddButton nosort last"><h3></h3></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="addEXEC_USER"><input type="text" id="addEXEC_USER" maxlength="256"></td>
						<td class="addEXEC_CTRL_FACE_FLAG"><input type="checkbox" id="addEXEC_CTRL_FACE_FLAG"></td>
						<td class="addEXEC_EXECUTOR_FLAG"><input type="checkbox" id="addEXEC_EXECUTOR_FLAG"></td>
						<td><button type="button" class="btn-class" id="addExecutor">Добавить</button></td>
					</tr>
				</tbody>
			</table>
			<h3 class="name">Поиск</h3>
			<table class="sortable add">
				<thead>
					<tr>
						<th class="headSearchEXEC_USER nosort"><h3>ФИО исполнителя</h3></th><!-- <a href="" onclick="event.preventDefault(); document.getElementById('sort_mode').value='name_doc_ASC'; document.getElementById('valid-select').submit();"></a> -->
						<th class="headSearchButton nosort last"><button type="button" class="btn-class" id="canselExecutor">Сбросить поиск</button></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="searchEXEC_USER"><input type="text" id="searchEXEC_USER" maxlength="256"></td>
						<td>
							<button type="button" class="btn-class" id="searchExecutor">Найти</button>
						</td>
					</tr>
				</tbody>
			</table>
			<h3 class="name">Список исполнительных лиц (автоматическое изменение)</h3>
			<input id="field_name" value="${field_nameJSP}" type="hidden" />
			<table id="result" class="sortable">
				<thead>
					<tr>
						<th class="headEXEC_ID"></th>
						<th class="headEXEC_USER asc" id="headEXEC_USER"><h3>ФИО исполнителя</h3></th><!-- <a href="" onclick="event.preventDefault(); document.getElementById('sort_mode').value='name_doc_ASC'; document.getElementById('valid-select').submit();"></a> -->
						<th class="headEXEC_CTRL_FACE_FLAG" id="headEXEC_CTRL_FACE_FLAG"><h3>Контрольное лицо</h3></th>
						<th class="headEXEC_EXECUTOR_FLAG last" id="headEXEC_EXECUTOR_FLAG"><h3>Исполнительное лицо</h3></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>