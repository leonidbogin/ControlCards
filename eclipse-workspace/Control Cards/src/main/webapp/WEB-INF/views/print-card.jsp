<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Контроль исполнительной дисциплины | Печать ${page_text_smallJSP} карточки </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10">
	<link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/print-card.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css" />">
	<link rel="icon" href="<c:url value="/resources/img/favicon.ico" />" type= "image/x-icon"> 
	<link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />" type="image/x-icon">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/print.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-1.12.4.js" />"></script>
</head>
<body>
	<div class="wrapper">
		<div class="section card">
			<div class="container">	
				<div class="modal-content">
						<h3>${page_textJSP} карточка</h3>
						<table>
							<tr><td class="name textarea">Наименование документа</td><td><textarea rows="4" readonly id="watchNAME_DOC" class="input-textarea" placeholder="" maxlength="256">${cardJSP.NAME_DOC}</textarea></td></tr>
							<tr><td class="name textarea">Описание документа</td><td><textarea rows="4" readonly id="watchDESC_DOC" class="input-textarea" placeholder="" maxlength="256">${cardJSP.DESC_DOC}</textarea></td></tr>
							<tr><td class="name textarea">Содержание поручения</td><td><textarea rows="8" readonly id="watchMISSION" class="input-textarea" placeholder="" maxlength="512">${cardJSP.MISSION}</textarea></td></tr>
							<tr><td class="name">Контролирующее лицо</td><td><input readonly autocomplete="off" id="watchCTRL_FACE" list="data-ctrls" type="text" class="input-text" placeholder="" value="${cardJSP.CTRL_FACE}" maxlength="256" /></td></tr>
							<tr><td class="name executors">Исполнители</td>
								<td>
									<table class="executors">
										<tr>
											<td><input autocomplete="off" readonly id="watchEXECUTOR_1" placeholder="" type="text" class="input-text" list="data-executors" value="${cardJSP.EXECUTOR_1}" maxlength="256" /></td>
											<td><input autocomplete="off" readonly id="watchEXECUTOR_2" placeholder="" type="text" class="input-text" list="data-executors" value="${cardJSP.EXECUTOR_2}" maxlength="256" /></td>
											<td><input autocomplete="off" readonly id="watchEXECUTOR_3" placeholder="" type="text" class="input-text" list="data-executors" value="${cardJSP.EXECUTOR_3}" maxlength="256" /></td>
											<td class="last"><input readonly autocomplete="off" id="watchEXECUTOR_4" placeholder="" type="text" class="input-text" list="data-executors" value="${cardJSP.EXECUTOR_4}" maxlength="256" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr><td class="name">Поставлена на контроль</td>
								<td>
									<input autocomplete="off" readonly id="watchDATE_EXEC" type="text" class="input-date" placeholder="" value="${cardJSP.DATE_EXEC}" maxlength="10" />
								</td>
							</tr>
							<tr><td class="name">Срок выполнения</td>
								<td>
									<input autocomplete="off" readonly id="watchDATE_CTRL" type="text" class="input-date" placeholder="" value="${cardJSP.DATE_CTRL}" maxlength="10" />
								</td>
							</tr>
							<tr><td class="name">Срок продления</td>
								<td>
									<input readonly autocomplete="off" id="watchDATE_EXTENSION" type="text" class="input-date" placeholder="" value="${cardJSP.DATE_EXTENSION}" maxlength="10" />
								</td>
							</tr>
							<tr class="${typeJSP}"><td class="name">Снята с контроля</td>
								<td>
									<input readonly autocomplete="off" id="watchTAKE_OFF_CTRL" type="text" class="input-date" placeholder="" value="${cardJSP.TAKE_OFF_CTRL}" maxlength="10" />
								</td>
							</tr>
						</table>
				</div> 
			</div>
		</div>	
	</div>
<!-- 	<hr> -->
</body>
</html>