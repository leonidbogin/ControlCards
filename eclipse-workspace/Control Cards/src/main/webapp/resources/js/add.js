$(document).ready(function(){
	$(function() {
		$('#addDATE_EXEC').datepicker({changeMonth: true, changeYear: true});
		$('#addDATE_CTRL').datepicker({changeMonth: true, changeYear: true});
	});
	
	$('.input_date').on('change', function(e) {	
		if (ValidDate($('#'+e.target.id).val())) 
			$('#'+e.target.id).removeClass('input-error'); 
		else $('#'+e.target.id).addClass('input-error');
    });
	
	$('#addCTRL_FACE').on('change', function(e) {
		var flag = false;
		for (var i = 0; i < $("#data-ctrls option").length; i++)
			if ($("#data-ctrls option")[i].value == $('#addCTRL_FACE').val()) {
				flag = true;
				break;
			}
		if (flag) {
			$('#addCTRL_FACE').removeClass('input-error');
		} else {
			$('#addCTRL_FACE').addClass('input-error');
		}
    });
	
	$('#addEXECUTOR_1').on('change', function(e) {
		var flag = false;
		for (var i = 0; i < $("#data-executors option").length; i++)
			if ($("#data-executors option")[i].value == $('#addEXECUTOR_1').val()) {
				flag = true;
				break;
			}
		if (flag) {
			$('#addEXECUTOR_1').removeClass('input-error');
			$("#addEXECUTOR_2").prop('disabled', false);
		} else {
			$('#addEXECUTOR_1').addClass('input-error');
			$("#addEXECUTOR_2").prop('disabled', true);
			$("#addEXECUTOR_2").removeClass('input-error');
			$("#addEXECUTOR_2").val('');
			$("#addEXECUTOR_3").prop('disabled', true);
			$("#addEXECUTOR_3").removeClass('input-error');
			$("#addEXECUTOR_3").val('');
			$("#addEXECUTOR_4").prop('disabled', true);
			$("#addEXECUTOR_4").removeClass('input-error');
			$("#addEXECUTOR_4").val('');
		}
    });
	
	$('#addEXECUTOR_2').on('change', function(e) {
		var flag = false;
		for (var i = 0; i < $("#data-executors option").length; i++)
			if ($("#data-executors option")[i].value == $('#addEXECUTOR_2').val()) {
				flag = true;
				break;
			}
		if (flag) {
			$('#addEXECUTOR_2').removeClass('input-error');
			$("#addEXECUTOR_3").prop('disabled', false);
		} else {
			$('#addEXECUTOR_2').addClass('input-error');
			$("#addEXECUTOR_3").prop('disabled', true);
			$("#addEXECUTOR_3").removeClass('input-error');
			$("#addEXECUTOR_3").val('');
			$("#addEXECUTOR_4").prop('disabled', true);
			$("#addEXECUTOR_4").removeClass('input-error');
			$("#addEXECUTOR_4").val('');
		}
    });
	
	$('#addEXECUTOR_3').on('change', function(e) {
		var flag = false;
		for (var i = 0; i < $("#data-executors option").length; i++)
			if ($("#data-executors option")[i].value == $('#addEXECUTOR_3').val()) {
				flag = true;
				break;
			}
		if (flag) {
			$('#addEXECUTOR_3').removeClass('input-error');
			$("#addEXECUTOR_4").prop('disabled', false);
		} else {
			$('#addEXECUTOR_3').addClass('input-error');
			$("#addEXECUTOR_4").prop('disabled', true);
			$("#addEXECUTOR_4").removeClass('input-error');
			$("#addEXECUTOR_4").val('');
		}
    });
	
	$('#addEXECUTOR_4').on('change', function(e) {
		var flag = false;
		for (var i = 0; i < $("#data-executors option").length; i++)
			if ($("#data-executors option")[i].value == $('#addEXECUTOR_4').val()) {
				flag = true;
				break;
			}
		if (flag) {
			$('#addEXECUTOR_4').removeClass('input-error');
		} else {
			$('#addEXECUTOR_4').addClass('input-error');
		}
    });
	
	$('#addDATE_EXEC').on('change', function(e) {
		if (ValidDate($('#addDATE_EXEC').val())) $('#addDATE_EXEC').removeClass('input-error'); 
		else $('#addDATE_EXEC').addClass('input-error');
	});
	
	$('#addDATE_CTRL').on('change', function(e) {
		if (ValidDate($('#addDATE_CTRL').val())) $('#addDATE_CTRL').removeClass('input-error'); 
		else $('#addDATE_CTRL').addClass('input-error');
	});
	
});

function AddError(text) {
	var errors = document.getElementById('errors');
	var div = document.createElement('div');
	div.classList.add('new_error');
	var p = document.createElement('p');
	p.innerHTML = text;
	div.appendChild(p);
	errors.appendChild(div);
}
	
function Add() {
	$('.new_error').remove();
	
	var NAME_DOC = $('#addNAME_DOC').val();
	var DESC_DOC = $('#addDESC_DOC').val();
	var MISSION = $('#addMISSION').val();
	var CTRL_FACE = $('#addCTRL_FACE').val();
	var EXECUTOR_1 = $('#addEXECUTOR_1').val();
	var EXECUTOR_2 = $('#addEXECUTOR_2').val();
	var EXECUTOR_3 = $('#addEXECUTOR_3').val();
	var EXECUTOR_4 = $('#addEXECUTOR_4').val();
	var DATE_EXEC = $('#addDATE_EXEC').val();
	var DATE_CTRL = $('#addDATE_CTRL').val();
	
	if (DATE_EXEC.length == 0) $('#addDATE_EXEC').addClass('input-error');
	if (DATE_CTRL.length == 0) $('#addDATE_CTRL').addClass('input-error');
	
	$.post('add-card', {
		NAME_DOC : NAME_DOC,
		DESC_DOC : DESC_DOC,
		MISSION : MISSION,
		CTRL_FACE : CTRL_FACE,
		EXECUTOR_1 : EXECUTOR_1,
		EXECUTOR_2 : EXECUTOR_2,
		EXECUTOR_3 : EXECUTOR_3,
		EXECUTOR_4 : EXECUTOR_4,
		DATE_EXEC : DATE_EXEC,
		DATE_CTRL : DATE_CTRL,
		
	}, function(data) {
		var jsons = JSON.parse(data);
		if (jsons.length > 0) {
			for (var i = 0; i < jsons.length; i++){
				AddError(jsons[i]);
			}
		} else {
			var errors = document.getElementById('errors');
			var div = document.createElement('div');
			div.classList.add('new_error');
			div.classList.add('ok');
			var p = document.createElement('p');
			p.innerHTML = "Контрольная карта успешно добавлена. Просмотреть/отредактировать карточку можно на странице 'Действующие карточки'.";
			div.appendChild(p);
			errors.appendChild(div);
			
			$('.input-error').removeClass('input-error');
			$('#addEXECUTOR_2').prop('disabled', true);
			$('#addEXECUTOR_3').prop('disabled', true);
			$('#addEXECUTOR_4').prop('disabled', true);
			
			$('#addNAME_DOC').val('');
			$('#addDESC_DOC').val('');
			$('#addMISSION').val('');
			$('#addCTRL_FACE').val('');
			$('#addEXECUTOR_1').val('');
			$('#addEXECUTOR_2').val('');
			$('#addEXECUTOR_3').val('');
			$('#addEXECUTOR_4').val('');
			$('#addDATE_EXEC').val('');
			$('#addDATE_CTRL').val('');
		}
	}).done(function() {
		
	}).complete(function() {

	});
}

function ValidDate(date){ // date в формате 31.12.2014
	  var d_arr = date.split('.');
	  var d = new Date(d_arr[2]+'/'+d_arr[1]+'/'+d_arr[0]+''); // дата в формате 2014/12/31
	  if (d_arr[2]!=d.getFullYear() || d_arr[1]!=(d.getMonth() + 1) || d_arr[0]!=d.getDate()) {
	    return false; // неккоректная дата
	  };
	  return true;
}

