<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Контроль исполнительной дисциплины | Печать ${page_text_smallJSP} карточек </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10">
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/print.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css" />">
	<link rel="icon" href="<c:url value="/resources/img/favicon.ico" />" type= "image/x-icon"> 
	<link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />" type="image/x-icon">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/print.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-1.12.4.js" />"></script>
</head>
<body>
	<div class="wrapper">
		<div class="section result">
			<div class="container">		
				<table id="result" class="sortable">
					<thead>
						<tr>
							<th class="headNAME_DOC nosort" id="headNAME_DOC"><h3>Наименование документа</h3></th>
							<th class="headDESC_DOC nosort" id="headDESC_DOC"><h3>Описание документа</h3></th>
							<th class="headMISSION nosort" id="headMISSION"><h3>Содержание поручения</h3></th>
							<th class="headCTRL_FACE nosort" id="headCTRL_FACE"><h3>Контролирующие лица</h3></th>
							<th class="headEXECUTORS nosort" id="headEXECUTORS"><h3>Исполнители</h3></th>
							<th class="headDATE_EXEC nosort" id="headDATE_EXEC"><h3>Поставлен на контр.</h3></th>
							<th class="headDATE_CTRL nosort" id="headDATE_CTRL"><h3>Срок выполнения</h3></th>
							<th class="headDATE_EXTENSION nosort ${sessionJSP.page_name}" id="headDATE_EXTENSION"><h3>Срок продлён</h3></th> 
							<th class="headTAKE_OFF_CTRL nosort last ${sessionJSP.page_name}" id="headTAKE_OFF_CTRL"><h3>Снята с контроля</h3></th> 
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${cardsJSP}" var="CardString">
                   		<tr class="rowlink">
                    		<td class="NAME_DOC first">${CardString.NAME_DOC}</td>
							<td class="DESC_DOC">${CardString.DESC_DOC}</td>
							<td class="MISSION">${CardString.MISSION}</td>
							<td class="CTRL_FACE">${CardString.CTRL_FACE}</td>
							<td class="EXECUTORS">${CardString.EXECUTORS}</td>
							<td class="DATE_EXEC">${CardString.DATE_EXEC}</td>
							<td class="DATE_CTRL">${CardString.DATE_CTRL}</td>
							<td class="DATE_EXTENSION">${CardString.DATE_EXTENSION}</td>
							<td class="TAKE_OFF_CTRL ${sessionJSP.page_name}">${CardString.TAKE_OFF_CTRL}</td>
                   		</tr>
                	</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>