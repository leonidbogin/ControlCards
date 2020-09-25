var show_more_id = 0;               //Индекс открытой подробно карточки

var show_count = 50;                //Кол-во выводимых записей
var next_show_start = 1;            //Начало диапозона вывода
var next_show_finish = show_count;  //Конец диапозона вывода

var now_search = false;             //Флаг работы выборки

var ntr = 0;                        //Кол-во выведенных записей
var ntr_last = 0;                   //Кол-во выведенных записей прошлую выборку 

//Старт JQuery
$(window).ready(function () {
	LoadPage();
	Search();
	$(window).on("scroll", ScrollingPage);
});

//Загрузка страницы 
function LoadPage() {
	if (document.getElementById('page_name').value == 'valid') { //Если это страница 'Действующие карточки'
		$('.a-valid').addClass('page-active');
		$('#headDATE_EXTENSION').addClass('last');
		CreateEmptySearchTAKE_OFF_CTRL();
	} else {                                                     //Если это страница 'Снятые карточки'
		$('.a-deleted').addClass('page-active');
		$('#search').addClass('deleted'); 
		CreateSearchTAKE_OFF_CTRL(); 
		CreateTAKE_OFF_CTRL();
	}
	if ($("#search_open").val() == "true") {
		ModeSearch(true);
	}
	
	$( function() { //Включение датапикеров
		$('#searchDATE_EXEC').datepicker({changeMonth: true, changeYear: true});
		$('#searchDATE_CTRL').datepicker({changeMonth: true, changeYear: true});
		$('#searchDATE_EXTENSION').datepicker({changeMonth: true, changeYear: true});
		$('#searchTAKE_OFF_CTRL').datepicker({changeMonth: true, changeYear: true});
		$('#searchDATE_EXEC2').datepicker({changeMonth: true, changeYear: true});
		$('#searchDATE_CTRL2').datepicker({changeMonth: true, changeYear: true});
		$('#searchDATE_EXTENSION2').datepicker({changeMonth: true, changeYear: true});
		$('#searchTAKE_OFF_CTRL2').datepicker({changeMonth: true, changeYear: true});
		$('#watchDATE_EXEC').datepicker({changeMonth: true, changeYear: true});
		$('#watchDATE_CTRL').datepicker({changeMonth: true, changeYear: true});
		$('#watchDATE_EXTENSION').datepicker({changeMonth: true, changeYear: true});
		$('#watchTAKE_OFF_CTRL').datepicker({changeMonth: true, changeYear: true});
	} );
    $.datepicker.setDefaults( $.datepicker.regional[ 'ru' ] ); 
}


//Создание столбца в таблице 'Снята с контроля'
function CreateTAKE_OFF_CTRL(){
	var tr = document.getElementById('result').getElementsByTagName('THEAD')[0].getElementsByTagName('TR')[0];
	
	var th = document.createElement('th'); th.classList.add('headTAKE_OFF_CTRL'); th.classList.add('last'); th.id = "headTAKE_OFF_CTRL";
    var h3 = document.createElement('h3'); h3.innerHTML = 'Снята с контроля';
    
    th.appendChild(h3);
    tr.appendChild(th);
}

//Создание пустого поля 'Снята с контроля' в поиске
function CreateEmptySearchTAKE_OFF_CTRL(){
	var trAfter = document.getElementById('buttonsSearch');
	var table = document.getElementById('search').getElementsByTagName('TABLE')[0].getElementsByTagName('TBODY')[0];
	var input1 = document.createElement('input'); input1.name = "TAKE_OFF_CTRL"; input1.type = "hidden";
	var input2 = document.createElement('input'); input2.name = "TAKE_OFF_CTRL2"; input2.type = "hidden";
	table.insertBefore(input1, trAfter);
	table.insertBefore(input2, trAfter);
}

//Создание поля 'Снята с контроля' в поиске
function CreateSearchTAKE_OFF_CTRL(){
	var trAfter = document.getElementById('buttonsSearch');
	var table = document.getElementById('search').getElementsByTagName('TABLE')[0].getElementsByTagName('TBODY')[0];
	
	var tr = document.createElement('tr');
	var td1 = document.createElement('td');	td1.classList.add('name');	td1.innerHTML = 'Снята с контроля';	
	var td2 = document.createElement('td');
	var input1 = document.createElement('input'); input1.classList.add('input-date'); input1.id = "searchTAKE_OFF_CTRL"; input1.name = "TAKE_OFF_CTRL"; input1.type = "text";	input1.autocomplete = "off"; input1.setAttribute("maxlength", 10); input1.placeholder = "Снята с контроля";
	var p = document.createElement('p'); p.classList.add('p-before-date'); p.innerHTML = 'по';
	var input2 = document.createElement('input'); input2.classList.add('input-date'); input2.id = "searchTAKE_OFF_CTRL2"; input2.name = "TAKE_OFF_CTRL2"; input2.type = "text"; input2.autocomplete = "off"; input2.setAttribute("maxlength", 10); input2.placeholder = "Снята с контроля";
	var button1 = document.createElement('button');	button1.type = "button"; button1.id = "equallyTAKE_OFF_CTRL"; button1.classList.add('btn-class'); button1.classList.add('after-date'); button1.innerHTML = '=';
	var button2 = document.createElement('button'); button2.type = "button"; button2.id = "clearTAKE_OFF_CTRL"; button2.classList.add('btn-class'); button2.classList.add('after-date'); button2.classList.add('last'); button2.innerHTML = 'X';
	
	td2.appendChild(input1);
	td2.appendChild(p);
	td2.appendChild(input2);
	td2.appendChild(button1);
	td2.appendChild(button2);
	tr.appendChild(td1);
	tr.appendChild(td2);
	table.insertBefore(tr, trAfter);
}

//Смена цвета кнопки поиска если поиск не актуален
function NotRelevantSearch(){
	$('#btnSearch').addClass('btn-error');
	$('#btnSearch').removeClass('btn-ok');
}

//Очистка полей поиска
function Clear() {
	NotRelevantSearch();
	$('.input-error').removeClass('input-error');
	$('#searchNAME_DOC').val('');
	$('#searchDESC_DOC').val('');
	$('#searchMISSION').val('');
	$('#searchCTRL_FACE').val('');
	$('#searchEXECUTOR_1').val('');
	$('#searchEXECUTOR_2').val('');
	$('#searchEXECUTOR_3').val('');
	$('#searchEXECUTOR_4').val('');
	$('#searchDATE_EXEC').val('');
	$('#searchDATE_CTRL').val('');
	$('#searchDATE_EXEC2').val('');
	$('#searchDATE_CTRL2').val('');
	$('.extension-open').removeClass('extension-open');	
	$('#searchDATE_EXTENSION').val('');
	$('#searchDATE_EXTENSION2').val('');
	$('#typeExtension').val(0);	
	$('#searchTAKE_OFF_CTRL').val('');
	$('#searchTAKE_OFF_CTRL2').val('');
}

//Очистка сортировки
function ClearSort() {
	$('#result').find('.asc').removeClass('asc');
	$('#result').find('.desc').removeClass('desc');	
}

//Событие скроллинга
function ScrollingPage(){
	if (!now_search && ntr+1== next_show_start){
		var currentHeight = $("#result").height();
		if($(this).scrollTop() >= (currentHeight - $(this).height()-150)){
			SearchMore();
		}
	}
	var the_top = $(document).scrollTop();
    if (the_top > 130) {
        $('.scroll').addClass('fixed-top');
    }
    else {
    	$('.scroll').removeClass('fixed-top');
    }
}

//Открытие/Закрытие выборки
function ModeSearch(flag) {
	if (flag == false)	{ 
		$('.search-head h4').text('Показать поиск');
		$('.search-head img').removeClass('route180');
		$('#search').removeClass('show');
	} else {
		$('.search-head h4').text('Скрыть поиск');
		$('.search-head img').addClass('route180');
		$('#search').addClass('show');
	}	
	$.post('change-search-mode', {
		flag : flag,
	}, function() {
	}).done(function() {
	}).complete(function() {
	});
}

//Старт выборки
function Search() {
	now_search = true;
	$('body').addClass('wait');
	$('.sortable thead h3').addClass('wait');
	$("#btnSearch").prop('disabled', true);
	$('#btnSearch').addClass('wait');
	$("#btnPrint").prop('disabled', true);
	$('#btnPrint').addClass('wait');
	NotRelevantSearch();
	
	ntr = 0;
	show_more_id = 0;
	$('.show-more').removeClass('show-more');
	next_show_start = 1;
	next_show_finish = show_count;
	
	var json;
	var page_name = document.getElementById('page_name').value;
	var field_name = document.getElementById('field_name').value;
	var el_table = document.getElementById('result');

	var NAME_DOC = $('#searchNAME_DOC').val(); var DESC_DOC = $('#searchDESC_DOC').val(); var MISSION = $('#searchMISSION').val(); var CTRL_FACE = $('#searchCTRL_FACE').val();	var EXECUTOR_1 = $('#searchEXECUTOR_1').val(); var EXECUTOR_2 = $('#searchEXECUTOR_2').val(); var EXECUTOR_3 = $('#searchEXECUTOR_3').val(); var EXECUTOR_4 = $('#searchEXECUTOR_4').val(); var DATE_EXEC = $('#searchDATE_EXEC').val(); var DATE_CTRL = $('#searchDATE_CTRL').val(); var DATE_EXEC2 = $('#searchDATE_EXEC2').val(); var DATE_CTRL2 = $('#searchDATE_CTRL2').val(); var typeExtension = $('#typeExtension').val(); var DATE_EXTENSION = $('#searchDATE_EXTENSION').val(); var DATE_EXTENSION2 = $('#searchDATE_EXTENSION2').val();
	var TAKE_OFF_CTRL;
	if (page_name == 'valid') {
		TAKE_OFF_CTRL = '';	
		TAKE_OFF_CTRL2 = '';
	} else {
		TAKE_OFF_CTRL = $('#searchTAKE_OFF_CTRL').val();
		TAKE_OFF_CTRL2 = $('#searchTAKE_OFF_CTRL2').val();
	}
	
	$.post('get-cards', {
		page_name : page_name,
		field_name : field_name,
		next_show_start : next_show_start,
		next_show_finish : next_show_finish,
		
		typeExtension : typeExtension,
		NAME_DOC : NAME_DOC, DESC_DOC : DESC_DOC, MISSION : MISSION, CTRL_FACE : CTRL_FACE,	EXECUTOR_1 : EXECUTOR_1, EXECUTOR_2 : EXECUTOR_2, EXECUTOR_3 : EXECUTOR_3, EXECUTOR_4 : EXECUTOR_4, DATE_EXEC : DATE_EXEC, DATE_CTRL : DATE_CTRL, DATE_EXEC2 : DATE_EXEC2, DATE_CTRL2 : DATE_CTRL2,	DATE_EXTENSION : DATE_EXTENSION, DATE_EXTENSION2 : DATE_EXTENSION2, TAKE_OFF_CTRL : TAKE_OFF_CTRL, TAKE_OFF_CTRL2 : TAKE_OFF_CTRL2,
	}, function(data) {
		jsons = JSON.parse(data);
		if (jsons.length > 0) {
			for (var i = 0; i < jsons.length; i++){
				NewRow(jsons[i], page_name);
			}
		} else {
			NewEmptyRow();
		}
		if (ntr_last > ntr) {
			for (var i = ntr+1; i <= ntr_last; i++){
				$(".rowlink.row"+i).remove();
			}
		} 
		ntr_last = jsons.length;
		next_show_start += show_count;
		next_show_finish += show_count;
	}).done(function() {
		$('#btnSearch').addClass('btn-ok');
		$('#btnSearch').removeClass('btn-error');
	}).complete(function() {
		now_search = false;
		$('body').removeClass('wait');
		$('.sortable thead h3').removeClass('wait');
		$('#btnSearch').prop('disabled', false);
		$('#btnSearch').removeClass('wait');
		$("#btnPrint").prop('disabled', false);
		$('#btnPrint').removeClass('wait');
	});
}

//Подгрузка карточек
function SearchMore() {
	now_search = true;
	$('body').addClass('wait');
	$('.sortable thead h3').addClass('wait');
	$("#btnSearch").prop('disabled', true);
	$('#btnSearch').addClass('wait');
	$("#btnPrint").prop('disabled', true);
	$('#btnPrint').addClass('wait');
	NotRelevantSearch();
		
	var json;
	var page_name = document.getElementById('page_name').value;
	var field_name = document.getElementById('field_name').value;
	var el_table = document.getElementById('result');

	var NAME_DOC = $('#searchNAME_DOC').val(); var DESC_DOC = $('#searchDESC_DOC').val(); var MISSION = $('#searchMISSION').val(); var CTRL_FACE = $('#searchCTRL_FACE').val();	var EXECUTOR_1 = $('#searchEXECUTOR_1').val(); var EXECUTOR_2 = $('#searchEXECUTOR_2').val(); var EXECUTOR_3 = $('#searchEXECUTOR_3').val(); var EXECUTOR_4 = $('#searchEXECUTOR_4').val(); var DATE_EXEC = $('#searchDATE_EXEC').val(); var DATE_CTRL = $('#searchDATE_CTRL').val(); var DATE_EXEC2 = $('#searchDATE_EXEC2').val(); var DATE_CTRL2 = $('#searchDATE_CTRL2').val(); var typeExtension = $('#typeExtension').val(); var DATE_EXTENSION = $('#searchDATE_EXTENSION').val(); var DATE_EXTENSION2 = $('#searchDATE_EXTENSION2').val();
	
	var TAKE_OFF_CTRL;
	if (page_name == 'valid') {
		TAKE_OFF_CTRL = '';	
		TAKE_OFF_CTRL2 = '';
	} else {
		TAKE_OFF_CTRL = $('#searchTAKE_OFF_CTRL').val();
		TAKE_OFF_CTRL2 = $('#searchTAKE_OFF_CTRL2').val();
	}
	$.post('get-cards', {
		page_name : page_name,
		field_name : field_name,
		next_show_start : next_show_start,
		next_show_finish : next_show_finish,
		
		typeExtension : typeExtension,
		NAME_DOC : NAME_DOC, DESC_DOC : DESC_DOC, MISSION : MISSION, CTRL_FACE : CTRL_FACE,	EXECUTOR_1 : EXECUTOR_1, EXECUTOR_2 : EXECUTOR_2, EXECUTOR_3 : EXECUTOR_3, EXECUTOR_4 : EXECUTOR_4, DATE_EXEC : DATE_EXEC, DATE_CTRL : DATE_CTRL, DATE_EXEC2 : DATE_EXEC2, DATE_CTRL2 : DATE_CTRL2,	DATE_EXTENSION : DATE_EXTENSION, DATE_EXTENSION2 : DATE_EXTENSION2, TAKE_OFF_CTRL : TAKE_OFF_CTRL, TAKE_OFF_CTRL2 : TAKE_OFF_CTRL2,
	}, function(data) {
		jsons = JSON.parse(data);
		for (var i = 0; i < jsons.length; i++){
			AddRow(jsons[i], page_name);
		}
		ntr_last = ntr_last + jsons.length;
		next_show_start += show_count;
		next_show_finish += show_count;
	}).done(function() {
		$('#btnSearch').addClass('btn-ok');
		$('#btnSearch').removeClass('btn-error');
	}).complete(function() {
		now_search = false;
		$('body').removeClass('wait');
		$('.sortable thead h3').removeClass('wait');
		$('#btnSearch').prop('disabled', false);
		$('#btnSearch').removeClass('wait');
		$("#btnPrint").prop('disabled', false);
		$('#btnPrint').removeClass('wait');
	});
}

//Замена или добавление новой строки
function NewRow(json, type_cards) {
	if($('.rowlink.row'+(ntr+1)).length > 0) {
		ntr++;
		$('.rowlink.row'+ntr+' .CTRL_ID').html(json.CTRL_ID);
		$('.rowlink.row'+ntr+' .NAME_DOC').html(json.NAME_DOC);	
		$('.rowlink.row'+ntr+' .DESC_DOC').html(json.DESC_DOC);		
		$('.rowlink.row'+ntr+' .MISSION').html(json.MISSION);		
		$('.rowlink.row'+ntr+' .CTRL_FACE').html(json.CTRL_FACE);
		$('.rowlink.row'+ntr+' .EXECUTORS').html(json.EXECUTORS);
		$('.rowlink.row'+ntr+' .DATE_EXEC').html(json.DATE_EXEC);
		$('.rowlink.row'+ntr+' .DATE_CTRL').html(json.DATE_CTRL);
		$('.rowlink.row'+ntr+' .DATE_EXTENSION').html(json.DATE_EXTENSION);
		$('.rowlink.row'+ntr+' .TAKE_OFF_CTRL').html(json.TAKE_OFF_CTRL);
	} else {
		AddRow(json, type_cards);
	}
}

//Добавление новой строки
function AddRow(json, type_cards) {
	var page_name = document.getElementById('page_name').value;
	var tbody = document.getElementById('result').getElementsByTagName('TBODY')[0];
	ntr++;
	var tr = document.createElement('tr'); tr.classList.add('rowlink'); tr.classList.add('row'+ntr);	
    var td1 = document.createElement('td'); td1.innerHTML = json.CTRL_ID; td1.classList.add('CTRL_ID');   
    var td2 = document.createElement('td'); td2.innerHTML = json.NAME_DOC; td2.classList.add('NAME_DOC');    
    var td3 = document.createElement('td'); td3.innerHTML = json.DESC_DOC; td3.classList.add('DESC_DOC');
    var td4 = document.createElement('td'); td4.innerHTML = json.MISSION; td4.classList.add('MISSION');
    var td5 = document.createElement('td'); td5.innerHTML = json.CTRL_FACE; td5.classList.add('CTRL_FACE'); 
    var td6 = document.createElement('td'); td6.innerHTML = json.EXECUTORS; td6.classList.add('EXECUTORS'); 
    var td7 = document.createElement('td'); td7.innerHTML = json.DATE_EXEC;  td7.classList.add('DATE_EXEC');
    var td8 = document.createElement('td'); td8.innerHTML = json.DATE_CTRL; td8.classList.add('DATE_CTRL');
    var td9 = document.createElement('td'); td9.innerHTML = json.DATE_EXTENSION; td9.classList.add('DATE_EXTENSION');
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);
    tr.appendChild(td6);
    tr.appendChild(td7);
    tr.appendChild(td8);
    tr.appendChild(td9);
    if (page_name != 'valid') {
    	var td10 = document.createElement('td'); td10.innerHTML = json.TAKE_OFF_CTRL; td10.classList.add('TAKE_OFF_CTRL');
        tr.appendChild(td10);
    }
    tbody.appendChild(tr);
}

//Добавление пустой строки или замена на пустую строку
function NewEmptyRow() {
	var page_name = document.getElementById('page_name').value;
	var tbody = document.getElementById('result').getElementsByTagName('TBODY')[0];
	ntr = 1;
	if($('.rowlink.row'+ntr).length > 0) {
		$('.rowlink.row'+ntr+' .CTRL_ID').html("0");
		$('.rowlink.row'+ntr+' .NAME_DOC').html("");	
		$('.rowlink.row'+ntr+' .DESC_DOC').html("");		
		$('.rowlink.row'+ntr+' .MISSION').html("");		
		$('.rowlink.row'+ntr+' .CTRL_FACE').html("");
		$('.rowlink.row'+ntr+' .EXECUTORS').html("");
		$('.rowlink.row'+ntr+' .DATE_EXEC').html("");
		$('.rowlink.row'+ntr+' .DATE_CTRL').html("");
		$('.rowlink.row'+ntr+' .DATE_EXTENSION').html("");
		$('.rowlink.row'+ntr+' .TAKE_OFF_CTRL').html("");
	} else {
		var tr = document.createElement('tr'); tr.classList.add('rowlink'); tr.classList.add('row'+ntr);
		var td1 = document.createElement('td'); td1.innerHTML = '0'; td1.classList.add('CTRL_ID');
	    var td2 = document.createElement('td'); td2.innerHTML = ''; td2.classList.add('NAME_DOC');  
	    var td3 = document.createElement('td'); td3.innerHTML = ''; td3.classList.add('DESC_DOC'); 		    
	    var td4 = document.createElement('td'); td4.innerHTML = ''; td4.classList.add('MISSION');	    
	    var td5 = document.createElement('td'); td5.innerHTML = ''; td5.classList.add('CTRL_FACE');	    
	    var td6 = document.createElement('td'); td6.innerHTML = ''; td6.classList.add('EXECUTORS');	    
	    var td7 = document.createElement('td'); td7.innerHTML = ''; td7.classList.add('DATE_EXEC');	    
	    var td8 = document.createElement('td'); td8.innerHTML = ''; td8.classList.add('DATE_CTRL');
	    var td9 = document.createElement('td'); td9.innerHTML = ''; td9.classList.add('DATE_EXTENSION');
	    tr.appendChild(td1);
	    tr.appendChild(td2);
	    tr.appendChild(td3);
	    tr.appendChild(td4);
	    tr.appendChild(td5);
	    tr.appendChild(td6);
	    tr.appendChild(td7);
	    tr.appendChild(td8);
	    tr.appendChild(td9);
	    if (page_name != 'valid') {
	    	var td10 = document.createElement('td');
	        td10.innerHTML = '';
	        td10.classList.add('TAKE_OFF_CTRL');
	        tr.appendChild(td10);
	    }
	    tbody.appendChild(tr);
	}
}

//Выборка карточки
function SelectCard(CTRL_ID){
	CardWatch();
	$.post('get-card', {
		CTRL_ID : CTRL_ID,
	}, function(data) {
		json = JSON.parse(data);
		$('#watchCTRL_ID').val(CTRL_ID);
		$('#watchNAME_DOC').val(json.NAME_DOC);
		$('#watchDESC_DOC').val(json.DESC_DOC);
		$('#watchMISSION').val(json.MISSION);
		$('#watchCTRL_FACE').val(json.CTRL_FACE);
		$('#watchEXECUTOR_1').val(json.EXECUTOR_1);
		$('#watchEXECUTOR_2').val(json.EXECUTOR_2);
		$('#watchEXECUTOR_3').val(json.EXECUTOR_3);
		$('#watchEXECUTOR_4').val(json.EXECUTOR_4);
		$('#watchDATE_EXEC').val(json.DATE_EXEC);
		$('#watchDATE_CTRL').val(json.DATE_CTRL);
				
		if (json.DATE_EXTENSION.length > 0) {
			$('#watchDATE_EXTENSION').val(json.DATE_EXTENSION);
			$('#watchDATE_EXTENSION').datepicker("destroy");
			$('#watchDATE_EXTENSION').prop('readonly', true);
		} else {
			$('#watchDATE_EXTENSION').val("");
			$('.btnDATE_EXTENSION').removeClass('hide-me');
			$('.divDATE_EXTENSION').addClass('hide-me');
	    	$('.btnsDATE_EXTENSION').removeClass('hide-me');
	    	$('#watchDATE_EXTENSION').datepicker("destroy");
	    	$('#watchDATE_EXTENSION').prop('readonly', true);
		}
		if (json.TAKE_OFF_CTRL.length > 0) {
			$('#watchTAKE_OFF_CTRL').val(json.TAKE_OFF_CTRL);
			$('#watchTAKE_OFF_CTRL').datepicker("destroy");
			$('#watchTAKE_OFF_CTRL').prop('readonly', true);
		} else {
			$('#watchTAKE_OFF_CTRL').val("");
			$('.btnTAKE_OFF_CTRL').removeClass('hide-me');
			$('.divTAKE_OFF_CTRL').addClass('hide-me');
	    	$('.btnsTAKE_OFF_CTRL').removeClass('hide-me');
	    	$('#watchTAKE_OFF_CTRL').datepicker("destroy");
	    	$('#watchTAKE_OFF_CTRL').prop('readonly', true);
		}
	}).done(function() {
		$('.section.card').addClass('show');
		$('body').addClass('show');
		$('.back').addClass('show');
	}).complete(function() {

	});
}

//Обновление срока продления
function UpdateDATE_EXTENSION(CTRL_ID, DATE_EXTENSION) {
	$.post('update-date-extension', {
		CTRL_ID : CTRL_ID,
		DATE_EXTENSION : DATE_EXTENSION,
	}, function(data) {
		jsons = JSON.parse(data);
		if (jsons.length > 0) {
			for (var i = 0; i < jsons.length; i++){
				AddError(jsons[i]);
			}
		} else {
			$('.btnDATE_EXTENSION').addClass('hide-me');
			$('.btnsDATE_EXTENSION').addClass('hide-me');
	    	if(!$('.buttonEdit').hasClass('hide-me')) {
	    		$('#watchDATE_EXTENSION').datepicker("destroy");
		    	$('#watchDATE_EXTENSION').prop('readonly', true);
			}
		}
	}).done(function() {
		Search();
	}).complete(function() {

	});
}

//Обновление даты снятия с контроля
function UpdateTAKE_OFF_CTRL(CTRL_ID, TAKE_OFF_CTRL) {
	$.post('update-take-off-ctrl', {
		CTRL_ID : CTRL_ID,
		TAKE_OFF_CTRL : TAKE_OFF_CTRL,
	}, function(data) {
		jsons = JSON.parse(data);
		if (jsons.length > 0) {
			for (var i = 0; i < jsons.length; i++){
				AddError(jsons[i]);
			}
		} else {
			$('.btnTAKE_OFF_CTRL').addClass('hide-me');
			$('.btnsTAKE_OFF_CTRL').addClass('hide-me');
	    	if(!$('.buttonEdit').hasClass('hide-me')) {
	    		$('#watchTAKE_OFF_CTRL').datepicker("destroy");
		    	$('#watchTAKE_OFF_CTRL').prop('readonly', true);
			}
		}
	}).done(function() {
		Search();
	}).complete(function() {

	});
}

//Обновление карточки
function UpdateCard() {
	$('.new_error').remove();
	
	var CTRL_ID = $('#watchCTRL_ID').val();
	var NAME_DOC = $('#watchNAME_DOC').val();
	var DESC_DOC = $('#watchDESC_DOC').val();
	var MISSION = $('#watchMISSION').val();
	var CTRL_FACE = $('#watchCTRL_FACE').val();
	var EXECUTOR_1 = $('#watchEXECUTOR_1').val();
	var EXECUTOR_2 = $('#watchEXECUTOR_2').val();
	var EXECUTOR_3 = $('#watchEXECUTOR_3').val();
	var EXECUTOR_4 = $('#watchEXECUTOR_4').val();
	var DATE_EXEC = $('#watchDATE_EXEC').val();
	var DATE_CTRL = $('#watchDATE_CTRL').val();
	var DATE_EXTENSION = $('#watchDATE_EXTENSION').val();
	var TAKE_OFF_CTRL = $('#watchTAKE_OFF_CTRL').val();
	
	$.post('update-card', {
		CTRL_ID : CTRL_ID,
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
		DATE_EXTENSION : DATE_EXTENSION,
		TAKE_OFF_CTRL : TAKE_OFF_CTRL,
		
	}, function(data) {
		jsons = JSON.parse(data);
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
			p.innerHTML = "Контрольная карта успешно обновлена.";
			div.appendChild(p);
			errors.appendChild(div);
		
	    	SelectCard($('#watchCTRL_ID').val());;
		}
	}).done(function() {
		Search();
	}).complete(function() {

	});
}

//Добавление ошибки
function AddError(text) {
	var errors = document.getElementById('errors');
	var div = document.createElement('div');
	div.classList.add('new_error');
	var p = document.createElement('p');
	p.innerHTML = text;
	div.appendChild(p);
	errors.appendChild(div);
}

//Режим просмотра карточки
function CardWatch() {
	$('.section.card h3').text("Просмотр карточки");
	$('.section.card h3').removeClass('edit');
	
	$('.modal-close').removeClass('hide-me');
	$('.modal-edit').removeClass('hide-me');
	$('.modal-print').removeClass('hide-me');
	$('.modal-cansel').addClass('hide-me');
	$('.modal-save').addClass('hide-me');
	
	$('.edit-mode').removeClass("edit-mode");
	$('.input-error').removeClass('input-error');
	$('#watchNAME_DOC').prop('readonly', true);
	$('#watchDESC_DOC').prop('readonly', true);
	$('#watchMISSION').prop('readonly', true);
	$('#watchCTRL_FACE').prop('readonly', true);
	$('#watchEXECUTOR_1').prop('readonly', true);
	$('#watchEXECUTOR_2').prop('readonly', true);
	$('#watchEXECUTOR_3').prop('readonly', true);
	$('#watchEXECUTOR_4').prop('readonly', true);
	$('#watchDATE_EXEC').prop('readonly', true);
	$('#watchDATE_CTRL').prop('readonly', true);
	$('#watchDATE_EXTENSION').prop('readonly', true);
	$('#watchTAKE_OFF_CTRL').prop('readonly', true);
	$('#watchDATE_EXEC').datepicker("destroy");
	$('#watchDATE_CTRL').datepicker("destroy");
	$('#watchDATE_EXTENSION').datepicker("destroy");
	$('#watchTAKE_OFF_CTRL').datepicker("destroy");

}

//Режим редактирования карточки
function CardEdit() {
	$('.section.card h3').text("Редактирование карточки");
	$('.section.card h3').addClass('edit');

	$('.modal-close').addClass('hide-me');
	$('.modal-edit').addClass('hide-me');
	$('.modal-print').addClass('hide-me');
	$('.modal-cansel').removeClass('hide-me');
	$('.modal-save').removeClass('hide-me');
	
	$('.input-error').removeClass('input-error');
	
	$('#watchNAME_DOC').addClass("edit-mode");
	$('#watchDESC_DOC').addClass("edit-mode");
	$('#watchMISSION').addClass("edit-mode");
	$('#watchCTRL_FACE').addClass("edit-mode");
	$('#watchEXECUTOR_1').addClass("edit-mode");
	$('#watchEXECUTOR_2').addClass("edit-mode");
	$('#watchEXECUTOR_3').addClass("edit-mode");
	$('#watchEXECUTOR_4').addClass("edit-mode");
	$('#watchDATE_EXEC').addClass("edit-mode");
	$('#watchDATE_CTRL').addClass("edit-mode");
	$('#watchDATE_EXTENSION').addClass("edit-mode");
	$('#watchTAKE_OFF_CTRL').addClass("edit-mode");
	$('#watchDATE_EXEC').addClass("edit-mode");
	$('#watchDATE_CTRL').addClass("edit-mode");
	$('#watchDATE_EXTENSION').addClass("edit-mode");
	$('#watchTAKE_OFF_CTRL').addClass("edit-mode");
	
	$('#watchNAME_DOC').prop('readonly', false);
	$('#watchDESC_DOC').prop('readonly', false);
	$('#watchMISSION').prop('readonly', false);
	$('#watchCTRL_FACE').prop('readonly', false);
	$('#watchEXECUTOR_1').prop('readonly', false);
	$('#watchEXECUTOR_2').prop('readonly', false);
	$('#watchEXECUTOR_3').prop('readonly', false);
	$('#watchEXECUTOR_4').prop('readonly', false);
	$('#watchDATE_EXEC').prop('readonly', false);
	$('#watchDATE_CTRL').prop('readonly', false);
	$('#watchDATE_EXTENSION').prop('readonly', false);
	$('#watchTAKE_OFF_CTRL').prop('readonly', false);
	$('#watchDATE_EXEC').datepicker({changeMonth: true, changeYear: true});
	$('#watchDATE_CTRL').datepicker({changeMonth: true, changeYear: true});
	$('#watchDATE_EXTENSION').datepicker({changeMonth: true, changeYear: true});
	$('#watchTAKE_OFF_CTRL').datepicker({changeMonth: true, changeYear: true});
	
	$('.divDATE_EXTENSION').removeClass('hide-me');
	$('#watchDATE_EXTENSION').removeClass('hide-me');
	$('.btnDATE_EXTENSION').addClass('hide-me');
	$('.btnsDATE_EXTENSION').addClass('hide-me');
	
	$('.divTAKE_OFF_CTRL').removeClass('hide-me');
	$('#watchTAKE_OFF_CTRL').removeClass('hide-me');
	$('.btnTAKE_OFF_CTRL').addClass('hide-me');
	$('.btnsTAKE_OFF_CTRL').addClass('hide-me');
}

function ValidDate(date){ // date в формате 31.12.2014
	  var d_arr = date.split('.');
	  var d = new Date(d_arr[2]+'/'+d_arr[1]+'/'+d_arr[0]+''); // дата в формате 2014/12/31
	  if (d_arr[2]!=d.getFullYear() || d_arr[1]!=(d.getMonth() + 1) || d_arr[0]!=d.getDate()) {
	    return false; // неккоректная дата
	  };
	  return true;
}

//Ожидание событий JQuery
$(document).ready(function(){
	
	$('.input-date').on('change', function(e) {	
		if (ValidDate($('#'+e.target.id).val())) 
			$('#'+e.target.id).removeClass('input-error'); 
		else $('#'+e.target.id).addClass('input-error');
    });
	
	$("#btnExcel").click(function(){
		$('#formPrint').attr('action', 'excel');
		document.getElementById('formPrint').submit();
	});
	
	$("#btnPrint").click(function(){
		$('#formPrint').attr('action', 'print');
		document.getElementById('formPrint').submit();
	});

	$(".section.result .container").scroll(function(){
        $(".scroll").scrollLeft($(".section.result .container").scrollLeft());
    });
    $(".scroll").scroll(function(){
        $(".section.result .container").scrollLeft($(".scroll").scrollLeft());
    });
	
    $('.modal-print').click(function(){
    	document.getElementById('printCardForm').submit();
    });
    	
	//События кнопок равно ('=') в поиске
	$('#equallyDATE_EXEC').click(function(){
		NotRelevantSearch();
		$('#searchDATE_EXEC2').val($('#searchDATE_EXEC').val());
	});
	$('#equallyDATE_CTRL').click(function(){
		NotRelevantSearch();
		$('#searchDATE_CTRL2').val($('#searchDATE_CTRL').val());
	});
	$('#equallyDATE_EXTENSION').click(function(){
		NotRelevantSearch();
		$('#searchDATE_EXTENSION2').val($('#searchDATE_EXTENSION').val());
	});
	$('#search').on('click', '#equallyTAKE_OFF_CTRL', function(){ //Обработчик для динамически созданного элемента
		NotRelevantSearch();
		$('#searchTAKE_OFF_CTRL2').val($('#searchTAKE_OFF_CTRL').val());
		
	});
	
	//События кнопок очистить дату ('x') в поиске
	$('#clearDATE_EXEC').click(function(){
		NotRelevantSearch();
		$('#searchDATE_EXEC').val('');
		$('#searchDATE_EXEC2').val('');
		$('#searchDATE_EXEC.input-error').removeClass('input-error');
		$('#searchDATE_EXEC2.input-error').removeClass('input-error');
	});
	$('#clearDATE_CTRL').click(function(){
		NotRelevantSearch();
		$('#searchDATE_CTRL').val('');
		$('#searchDATE_CTRL2').val('');
		$('#searchDATE_CTRL.input-error').removeClass('input-error');
		$('#searchDATE_CTRL2.input-error').removeClass('input-error');
	});
	$('#clearDATE_EXTENSION').click(function(){
		NotRelevantSearch();
		$('#searchDATE_EXTENSION').val('');
		$('#searchDATE_EXTENSION2').val('');
		$('#searchDATE_EXTENSION.input-error').removeClass('input-error');
		$('#searchDATE_EXTENSION2.input-error').removeClass('input-error');
	});
	$('#search').on('click', '#clearTAKE_OFF_CTRL', function(){ //Обработчик для динамически созданного элемента
		NotRelevantSearch();
		$('#searchTAKE_OFF_CTRL').val('');
		$('#searchTAKE_OFF_CTRL2').val('');
		$('#searchTAKE_OFF_CTRL.input-error').removeClass('input-error');
		$('#searchTAKE_OFF_CTRL2.input-error').removeClass('input-error');
	});
	
	//Событие выбора 'Срок продления'
	$('#typeExtension').on('change', function (e) {
		if (!$('.section.all-search .container .search').hasClass('animate')) $('.section.all-search .container .search').addClass('animate');
    	if ($('#typeExtension').val() != 1) { //Продленные
    		$('#divExtension').removeClass('extension-open');
    		$('#search').removeClass('extension-open');
    	} else {
    		$('#divExtension').addClass('extension-open');
    		$('#search').addClass('extension-open');
    	}
    	NotRelevantSearch();
    });
	
	//Правый клик по строке
	$('#result').on('contextmenu', '.rowlink', function(){
    	if ($(this).find('.CTRL_ID').html() > 0) {
			if (show_more_id == $(this).find('.CTRL_ID').html()) {
				
			} else {
				$(document).find('.show-more').removeClass('show-more');
				$(this).addClass('show-more');
				show_more_id = $(this).find('.CTRL_ID').html();
			}
			$('.new_error').remove();
			SelectCard($(this).find('.CTRL_ID').html());
		}
    	return false; 
    });
    
	//Двойной клик по строке
    $('#result').on('dblclick', '.rowlink', function(){
    	if ($(this).find('.CTRL_ID').html() > 0) {
			if (show_more_id == $(this).find('.CTRL_ID').html()) {
				
			} else {
				$(document).find('.show-more').removeClass('show-more');
				$(this).addClass('show-more');
				show_more_id = $(this).find('.CTRL_ID').html();
			}
			$('.new_error').remove();
			SelectCard($(this).find('.CTRL_ID').html());;
		}
    	return false; 
    });
    
    //Левый клик по строке
    $('#result').on('click', '.rowlink', function(){
		if ($(this).find('.CTRL_ID').html() > 0) {
			if (show_more_id == $(this).find('.CTRL_ID').html()) {
			} else {
				$(document).find('.show-more').removeClass('show-more');
				$(this).addClass('show-more');
				show_more_id = $(this).find('.CTRL_ID').html();
			}
		}
	});
    
    //Закрытие модального окна
    $('.modal-close').click(function(){
    	$('.back').removeClass('show');
    	$('.section.card').removeClass('show');
    	$('body').removeClass('show');
    });
    
    //Закрытие модального окна
    $('.section.card').on('click', function(e){
    	if(e.target.className == "section card show") {
    		if (!$('.modal-close').hasClass('hide-me')){
    			$('.back').removeClass('show');
            	$('.section.card').removeClass('show');
            	$('body').removeClass('show');
    		}
    	}
    });
    
    //Закрытие модального окна
    $('.section.card').on('contextmenu', function(e){
    	if(e.target.className == "section card show") {
    		if (!$('.modal-close').hasClass('hide-me')){
    			$('.back').removeClass('show');
    			$('.section.card').removeClass('show');
    			$('body').removeClass('show');
    		}
    		return false; 
    	}
    });
    
    $('.btnDATE_EXTENSION').click(function(){
    	$('.btnDATE_EXTENSION').addClass('hide-me');
    	$('.divDATE_EXTENSION').removeClass('hide-me');
    	$('#watchDATE_EXTENSION').datepicker({changeMonth: true, changeYear: true});
    	$('#watchDATE_EXTENSION').prop('readonly', false);
    	$('.new_error').remove();
    });
    
    $('.canselDATE_EXTENSION').click(function(){
    	$('.btnDATE_EXTENSION').removeClass('hide-me');
    	$('.divDATE_EXTENSION').addClass('hide-me');
    	$('#watchDATE_EXTENSION').val('');
    	$('#watchDATE_EXTENSION').removeClass('input-error');
    	$('#watchDATE_EXTENSION').datepicker("destroy");
    	$('#watchDATE_EXTENSION').prop('readonly', true);
    	$('.new_error').remove();
    });
    
    $('.btnTAKE_OFF_CTRL').click(function(){
    	$('.btnTAKE_OFF_CTRL').addClass('hide-me');
    	$('.divTAKE_OFF_CTRL').removeClass('hide-me');
    	$('#watchTAKE_OFF_CTRL').datepicker({changeMonth: true, changeYear: true});
    	$('#watchTAKE_OFF_CTRL').prop('readonly', false);
    	$('.new_error').remove();
    });
    
    $('.canselTAKE_OFF_CTRL').click(function(){
    	$('.new_error').remove();
    	$('.btnTAKE_OFF_CTRL').removeClass('hide-me');
    	$('.divTAKE_OFF_CTRL').addClass('hide-me');
    	$('#watchTAKE_OFF_CTRL').val('');
    	$('#watchTAKE_OFF_CTRL').removeClass('input-error');
    	$('#watchTAKE_OFF_CTRL').datepicker("destroy");
    	$('#watchTAKE_OFF_CTRL').prop('readonly', true);
    	$('.new_error').remove();
    });
    
    $('.goDATE_EXTENSION').click(function(){
    	if($('#watchDATE_EXTENSION').val().length > 0) {
    		UpdateDATE_EXTENSION($('#watchCTRL_ID').val(), $('#watchDATE_EXTENSION').val());
    	} else {
    		$('.new_error').remove();
    		AddError("Поле 'Продлить срок' пусто.");
    	}
    });
    
    $('.goTAKE_OFF_CTRL').click(function(){
    	if($('#watchTAKE_OFF_CTRL').val().length > 0) {
    		UpdateTAKE_OFF_CTRL($('#watchCTRL_ID').val(), $('#watchTAKE_OFF_CTRL').val());
    	} else {
    		$('.new_error').remove();
    		AddError("Поле 'Снята с контроля' пусто.");
    	}
    });

    
    $('.modal-cansel').click(function(){
    	SelectCard($('#watchCTRL_ID').val());;
    	$('.new_error').remove();
    });
    
    $('.modal-save').click(function(){
		UpdateCard();
	});
    
    $('.modal-edit').click(function(){
		CardEdit();
		$('.new_error').remove();
	});
    
    //Открытие/закрытие выборки
    $('.search-head').click(function(){
        if (!$('.section.all-search .container .search').hasClass('animate')) $('.section.all-search .container .search').addClass('animate');
        if (!$(".section.all-search .container .search-head img").hasClass('animate')) $(".section.all-search .container .search-head img").addClass('animate');
		if ($('#search').hasClass('show')) ModeSearch(false);
		else ModeSearch(true);
	});
    
    //Событие изменение поля
    $('.input-text').on('change', function(e) {
    	NotRelevantSearch();
    });
    $('.input-date').on('change', function(e) {
    	NotRelevantSearch();
    });
    
    //Сортировки
    $('.headNAME_DOC').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'c.NAME_DOC ASC') {
    		el_field.value = 'c.NAME_DOC DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'c.NAME_DOC DESC') {
    			el_field.value = 'c.NAME_DOC ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'c.NAME_DOC ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    $('.headDESC_DOC').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'c.DESC_DOC ASC') {
    		el_field.value = 'c.DESC_DOC DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'c.DESC_DOC DESC') {
    			el_field.value = 'c.DESC_DOC ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'c.DESC_DOC ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    $('.headMISSION').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'c.MISSION ASC') {
    		el_field.value = 'c.MISSION DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'c.MISSION DESC') {
    			el_field.value = 'c.MISSION ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'c.MISSION ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    $('.headCTRL_FACE').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'ctrl.EXEC_USER ASC') {
    		el_field.value = 'ctrl.EXEC_USER DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'ctrl.EXEC_USER DESC') {
    			el_field.value = 'ctrl.EXEC_USER ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'ctrl.EXEC_USER ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    $('.headEXECUTORS').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'CONCAT(CONCAT(exec1.EXEC_USER, exec2.EXEC_USER), CONCAT(exec3.EXEC_USER, exec4.EXEC_USER)) ASC') {
    		el_field.value = 'CONCAT(CONCAT(exec1.EXEC_USER, exec2.EXEC_USER), CONCAT(exec3.EXEC_USER, exec4.EXEC_USER)) DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'CONCAT(CONCAT(exec1.EXEC_USER, exec2.EXEC_USER), CONCAT(exec3.EXEC_USER, exec4.EXEC_USER)) DESC') {
    			el_field.value = 'CONCAT(CONCAT(exec1.EXEC_USER, exec2.EXEC_USER), CONCAT(exec3.EXEC_USER, exec4.EXEC_USER)) ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'CONCAT(CONCAT(exec1.EXEC_USER, exec2.EXEC_USER), CONCAT(exec3.EXEC_USER, exec4.EXEC_USER)) ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    $('.headDATE_EXEC').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'c.DATE_EXEC ASC') {
    		el_field.value = 'c.DATE_EXEC DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'c.DATE_EXEC DESC') {
    			el_field.value = 'c.DATE_EXEC ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'c.DATE_EXEC ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    $('.headDATE_CTRL').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'c.DATE_CTRL ASC') {
    		el_field.value = 'c.DATE_CTRL DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'c.DATE_CTRL DESC') {
    			el_field.value = 'c.DATE_CTRL ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'c.DATE_CTRL ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    $('.headDATE_EXTENSION').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'c.DATE_EXTENSION ASC') {
    		el_field.value = 'c.DATE_EXTENSION DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'c.DATE_EXTENSION DESC') {
    			el_field.value = 'c.DATE_EXTENSION ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'c.DATE_EXTENSION ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});
    $('.headTAKE_OFF_CTRL').click(function(){
    	var el_field = document.getElementById('field_name');
    	if (el_field.value == 'c.TAKE_OFF_CTRL ASC') {
    		el_field.value = 'c.TAKE_OFF_CTRL DESC';
    		$(this).removeClass('asc');
    		$(this).addClass('desc')
    	} else {
    		if (el_field.value == 'c.TAKE_OFF_CTRL DESC') {
    			el_field.value = 'c.TAKE_OFF_CTRL ASC';
    			$(this).removeClass('desc');
    			$(this).addClass('asc')
    		} else {
    			el_field.value = 'c.TAKE_OFF_CTRL ASC';
    			ClearSort();
    			$(this).addClass('asc')
    		}
    	}
    	Search();
	});    
});