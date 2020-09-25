package by.ntck.control_cards.objects;

public class Session {

	private Boolean login;
	private String page_name;
	private Boolean search_open;
	
	public Session() {
		login = false;
		search_open = false;
	}
	
	public Boolean getLogin() {
		return login;
	}
	public void Login() {
		this.login = true;
	}
	public void Logout() {
		this.login = false;
	}

	public String getPage_name() {
		return page_name;
	}

	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}

	public Boolean getSearch_open() {
		return search_open;
	}

	public void setSearch_open(Boolean search_open) {
		this.search_open = search_open;
	}
}
