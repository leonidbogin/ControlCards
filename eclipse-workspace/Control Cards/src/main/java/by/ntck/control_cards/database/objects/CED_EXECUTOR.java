package by.ntck.control_cards.database.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CED_EXECUTOR")
public class CED_EXECUTOR {
	
	@Id
	@Column(name="EXEC_ID")
	private long EXEC_ID;
	
	@Column(name="EXEC_USER", nullable = true, length=256)
	private String EXEC_USER;
	
	@Column(name="EXEC_EXECUTOR_FLAG", nullable = false, length=32)
	private String EXEC_EXECUTOR_FLAG;
	
	@Column(name="EXEC_CTRL_FACE_FLAG", nullable = false, length=32)
	private String EXEC_CTRL_FACE_FLAG;

	public long getEXEC_ID() {
		return EXEC_ID;
	}
	public void setEXEC_ID(long eXEC_ID) {
		EXEC_ID = eXEC_ID;
	}
	public String getEXEC_USER() {
		return EXEC_USER;
	}
	public void setEXEC_USER(String EXEC_USER) {
		this.EXEC_USER = EXEC_USER;
	}
	public String getEXEC_EXECUTOR_FLAG() {
		return EXEC_EXECUTOR_FLAG;
	}
	public void setEXEC_EXECUTOR_FLAG(String eXEC_EXECUTOR_FLAG) {
		EXEC_EXECUTOR_FLAG = eXEC_EXECUTOR_FLAG;
	}
	public String getEXEC_CTRL_FACE_FLAG() {
		return EXEC_CTRL_FACE_FLAG;
	}
	public void setEXEC_CTRL_FACE_FLAG(String eXEC_CTRL_FACE_FLAG) {
		EXEC_CTRL_FACE_FLAG = eXEC_CTRL_FACE_FLAG;
	}

}
