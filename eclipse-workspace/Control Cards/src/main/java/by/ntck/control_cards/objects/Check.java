package by.ntck.control_cards.objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import by.ntck.control_cards.database.objects.CED_CTRL_CARD;

public class Check {
	private List<String> errors;
	private CED_CTRL_CARD card;
	
	public Check(List<String> errors, CED_CTRL_CARD card) {
		this.errors = errors;
		this.card = card;
	}
	
	public Check() {
		errors = new ArrayList<String>();
		card = new CED_CTRL_CARD();
	}
	
	@SuppressWarnings("serial")
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy") {{ setLenient(false); }};
	public static boolean isValidDate(String date) {	
		try {
			sdf.parse(date);
			return true;
		} catch (Exception e) {}
		return false;
	}
	
	public void addErrors(String error) {
		errors.add(error);
	}
	
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public CED_CTRL_CARD getCard() {
		return card;
	}
	public void setCard(CED_CTRL_CARD card) {
		this.card = card;
	}
}
