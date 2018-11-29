package com.net.metadata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class T_airport_dynchute {

	private Long ID;
	private String PLANDATE;
	private String AIRCORP;
	private String FLTNO;
	private String FLTATTR;
	private String FLTTYPE;
	private String CODE;
	private String ATTR;
	private String STATUS;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date STM ;//资源名称
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date ETM;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date PSTM;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date PETM;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date RSTM;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date RETM;
	private String TRAINLERCODE;
	private String PERSONCODE;
	private String REMARTK;
	private String by1;
	private String by2;
	private String by3;
	private String by4;
	private String by5;
	private String by6;
	private String by7;
	private String by8;
	private String by9;
	private String by10;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getPLANDATE() {
		return PLANDATE;
	}
	public void setPLANDATE(String pLANDATE) {
		PLANDATE = pLANDATE;
	}
	public String getAIRCORP() {
		return AIRCORP;
	}
	public void setAIRCORP(String aIRCORP) {
		AIRCORP = aIRCORP;
	}
	public String getFLTNO() {
		return FLTNO;
	}
	public void setFLTNO(String fLTNO) {
		FLTNO = fLTNO;
	}
	public String getFLTATTR() {
		return FLTATTR;
	}
	public void setFLTATTR(String fLTATTR) {
		FLTATTR = fLTATTR;
	}
	public String getFLTTYPE() {
		return FLTTYPE;
	}
	public void setFLTTYPE(String fLTTYPE) {
		FLTTYPE = fLTTYPE;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getATTR() {
		return ATTR;
	}
	public void setATTR(String aTTR) {
		ATTR = aTTR;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public Date getSTM() {
		return STM;
	}
	public void setSTM(Date sTM) {
		STM = sTM;
	}
	public Date getETM() {
		return ETM;
	}
	public void setETM(Date eTM) {
		ETM = eTM;
	}
	public Date getPSTM() {
		return PSTM;
	}
	public void setPSTM(Date pSTM) {
		PSTM = pSTM;
	}
	public Date getPETM() {
		return PETM;
	}
	public void setPETM(Date pETM) {
		PETM = pETM;
	}
	public Date getRSTM() {
		return RSTM;
	}
	public void setRSTM(Date rSTM) {
		RSTM = rSTM;
	}
	public Date getRETM() {
		return RETM;
	}
	public void setRETM(Date rETM) {
		RETM = rETM;
	}
	public String getTRAINLERCODE() {
		return TRAINLERCODE;
	}
	public void setTRAINLERCODE(String tRAINLERCODE) {
		TRAINLERCODE = tRAINLERCODE;
	}
	public String getPERSONCODE() {
		return PERSONCODE;
	}
	public void setPERSONCODE(String pERSONCODE) {
		PERSONCODE = pERSONCODE;
	}
	public String getREMARTK() {
		return REMARTK;
	}
	public void setREMARTK(String rEMARTK) {
		REMARTK = rEMARTK;
	}
	public String getBy1() {
		return by1;
	}
	public String getBy2() {
		return by2;
	}
	public String getBy3() {
		return by3;
	}
	public String getBy4() {
		return by4;
	}
	public String getBy5() {
		return by5;
	}
	public String getBy6() {
		return by6;
	}
	public String getBy7() {
		return by7;
	}
	public String getBy8() {
		return by8;
	}
	public String getBy9() {
		return by9;
	}
	public String getBy10() {
		return by10;
	}
	public void setBy1(String by1) {
		this.by1 = by1;
	}
	public void setBy2(String by2) {
		this.by2 = by2;
	}
	public void setBy3(String by3) {
		this.by3 = by3;
	}
	public void setBy4(String by4) {
		this.by4 = by4;
	}
	public void setBy5(String by5) {
		this.by5 = by5;
	}
	public void setBy6(String by6) {
		this.by6 = by6;
	}
	public void setBy7(String by7) {
		this.by7 = by7;
	}
	public void setBy8(String by8) {
		this.by8 = by8;
	}
	public void setBy9(String by9) {
		this.by9 = by9;
	}
	public void setBy10(String by10) {
		this.by10 = by10;
	}
	
}
