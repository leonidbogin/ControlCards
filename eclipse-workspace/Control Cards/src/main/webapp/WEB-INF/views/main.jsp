<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<title>${page_textJSP} карточки | Контроль исполнительной дисциплины</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10">
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css" />">
	<link rel="icon" href="<c:url value="/resources/img/favicon.ico" />" type= "image/x-icon"> 
	<link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />" type="image/x-icon">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-1.12.4.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>	
	<script src="<c:url value="/resources/js/datepicker-ru.js" />"></script>
</head>
<body>
	<div class="wrapper">
		<div class="back"></div>
		<div class="section card">
			<div class="container">	
				<div class="modal-content">
					<div class="cont">
						<label class="modal-close" for="modal">&#10005;</label>
						<label class="modal-edit ${sessionJSP.login}" for="modal">Редактировать</label>
						<label class="modal-print ${sessionJSP.login}" for="modal">Печать</label>
						<label class="modal-cansel hide-me" for="modal">Отмена</label>
						<label class="modal-save hide-me" for="modal">Сохранить изменения</label>
						<form id="printCardForm" action="print-card-pdf" method="POST">
							<input name="watchCTRL_ID" id="watchCTRL_ID" type="hidden" />
						</form>
						<h3>Просмотр карточки</h3>
						<div id="errors" class="errors"></div>
						<table>
							<tr><td class="name textarea">Наименование документа</td><td><textarea rows="4" readonly id="watchNAME_DOC" class="input-textarea" placeholder="Наименование документа" maxlength="256"></textarea></td></tr>
							<tr><td class="name textarea">Описание документа</td><td><textarea rows="4" readonly id="watchDESC_DOC" class="input-textarea" placeholder="Описание документа" maxlength="256"></textarea></td></tr>
							<tr><td class="name textarea">Содержание поручения</td><td><textarea rows="8" readonly id="watchMISSION" class="input-textarea" placeholder="Содержание поручения" maxlength="512"></textarea></td></tr>
							<tr><td class="name">Контролирующее лицо</td><td><input readonly autocomplete="off" id="watchCTRL_FACE" list="data-ctrls" type="text" class="input-text" placeholder="Контролирующее лицо" maxlength="256" /></td></tr>
							<tr><td class="name executors">Исполнители</td>
								<td>
									<table class="executors">
										<tr>
											<td><input autocomplete="off" readonly id="watchEXECUTOR_1" placeholder="Исполнитель 1" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
											<td><input autocomplete="off" readonly id="watchEXECUTOR_2" placeholder="Исполнитель 2" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
											<td><input autocomplete="off" readonly id="watchEXECUTOR_3" placeholder="Исполнитель 3" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
											<td class="last"><input readonly autocomplete="off" id="watchEXECUTOR_4" placeholder="Исполнитель 4" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr><td class="name">Поставлена на контроль</td>
								<td>
									<input autocomplete="off" readonly id="watchDATE_EXEC" type="text" class="input-date" placeholder="Поставлена на контроль" maxlength="10" />
								</td>
							</tr>
							<tr><td class="name">Срок выполнения</td>
								<td>
									<input autocomplete="off" readonly id="watchDATE_CTRL" type="text" class="input-date" placeholder="Срок выполнения" maxlength="10" />
								</td>
							</tr>
							<tr><td class="name">Срок продления</td>
								<td>
									<button class="btn-class btnDATE_EXTENSION hide-me ${sessionJSP.login} ${sessionJSP.page_name}">Продлить срок</button>
									<div class="divDATE_EXTENSION ${sessionJSP.login} ${sessionJSP.page_name}">
										<input readonly autocomplete="off" id="watchDATE_EXTENSION" type="text" class="input-date" placeholder="Срок продления" maxlength="10" />
										<div class="btnsDATE_EXTENSION hide-me">
											<button class="btn-class goDATE_EXTENSION">ОК</button>
											<button class="btn-class canselDATE_EXTENSION">Отмена</button>
										</div>
									</div>
								</td>
							</tr>
							<tr><td class="name">Снята с контроля</td>
								<td>
									<button class="btn-class btnTAKE_OFF_CTRL hide-me ${sessionJSP.login}">Снять с контроля</button>
									<div class="divTAKE_OFF_CTRL ${sessionJSP.login}">
										<input readonly autocomplete="off" id="watchTAKE_OFF_CTRL" type="text" class="input-date" placeholder="Снята с контроля" maxlength="10" />
										<div class="btnsTAKE_OFF_CTRL hide-me">
											<button class="btn-class goTAKE_OFF_CTRL">ОК</button>
											<button class="btn-class canselTAKE_OFF_CTRL">Отмена</button>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div> 
			</div>
		</div>	
		<div class="section header">
			<div class="container">	
				<div class="logo"> 
					<a href="<c:url value="/" />"><img src="<c:url value="/resources/img/logo.png" />" alt="Логотип" title=""></a>
					<a class="logo_text" href="<c:url value="/" />">Контроль исполнительной дисциплины</a>
				</div>	
				<nav>	
					<input name="search_open" id="search_open" value="${sessionJSP.search_open}" type="hidden" />
					<ul class="list-unstyled">
						<li class="with_hide_img">
							<a href="<c:url	value="valid" />" class="a-valid"> 
								<img src="<	c:url value='/resources/img/menu/valid.png' />" alt="Действующие карточки" />
								<img src="<	c:url value='/resources/img/menu/valid_active.png' />" alt="Действующие карточки" class="image-active" />
							</a>
							<p><a href="<c:url	value="valid" />" class="a-valid">Действующие карточки</a></p>
						</li>
						<li class="with_hide_img">
							<a href="<c:url value="deleted" />" class="a-deleted">
								<img src="<	c:url value='/resources/img/menu/deleted.png' />" alt="Снятые карточки" />
								<img src="<	c:url value='/resources/img/menu/deleted_active.png' />" alt="Снятые карточки" class="image-active" />
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
		<div class="section all-search">
			<div class="container">	
				<div class="search-head search_click">
					<h3>Поиск ${page_text_smallJSP} карточек</h3>
					<img src="<c:url value="/resources/img/down_active.png" />" alt="Скрыть/показать поиск">
					<h4>Показать поиск</h4>
				</div>
				<div id="search" class="search">
					<form id="formPrint" action="print" method="POST">
						<input name="page_name" id="page_name" value="${sessionJSP.page_name}" type="hidden" />
						<input name="field_name" id="field_name" value="${field_nameJSP}" type="hidden" />
						<div class="cont">	
							<table>
								<tr><td class="name">Наименование документа</td><td><input name="NAME_DOC" id="searchNAME_DOC" type="text" class="input-text" placeholder="Наименование документа" maxlength="256" /></td></tr>
								<tr><td class="name">Описание документа</td><td><input name="DESC_DOC" id="searchDESC_DOC" type="text" class="input-text" placeholder="Описание документа" maxlength="256" /></td></tr>
								<tr><td class="name">Содержание поручения</td><td><input name="MISSION" id="searchMISSION" type="text" class="input-text" placeholder="Содержание поручения" maxlength="512" /></td></tr>
								<tr><td class="name">Контролирующее лицо</td><td><input name="CTRL_FACE" autocomplete="off" id="searchCTRL_FACE" list="data-ctrls" type="text" class="input-text" placeholder="Контролирующее лицо" maxlength="256" /></td></tr>
								<tr><td class="name">Исполнители</td>
									<td><table class="executors"><tr>
										<td><input autocomplete="off" name="EXECUTOR_1" id="searchEXECUTOR_1" placeholder="Исполнител 1" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
										<td><input autocomplete="off" name="EXECUTOR_2" id="searchEXECUTOR_2" placeholder="Исполнител 2" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
										<td><input autocomplete="off" name="EXECUTOR_3" id="searchEXECUTOR_3" placeholder="Исполнител 3" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
										<td class="last"><input autocomplete="off" name="EXECUTOR_4" id="searchEXECUTOR_4" placeholder="Исполнител 4" type="text" class="input-text" list="data-executors" maxlength="256" /></td>
									</tr></table></td>
								</tr>
								<tr><td class="name">Поставлена на контроль</td>
									<td>
										<input autocomplete="off" id="searchDATE_EXEC" name="DATE_EXEC" type="text" class="input-date" placeholder="Поставлена на контроль" maxlength="10" />
										<p class="p-before-date">по</p><input name="DATE_EXEC2" autocomplete="off" id="searchDATE_EXEC2" type="text" class="input-date" placeholder="Поставлена на контроль" maxlength="10" />
										<button type="button" class="btn-class after-date" id="equallyDATE_EXEC">=</button>
										<button type="button" class="btn-class after-date last"  id="clearDATE_EXEC">X</button>
									</td>
								</tr>
								<tr><td class="name">Срок выполнения</td>
									<td>
										<input autocomplete="off" id="searchDATE_CTRL" name="DATE_CTRL" type="text" class="input-date" placeholder="Срок выполнения" maxlength="10" />
										<p class="p-before-date">по</p><input name="DATE_CTRL2" autocomplete="off" id="searchDATE_CTRL2" type="text" class="input-date" placeholder="Срок выполнения" maxlength="10" />
										<button type="button" class="btn-class after-date" id="equallyDATE_CTRL">=</button>
										<button type="button" class="btn-class after-date last" id="clearDATE_CTRL">X</button>
									</td>
								</tr>
								<tr><td class="name">Срок продления</td>
									<td>
										<select name="typeExtension" id="typeExtension" class="typeExtension">
											<option value="0" selected="selected">Все карточки</option>
											<option value="1">Продленные</option>
											<option value="2">Непродленные</option>
										</select>
										<div id="divExtension">
											<input autocomplete="off" id="searchDATE_EXTENSION" name="DATE_EXTENSION" type="text" class="input-date" placeholder="Срок продления" maxlength="10" />
											<p class="p-before-date">по</p><input name="DATE_EXTENSION2" autocomplete="off" id="searchDATE_EXTENSION2" type="text" class="input-date" placeholder="Срок продления" maxlength="10" />
											<button type="button" class="btn-class after-date" id="equallyDATE_EXTENSION">=</button>
											<button type="button" class="btn-class after-date last" id="clearDATE_EXTENSION">X</button>
										</div>
									</td>
								</tr>
								<tr id="buttonsSearch">
									<td colspan="2" class="buttons dynamic_parent clearfix">
										<div class="dynamic_fixed left"><button id="btnExcel" type="button" class="btn-class">Экспорт в Excel</button></div>
										<div class="dynamic_fixed left"><button id="btnPrint" type="button" class="btn-class">Печать</button></div>
										<div class="dynamic_fixed right"><button type="button" class="btn-class" onclick="Clear();">Очистить</button></div>
										<div class="dynamic_stretch"><button id="btnSearch" type="button" class="btn-class" onclick="Search();">Найти</button></div>
									</td>
								</tr>
							</table>
						</div>
					</form>	
				</div>
			</div>
		</div>		
		<div class="section result">
			<div class="scroll"><div class="top-scroll"></div>	</div>
			<div class="container">		
				<table id="result" class="sortable">
					<thead>
						<tr>
							<th class="headCTRL_ID"></th>
							<th class="headNAME_DOC" id="headNAME_DOC"><h3>Наименование документа</h3></th>
							<th class="headDESC_DOC" id="headDESC_DOC"><h3>Описание документа</h3></th>
							<th class="headMISSION" id="headMISSION"><h3>Содержание поручения</h3></th>
							<th class="headCTRL_FACE" id="headCTRL_FACE"><h3>Контролирующие лица</h3></th>
							<th class="headEXECUTORS" id="headEXECUTORS"><h3>Исполнители</h3></th>
							<th class="headDATE_EXEC asc" id="headDATE_EXEC"><h3>Поставлен на контр.</h3></th>
							<th class="headDATE_CTRL" id="headDATE_CTRL"><h3>Срок выполнения</h3></th>
							<th class="headDATE_EXTENSION" id="headDATE_EXTENSION"><h3>Срок продлён</h3></th> 
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<datalist id="data-ctrls"> 
		<c:forEach items="${executorsExecJSP}" var="Executor">
        	<option class="exec${Executor.id}" label="${Executor.name}">${Executor.name}</option>
        </c:forEach>
	</datalist>	
	<datalist id="data-executors"> 
		<c:forEach items="${executorsCtrlJSP}" var="Executor">
        	<option class="exec${Executor.id}" label="${Executor.name}">${Executor.name}</option>
        </c:forEach>
	</datalist>
</body>
</html>