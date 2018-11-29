package com.net.metadata.entity;

public class T_airport_chute {
	private Long ID;
	private String CODE;
	private String TYPE;
	private String STATUS;
	private String ATTR;
	private String RFID;
	private String REMARK;
	private String trailercode;//当前对应拖车编号
	private String personcode;//当前对应操作员编号
	private String BY1;
	private String BY2;
	private String BY3;
	private String BY4;
	private String BY5;
	private String BY6;
	private String BY7;
	private String BY8;
	private String BY9;
	private String BY10;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getATTR() {
		return ATTR;
	}
	public void setATTR(String aTTR) {
		ATTR = aTTR;
	}
	public String getRFID() {
		return RFID;
	}
	public void setRFID(String rFID) {
		RFID = rFID;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getTrailercode() {
		return trailercode;
	}
	public void setTrailercode(String trailercode) {
		this.trailercode = trailercode;
	}
	public String getPersoncode() {
		return personcode;
	}
	public void setPersoncode(String personcode) {
		this.personcode = personcode;
	}
	public String getBY1() {
		return BY1;
	}
	public void setBY1(String bY1) {
		BY1 = bY1;
	}
	public String getBY2() {
		return BY2;
	}
	public void setBY2(String bY2) {
		BY2 = bY2;
	}
	public String getBY3() {
		return BY3;
	}
	public void setBY3(String bY3) {
		BY3 = bY3;
	}
	public String getBY4() {
		return BY4;
	}
	public void setBY4(String bY4) {
		BY4 = bY4;
	}
	public String getBY5() {
		return BY5;
	}
	public void setBY5(String bY5) {
		BY5 = bY5;
	}
	public String getBY6() {
		return BY6;
	}
	public void setBY6(String bY6) {
		BY6 = bY6;
	}
	public String getBY7() {
		return BY7;
	}
	public void setBY7(String bY7) {
		BY7 = bY7;
	}
	public String getBY8() {
		return BY8;
	}
	public void setBY8(String bY8) {
		BY8 = bY8;
	}
	public String getBY9() {
		return BY9;
	}
	public void setBY9(String bY9) {
		BY9 = bY9;
	}
	public String getBY10() {
		return BY10;
	}
	public void setBY10(String bY10) {
		BY10 = bY10;
	}
	public T_airport_chute(Long iD, String cODE, String tYPE, String sTATUS,
			String aTTR, String rFID, String rEMARK, String trailercode,
			String personcode, String bY1, String bY2, String bY3, String bY4,
			String bY5, String bY6, String bY7, String bY8, String bY9,
			String bY10) {
		super();
		ID = iD;
		CODE = cODE;
		TYPE = tYPE;
		STATUS = sTATUS;
		ATTR = aTTR;
		RFID = rFID;
		REMARK = rEMARK;
		this.trailercode = trailercode;
		this.personcode = personcode;
		BY1 = bY1;
		BY2 = bY2;
		BY3 = bY3;
		BY4 = bY4;
		BY5 = bY5;
		BY6 = bY6;
		BY7 = bY7;
		BY8 = bY8;
		BY9 = bY9;
		BY10 = bY10;
	}
	public T_airport_chute() {
		super();
	}
	public T_airport_chute(String cODE, String tYPE, String sTATUS,
			String aTTR, String rFID, String rEMARK, String trailercode,
			String personcode, String bY1, String bY2, String bY3, String bY4,
			String bY5, String bY6, String bY7, String bY8, String bY9,
			String bY10) {
		super();
		CODE = cODE;
		TYPE = tYPE;
		STATUS = sTATUS;
		ATTR = aTTR;
		RFID = rFID;
		REMARK = rEMARK;
		this.trailercode = trailercode;
		this.personcode = personcode;
		BY1 = bY1;
		BY2 = bY2;
		BY3 = bY3;
		BY4 = bY4;
		BY5 = bY5;
		BY6 = bY6;
		BY7 = bY7;
		BY8 = bY8;
		BY9 = bY9;
		BY10 = bY10;
	}
}
