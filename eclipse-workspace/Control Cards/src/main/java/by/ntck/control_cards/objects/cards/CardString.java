package by.ntck.control_cards.objects.cards;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CardString {
	
	private String CTRL_ID;
	private String NAME_DOC;
	private String DESC_DOC;
	private String CTRL_FACE;
	private String EXECUTOR_1;
	private String EXECUTOR_2;
	private String EXECUTOR_3;
	private String EXECUTOR_4;
	private String DATE_EXEC;
	private String DATE_CTRL;
	private String MISSION;
	private String DATE_EXTENSION;
	private String TAKE_OFF_CTRL;
	
	private String EXECUTORS;
	
	//new CardString from CED_CTRL_CARD
	public CardString(Object[] row) {
		setCTRL_ID(ObjToStr(row[0]));
		setNAME_DOC(ObjToStr(row[1]));
		setDESC_DOC(ObjToStr(row[2]));
		setCTRL_FACE(ObjToStr(row[3]));		
		setEXECUTOR_1(ObjToStr(row[4]));	
		setEXECUTOR_2(ObjToStr(row[5]));
		setEXECUTOR_3(ObjToStr(row[6]));
		setEXECUTOR_4(ObjToStr(row[7]));
		setDATE_EXEC(ObjDateToStr(row[8]));
		setDATE_CTRL(ObjDateToStr(row[9]));
		setMISSION(ObjToStr(row[10]));
		setDATE_EXTENSION(ObjDateToStr(row[11]));
		setTAKE_OFF_CTRL(ObjDateToStr(row[12]));
		
		setEXECUTORS("");
		if (getEXECUTOR_4().length() > 0) {
			if (getEXECUTOR_3().length() + getEXECUTOR_2().length() + getEXECUTOR_1().length() > 0)
				setEXECUTORS(";\n" + getEXECUTOR_4());
			else setEXECUTORS(getEXECUTOR_4());
		}
		if (getEXECUTOR_3().length() > 0) {
			if (getEXECUTOR_2().length() + getEXECUTOR_1().length() > 0)
				setEXECUTORS(";\n" + getEXECUTOR_3() + getEXECUTORS());
			else setEXECUTORS(getEXECUTOR_3() + getEXECUTORS());
		}
		if (getEXECUTOR_2().length() > 0) {
			if (getEXECUTOR_1().length() > 0)
				setEXECUTORS(";\n" + getEXECUTOR_2() + getEXECUTORS());
			else setEXECUTORS(getEXECUTOR_2() + getEXECUTORS());
		}
		if (getEXECUTOR_1().length() > 0) {
			setEXECUTORS(getEXECUTOR_1() + getEXECUTORS());
		}
	}
	
	private String ObjToStr(Object obj) {
		if (obj != null) return obj.toString();
		return "";
	}
	
	private String ObjDateToStr(Object obj) {
		if(obj != null) {
			String pattern = "dd.MM.yyyy";
			DateFormat df = new SimpleDateFormat(pattern);
			return df.format(obj);
		} else return "";	
	}
	
	//getters and setters
	public String getCTRL_ID() {
		return CTRL_ID;
	}
	public void setCTRL_ID(String cTRL_ID) {
		CTRL_ID = cTRL_ID;
	}
	public String getNAME_DOC() {
		return NAME_DOC;
	}
	public void setNAME_DOC(String nAME_DOC) {
		NAME_DOC = nAME_DOC;
	}
	public String getDESC_DOC() {
		return DESC_DOC;
	}
	public void setDESC_DOC(String dESC_DOC) {
		DESC_DOC = dESC_DOC;
	}
	public String getCTRL_FACE() {
		return CTRL_FACE;
	}
	public void setCTRL_FACE(String cTRL_FACE) {
		CTRL_FACE = cTRL_FACE;
	}
	public String getEXECUTOR_1() {
		return EXECUTOR_1;
	}
	public void setEXECUTOR_1(String eXECUTOR_1) {
		EXECUTOR_1 = eXECUTOR_1;
	}
	public String getEXECUTOR_2() {
		return EXECUTOR_2;
	}
	public void setEXECUTOR_2(String eXECUTOR_2) {
		EXECUTOR_2 = eXECUTOR_2;
	}
	public String getEXECUTOR_3() {
		return EXECUTOR_3;
	}
	public void setEXECUTOR_3(String eXECUTOR_3) {
		EXECUTOR_3 = eXECUTOR_3;
	}
	public String getEXECUTOR_4() {
		return EXECUTOR_4;
	}
	public void setEXECUTOR_4(String eXECUTOR_4) {
		EXECUTOR_4 = eXECUTOR_4;
	}
	public String getDATE_EXEC() {
		return DATE_EXEC;
	}
	public void setDATE_EXEC(String dATE_EXEC) {
		DATE_EXEC = dATE_EXEC;
	}
	public String getDATE_CTRL() {
		return DATE_CTRL;
	}
	public void setDATE_CTRL(String dATE_CTRL) {
		DATE_CTRL = dATE_CTRL;
	}
	public String getMISSION() {
		return MISSION;
	}
	public void setMISSION(String mISSION) {
		MISSION = mISSION;
	}
	public String getDATE_EXTENSION() {
		return DATE_EXTENSION;
	}
	public void setDATE_EXTENSION(String dATE_EXTENSION) {
		DATE_EXTENSION = dATE_EXTENSION;
	}
	public String getTAKE_OFF_CTRL() {
		return TAKE_OFF_CTRL;
	}
	public void setTAKE_OFF_CTRL(String tAKE_OFF_CTRL) {
		TAKE_OFF_CTRL = tAKE_OFF_CTRL;
	}
	public String getEXECUTORS() {
		return EXECUTORS;
	}
	public void setEXECUTORS(String eXECUTORS) {
		EXECUTORS = eXECUTORS;
	}
	
}
