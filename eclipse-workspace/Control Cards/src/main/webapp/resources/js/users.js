document.addEventListener('DOMContentLoaded', function () {
	Search();
});

$(document).ready(function(){
	
	//Сортировки
    $('.headEXEC_USER').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'EXEC_USER ASC') {
    		el_field.value = 'EXEC_USER DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'EXEC_USER DESC') {
    			el_field.value = 'EXEC_USER ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'EXEC_USER ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    $('.headEXEC_CTRL_FACE_FLAG').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'EXEC_CTRL_FACE_FLAG ASC') {
    		el_field.value = 'EXEC_CTRL_FACE_FLAG DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'EXEC_CTRL_FACE_FLAG DESC') {
    			el_field.value = 'EXEC_CTRL_FACE_FLAG ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'EXEC_CTRL_FACE_FLAG ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    $('.headEXEC_EXECUTOR_FLAG').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'EXEC_EXECUTOR_FLAG ASC') {
    		el_field.value = 'EXEC_EXECUTOR_FLAG DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'EXEC_EXECUTOR_FLAG DESC') {
    			el_field.value = 'EXEC_EXECUTOR_FLAG ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'EXEC_EXECUTOR_FLAG ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    
    $('tbody').on('change', ".EXEC_USER input[type='text']", function (e) {
    	if (e.currentTarget.value.length > 0) {
    		if (e.currentTarget.value.length <= 256) {
    			var EXEC_ID = e.currentTarget.id;
            	var EXEC_USER = e.currentTarget.value;
            	
            	$.post('update-executor-user', {
            		EXEC_ID : EXEC_ID,		
            		EXEC_USER : EXEC_USER,	
            	}, function(data) {
            		if (data != 'TRUE') alert('Произошла ошибка изменения. Обновите страницу и попробуйте ещё раз.');
            	}).done(function() {

            	}).complete(function() {
            		
            	});
    		} else {
    			alert('Произошла ошибка изменения. ФИО должно быть меньше 256 символов.');
    		}
    	} else {
    		alert('Произошла ошибка изменения. ФИО не может быть пустым.');
    	}
    });
    $('tbody').on('change', ".EXEC_CTRL_FACE_FLAG input[type='checkbox']", function (e) {
    	var EXEC_ID = e.currentTarget.val;
    	var EXEC_CTRL_FACE_FLAG;
    	if (e.currentTarget.checked) EXEC_CTRL_FACE_FLAG = "TRUE";
    	else EXEC_CTRL_FACE_FLAG = "FALSE";
    	$.post('update-executor-ctrl', {
    		EXEC_ID : EXEC_ID,		
    		EXEC_CTRL_FACE_FLAG : EXEC_CTRL_FACE_FLAG,	
    	}, function(data) {
    		if (data != 'TRUE') alert('Произошла ошибка изменения. Обновите страницу и попробуйте ещё раз.');
    	}).done(function() {

    	}).complete(function() {
    		
    	});
    });    
    $('tbody').on('change', ".EXEC_EXECUTOR_FLAG input[type='checkbox']", function (e) {
    	var EXEC_ID = e.currentTarget.val;
    	var EXEC_EXECUTOR_FLAG;
    	if (e.currentTarget.checked) EXEC_EXECUTOR_FLAG = "TRUE";
    	else EXEC_EXECUTOR_FLAG = "FALSE";
    	$.post('update-executor-exec', {
    		EXEC_ID : EXEC_ID,		
    		EXEC_EXECUTOR_FLAG : EXEC_EXECUTOR_FLAG,	
    	}, function(data) {
    		if (data != 'TRUE') alert('Произошла ошибка изменения. Обновите страницу и попробуйте ещё раз.');
    	}).done(function() {

    	}).complete(function() {
    		
    	});
    });    
    
    $('#addExecutor').click(function(){
    	AddExecutor();
	});
    
    $('#searchExecutor').click(function(){
    	Search();
	});
    
    $('#canselExecutor').click(function(){
    	$('#searchEXEC_USER').val("");
    	Search();
	});
    
//    $('tbody').on('click', "button[type='button'].delete", function (e) {
//    	var EXEC_ID = e.currentTarget.value;
//    	
//    	$.post('remove-executor', {	
//			EXEC_ID : EXEC_ID,
//		}, function(data) {
//			if (data != 'TRUE') {
//				alert('Произошла ошибка удаления. Обновите страницу и попробуйте ещё раз.');
//			} else {
//				alert("Исполнительное лицо успешно удаленно");		
//				Search();
//			}
//		}).done(function() {
//		
//		}).fail(function() {
//			alert('Произошла ошибка удаления. Обновите страницу и попробуйте ещё раз.');
//		}).complete(function() {
//
//		});
//	});
});

function AddExecutor() {
	if ($('#addEXEC_USER').val().length > 0) {
		if ($('#addEXEC_USER').val().length <= 256) {
			var EXEC_USER = $('#addEXEC_USER').val();
			var EXEC_CTRL_FACE_FLAG;
			if ($('#addEXEC_CTRL_FACE_FLAG').prop("checked")) EXEC_CTRL_FACE_FLAG = "TRUE";
			else EXEC_CTRL_FACE_FLAG = "FALSE";
			var EXEC_EXECUTOR_FLAG;
			if ($('#addEXEC_EXECUTOR_FLAG').prop("checked")) EXEC_EXECUTOR_FLAG = "TRUE";
			else EXEC_EXECUTOR_FLAG = "FALSE";
			
			$.post('add-executor', {	
				EXEC_USER : EXEC_USER,
				EXEC_CTRL_FACE_FLAG : EXEC_CTRL_FACE_FLAG,
				EXEC_EXECUTOR_FLAG : EXEC_EXECUTOR_FLAG,
			}, function(data) {
				if (data != 'TRUE') {
					alert('Произошла ошибка добавления. Обновите страницу и попробуйте ещё раз.');
				} else {
					alert("Исполнительное лицо '" + $('#addEXEC_USER').val() + "' успешно добавлено.");		
					$('#addEXEC_USER').val('');
					$('#addEXEC_CTRL_FACE_FLAG').prop('checked', false);
					$('#addEXEC_EXECUTOR_FLAG').prop('checked', false);
					Search();
				}
			}).done(function() {
			
			}).fail(function() {
				alert('Произошла ошибка добавления. Обновите страницу и попробуйте ещё раз.');
			}).complete(function() {

			});
		} else {
			alert('Произошла ошибка добавления. ФИО должно быть меньше 256 символов.');
		}
	} else {
		alert('Произошла ошибка добавления. ФИО не может быть пустым.');
	}
}

function ClearSort() {
	$('#result').find('.asc').removeClass('asc');
	$('#result').find('.desc').removeClass('desc');	
}

function Search() {
	now_search = true;
	$('body').addClass('wait');
	$('.sortable thead h3').addClass('wait');
	
	var json;
	var field_name = document.getElementById('field_name').value;
	var el_table = document.getElementById('result');
	var EXEC_USER = $('#searchEXEC_USER').val();
	
	$('.rowlink').remove('.rowlink');
	
	$.post('get-executors', {
		field_name : field_name,
		EXEC_USER : EXEC_USER,
	}, function(data) {
		jsons = JSON.parse(data);
		if (jsons.length > 0) {
			for (var i = 0; i < jsons.length; i++){
				AddRow(jsons[i]);
			}
		} else {
			NewEmptyRow();
		}

	}).done(function() {

	}).complete(function() {
		now_search = false;
		$('body').removeClass('wait');
		$('.sortable thead h3').removeClass('wait');
	});
}

//Добавить пустую строку
function NewEmptyRow() {
	var tbody = document.getElementById('result').getElementsByTagName('TBODY')[0];
	
	var tr = document.createElement('tr');
	tr.classList.add('rowlink');
		
	var td1 = document.createElement('td');
    td1.innerHTML = '0';
    td1.classList.add('EXEC_ID');
    tr.appendChild(td1);
	    
	var td2 = document.createElement('td');
	td2.innerHTML = '';
    td2.classList.add('EXEC_USER');
    tr.appendChild(td2);
	    
    var td3 = document.createElement('td');
    td3.innerHTML = '';
    td3.classList.add('EXEC_CTRL_FACE_FLAG');
    tr.appendChild(td3);

    var td4 = document.createElement('td');
    td4.innerHTML = '';
    td4.classList.add('EXEC_EXECUTOR_FLAG');
    tr.appendChild(td4);
    
	tbody.appendChild(tr);
}

//Добавить строку
function AddRow(json) {
	var tbody = document.getElementById('result').getElementsByTagName('TBODY')[0];
	
	var tr = document.createElement('tr');
	tr.classList.add('rowlink');
		
	var td1 = document.createElement('td');
	td1.innerHTML = json.EXEC_ID;
    td1.classList.add('EXEC_ID');
    tr.appendChild(td1);
	    
	var td2 = document.createElement('td');
	td2.classList.add('EXEC_USER');
//	td2.innerHTML = json.EXEC_USER;
	var inputtext = document.createElement('input');
	inputtext.type = 'text';
	inputtext.id = json.EXEC_ID;
	inputtext.value = json.EXEC_USER;
	inputtext.maxLength = "256";
	td2.appendChild(inputtext);
    tr.appendChild(td2);
	    
    var td3 = document.createElement('td');
    td3.classList.add('EXEC_CTRL_FACE_FLAG');
    var inputcheck1 = document.createElement('input');
    inputcheck1.type = 'checkbox';
    if (json.EXEC_CTRL_FACE_FLAG == 'TRUE') inputcheck1.checked = true;
    else inputcheck1.checked = false;
    inputcheck1.val = json.EXEC_ID;
    td3.appendChild(inputcheck1);
    tr.appendChild(td3);

    var td4 = document.createElement('td');
    td4.classList.add('EXEC_EXECUTOR_FLAG');
    var inputcheck2 = document.createElement('input');
    inputcheck2.type = 'checkbox';
    if (json.EXEC_EXECUTOR_FLAG == 'TRUE') inputcheck2.checked = true;
    else inputcheck2.checked = false;
    inputcheck2.val = json.EXEC_ID;
    td4.appendChild(inputcheck2);
    tr.appendChild(td4);
    
	tbody.appendChild(tr);
}