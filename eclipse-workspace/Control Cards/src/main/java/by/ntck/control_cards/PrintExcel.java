package by.ntck.control_cards;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import by.ntck.control_cards.objects.cards.CardString;

public class PrintExcel {
	public static void WriteIntoExcel(List<CardString> cards, HttpServletResponse response) { 
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet("Control Cards");

		// Нумерация начинается с нуля
		Row row = sheet.createRow(0);
		row.setHeightInPoints(20.0f);
		org.apache.poi.ss.usermodel.Font font = book.createFont();
		font.setFontHeightInPoints((short)11);
		font.setFontName("Arial");
		font.setItalic(false);
		font.setStrikeout(false);
		font.setBold(true);
		// цвет шрифта
//		font.setColor(new Color(new java.awt.Color(16,64,255)));

		CellStyle cellStyleHead = book.createCellStyle();
		cellStyleHead.setAlignment(HorizontalAlignment.CENTER);
	    cellStyleHead.setVerticalAlignment(VerticalAlignment.CENTER);
	    cellStyleHead.setFont(font);
	    
		Cell name_doc = row.createCell(0);
		name_doc.setCellStyle(cellStyleHead);
		name_doc.setCellValue("Наименование документа");
		Cell desc_doc = row.createCell(1);
		desc_doc.setCellValue("Описание документа");
		desc_doc.setCellStyle(cellStyleHead);
		Cell mission = row.createCell(2);
    	mission.setCellValue("Содержание поручения");
    	mission.setCellStyle(cellStyleHead);
    	Cell strCtrl_face = row.createCell(3);
		strCtrl_face.setCellValue("Контролирующее лицо");
		strCtrl_face.setCellStyle(cellStyleHead);
		Cell strExecutor1 = row.createCell(4);
		strExecutor1.setCellValue("Исполнитель 1");
		strExecutor1.setCellStyle(cellStyleHead);
		Cell strExecutor2 = row.createCell(5);
		strExecutor2.setCellValue("Исполнитель 2");
		strExecutor2.setCellStyle(cellStyleHead);
		Cell strExecutor3 = row.createCell(6);
		strExecutor3.setCellValue("Исполнитель 3");
		strExecutor3.setCellStyle(cellStyleHead);
		Cell strExecutor4 = row.createCell(7);
		strExecutor4.setCellValue("Исполнитель 4");
		strExecutor4.setCellStyle(cellStyleHead);
    	Cell strDate_exec = row.createCell(8);
    	strDate_exec.setCellValue("Поставлена на контроль");
    	strDate_exec.setCellStyle(cellStyleHead);
    	Cell strDate_ctrl = row.createCell(9);
    	strDate_ctrl.setCellValue("Срок выполнения");
    	strDate_ctrl.setCellStyle(cellStyleHead);
    	Cell strDate_extension = row.createCell(10);
    	strDate_extension.setCellValue("Срок продления");
    	strDate_extension.setCellStyle(cellStyleHead);
    	Cell strTake_off_ctrl = row.createCell(11);
    	strTake_off_ctrl.setCellValue("Срок выполнения");
    	strTake_off_ctrl.setCellStyle(cellStyleHead);
    	Cell strCtrl_id = row.createCell(12);
    	strCtrl_id.setCellValue("id");
    	strCtrl_id.setCellStyle(cellStyleHead);
    	
	    sheet.setColumnWidth(0,(int)(40 * 1.14388) * 256);        
	    sheet.setColumnWidth(1,(int)(40 * 1.14388) * 256); 
	    sheet.setColumnWidth(2,(int)(40 * 1.14388) * 256); 
	    sheet.autoSizeColumn(3);
	    sheet.autoSizeColumn(4);
	    sheet.autoSizeColumn(5);
	    sheet.autoSizeColumn(6);
	    sheet.autoSizeColumn(7);
	    sheet.autoSizeColumn(8);
	    sheet.autoSizeColumn(9);
	    sheet.autoSizeColumn(10);
	    sheet.autoSizeColumn(11);
	    sheet.autoSizeColumn(12);
	    
	    CellStyle cellStyleCenter = book.createCellStyle();
	    cellStyleCenter.setAlignment(HorizontalAlignment.CENTER);
	    cellStyleCenter.setVerticalAlignment(VerticalAlignment.TOP);
	    
	    CellStyle cellStyleLeft = book.createCellStyle();
	    cellStyleLeft.setAlignment(HorizontalAlignment.LEFT);
	    cellStyleLeft.setVerticalAlignment(VerticalAlignment.TOP);
	    
	    for (int i = 0; i < cards.size(); i++) {
	    	Row rowN = sheet.createRow(i + 1);
	    	rowN.setHeightInPoints(15.0f);
	    	Cell name_docN = rowN.createCell(0);
	        name_docN.setCellValue(cards.get(i).getNAME_DOC());
	        name_docN.setCellStyle(cellStyleLeft);
	        Cell desc_docN = rowN.createCell(1);
	        desc_docN.setCellValue(cards.get(i).getDESC_DOC());
	        desc_docN.setCellStyle(cellStyleLeft);
	        Cell missionN = rowN.createCell(2);
	        if (cards.get(i).getMISSION() != null)
	        	missionN.setCellValue(cards.get(i).getMISSION());
	        else missionN.setCellValue("");
	        missionN.setCellStyle(cellStyleLeft);
	        Cell strCtrl_faceN = rowN.createCell(3);
	        strCtrl_faceN.setCellValue(cards.get(i).getCTRL_FACE());
	        strCtrl_faceN.setCellStyle(cellStyleLeft);
	        Cell strExecutor1N = rowN.createCell(4);
	        strExecutor1N.setCellValue(cards.get(i).getEXECUTOR_1());
	        strExecutor1N.setCellStyle(cellStyleLeft);
	        Cell strExecutor2N = rowN.createCell(5);
	        strExecutor2N.setCellValue(cards.get(i).getEXECUTOR_2());
	        strExecutor2N.setCellStyle(cellStyleLeft);
	        Cell strExecutor3N = rowN.createCell(6);
	        strExecutor3N.setCellValue(cards.get(i).getEXECUTOR_3());
	        strExecutor3N.setCellStyle(cellStyleLeft);
	        Cell strExecutor4N = rowN.createCell(7);
	        strExecutor4N.setCellValue(cards.get(i).getEXECUTOR_4());
	        strExecutor4N.setCellStyle(cellStyleLeft);
	        Cell strDate_execN = rowN.createCell(8);
	        strDate_execN.setCellValue(cards.get(i).getDATE_CTRL());
	        strDate_execN.setCellStyle(cellStyleCenter);
	        Cell strDate_ctrlN = rowN.createCell(9);
	        strDate_ctrlN.setCellValue(cards.get(i).getDATE_EXEC());
	        strDate_ctrlN.setCellStyle(cellStyleCenter);
	        Cell strDate_extensionN = rowN.createCell(10);
	        if (cards.get(i).getDATE_EXTENSION() != null)
	        	strDate_extensionN.setCellValue(cards.get(i).getDATE_EXTENSION());
	        else strDate_extensionN.setCellValue("");
	        strDate_extensionN.setCellStyle(cellStyleCenter);
	        Cell strTake_off_ctrlN = rowN.createCell(11);
	        if (cards.get(i).getTAKE_OFF_CTRL() != null)
	        	strTake_off_ctrlN.setCellValue(cards.get(i).getTAKE_OFF_CTRL());
	        else strTake_off_ctrlN.setCellValue("");
	        strTake_off_ctrlN.setCellStyle(cellStyleCenter);
	        Cell strCtrl_idN = rowN.createCell(12);
	        strCtrl_idN.setCellValue(cards.get(i).getCTRL_ID());
	        strCtrl_idN.setCellStyle(cellStyleCenter);
	    }

	    ByteArrayOutputStream bos2 = new ByteArrayOutputStream(); 
	    try {
			book.write(bos2);
			response.setContentLength(bos2.size()); 
		    response.setContentType("application/vnd.ms-excel");
		    Date date = new Date();
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
		    response.setHeader("Content-disposition", "attachment;filename=ControlCards ("+ df.format(date) +").xls");
		    book.write(response.getOutputStream());
		    book.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
