package by.ntck.control_cards.database.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CED_ADMIN")
public class CED_ADMIN {
	
	@Id
	@Column(name="ADMIN_ID")
	private long ADMIN_ID;
	
	@Column(name="ADMIN_LOGIN", nullable = false, length=64)
	private String ADMIN_LOGIN;
	
	public long getADMIN_ID() {
		return ADMIN_ID;
	}
	public void setADMIN_ID(long ADMIN_ID) {
		this.ADMIN_ID = ADMIN_ID;
	}
	public String getADMIN_LOGIN() {
		return ADMIN_LOGIN;
	}
	public void setADMIN_LOGIN(String ADMIN_LOGIN) {
		this.ADMIN_LOGIN = ADMIN_LOGIN;
	}	
}
