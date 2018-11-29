package com.net.metadata.entity;

public class Place {
	private Long place_no;
    private String ESNAME;
    private String ICAO;
    private String ELNAME;
    private String CSNAME;
    private String CLNAME;
    private String CSSNAME;
    private String GJBZ;
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
	public Long getPlace_no() {
		return place_no;
	}
	public void setPlace_no(Long place_no) {
		this.place_no = place_no;
	}
	public String getESNAME() {
		return ESNAME;
	}
	public void setESNAME(String eSNAME) {
		ESNAME = eSNAME;
	}
	public String getICAO() {
		return ICAO;
	}
	public void setICAO(String iCAO) {
		ICAO = iCAO;
	}
	public String getELNAME() {
		return ELNAME;
	}
	public void setELNAME(String eLNAME) {
		ELNAME = eLNAME;
	}
	public String getCSNAME() {
		return CSNAME;
	}
	public void setCSNAME(String cSNAME) {
		CSNAME = cSNAME;
	}
	public String getCLNAME() {
		return CLNAME;
	}
	public void setCLNAME(String cLNAME) {
		CLNAME = cLNAME;
	}
	public String getCSSNAME() {
		return CSSNAME;
	}
	public void setCSSNAME(String cSSNAME) {
		CSSNAME = cSSNAME;
	}
	public String getGJBZ() {
		return GJBZ;
	}
	public void setGJBZ(String gJBZ) {
		GJBZ = gJBZ;
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
	public Place(Long place_no, String eSNAME, String iCAO, String eLNAME,
			String cSNAME, String cLNAME, String cSSNAME, String gJBZ,
			String bY1, String bY2, String bY3, String bY4, String bY5,
			String bY6, String bY7, String bY8, String bY9, String bY10) {
		super();
		this.place_no = place_no;
		ESNAME = eSNAME;
		ICAO = iCAO;
		ELNAME = eLNAME;
		CSNAME = cSNAME;
		CLNAME = cLNAME;
		CSSNAME = cSSNAME;
		GJBZ = gJBZ;
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
	public Place(String eSNAME, String iCAO, String eLNAME, String cSNAME,
			String cLNAME, String cSSNAME, String gJBZ, String bY1, String bY2,
			String bY3, String bY4, String bY5, String bY6, String bY7,
			String bY8, String bY9, String bY10) {
		super();
		ESNAME = eSNAME;
		ICAO = iCAO;
		ELNAME = eLNAME;
		CSNAME = cSNAME;
		CLNAME = cLNAME;
		CSSNAME = cSSNAME;
		GJBZ = gJBZ;
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
	public Place() {
		super();
	}
	
}
