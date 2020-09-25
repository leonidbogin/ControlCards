package by.ntck.control_cards.database.objects;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CED_CTRL_CARD")
public class CED_CTRL_CARD {
	
	@Id
	@Column(name="CTRL_ID")
	private long CTRL_ID;
	
	@Column(name="NAME_DOC", length=256) 
	private String NAME_DOC;
	
	@Column(name="DESC_DOC", length=256)
	private String DESC_DOC;
	
	@Column(name="CTRL_FACE")
	private long CTRL_FACE;
	
	@Column(name="EXECUTOR_1")
	private long EXECUTOR_1;
	
	@Column(name="EXECUTOR_2")
	private long EXECUTOR_2;
	
	@Column(name="EXECUTOR_3")
	private long EXECUTOR_3;
	
	@Column(name="EXECUTOR_4")
	private long EXECUTOR_4;
	
	@Column(name="DATE_EXEC", nullable = false)
	private Date DATE_EXEC;
	
	@Column(name="DATE_CTRL", nullable = false)
	private Date DATE_CTRL;
	
	@Column(name="MISSION", length=512)
	private String MISSION;
	
	@Column(name="DATE_EXTENSION")
	private Date DATE_EXTENSION;
	
	@Column(name="TAKE_OFF_CTRL")
	private Date TAKE_OFF_CTRL;
	
	public long getCTRL_ID() {
		return CTRL_ID;
	}
	public void setCTRL_ID(long cTRL_ID) {
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
	public long getCTRL_FACE() {
		return CTRL_FACE;
	}
	public void setCTRL_FACE(long cTRL_FACE) {
		CTRL_FACE = cTRL_FACE;
	}
	public long getEXECUTOR_1() {
		return EXECUTOR_1;
	}
	public void setEXECUTOR_1(long eXECUTOR_1) {
		EXECUTOR_1 = eXECUTOR_1;
	}
	public long getEXECUTOR_2() {
		return EXECUTOR_2;
	}
	public void setEXECUTOR_2(long eXECUTOR_2) {
		EXECUTOR_2 = eXECUTOR_2;
	}
	public long getEXECUTOR_3() {
		return EXECUTOR_3;
	}
	public void setEXECUTOR_3(long eXECUTOR_3) {
		EXECUTOR_3 = eXECUTOR_3;
	}
	public long getEXECUTOR_4() {
		return EXECUTOR_4;
	}
	public void setEXECUTOR_4(long eXECUTOR_4) {
		EXECUTOR_4 = eXECUTOR_4;
	}
	public Date getDATE_EXEC() {
		return DATE_EXEC;
	}
	public void setDATE_EXEC(Date dATE_EXEC) {
		DATE_EXEC = dATE_EXEC;
	}
	public Date getDATE_CTRL() {
		return DATE_CTRL;
	}
	public void setDATE_CTRL(Date dATE_CTRL) {
		DATE_CTRL = dATE_CTRL;
	}
	public String getMISSION() {
		return MISSION;
	}
	public void setMISSION(String mISSION) {
		MISSION = mISSION;
	}
	public Date getDATE_EXTENSION() {
		return DATE_EXTENSION;
	}
	public void setDATE_EXTENSION(Date dATE_EXTENSION) {
		DATE_EXTENSION = dATE_EXTENSION;
	}
	public Date getTAKE_OFF_CTRL() {
		return TAKE_OFF_CTRL;
	}
	public void setTAKE_OFF_CTRL(Date tAKE_OFF_CTRL) {
		TAKE_OFF_CTRL = tAKE_OFF_CTRL;
	}
}
