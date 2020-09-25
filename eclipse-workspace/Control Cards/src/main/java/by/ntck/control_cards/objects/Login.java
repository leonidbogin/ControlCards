package by.ntck.control_cards.objects;

public class Login {
	
	private String login;
	private String error;
	
	public Login() {
	}
	
	public Login(String error) {
		this.error = error;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}	
}
