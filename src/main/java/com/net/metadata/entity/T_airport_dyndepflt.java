package com.net.metadata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 出港航班动态表 Entity
 */
public class T_airport_dyndepflt {

	private Long id;				//主键
	private String plandatetime;	//航班日期，格式 YYYY-MM-DD
	private String aircorp;			//航空公司 二字码
	private String fltno;			//航班号
	private String fstatus;			//航班状态
	private String planeno;			//机号
	private String planemdl;		//机型
	private String portno;			//机位号
	private String fltattr;			//航班属性
	private String flttype;			//航班性质
	private String tkfp;			//起飞地 中文
	private String pass1;			//经停地1中文
	private String pass2;			//经停地2中文
	private String arrp;			//到达地 中文
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date tkftm;				//计划起飞时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date arrtm;				//计划落地时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date ptkftm;			//预计起飞时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date parrtm;			//预计落地时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date rtkftm;			//实际起飞时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date rarrtm;			//实际落地时间
	private String prividerno;		//代理人编号
	private String build;			//航站楼
	private String airportid;		//机场三字码
	private String remartk;			//备注
	private String by1;				//备用1 下站国内  中文
	private String by2;				//备用2 下站国际  中文
	private String by3;				//备用3  起飞地  三字码
	private String by4;				//备用4  经停地1  三字码
	private String by5;				//备用5  经停地2  三字码
	private String by6;				//备用6  到达地  三字码
	private String by7;				//
	private String by8;
	private String by9;
	private String by10;

	public T_airport_dyndepflt() {
		super();
	}

	public T_airport_dyndepflt(Long id) {
		this.id = id;
	}

	public T_airport_dyndepflt(Long id, String plandatetime, String aircorp, String fltno, String fstatus, String planeno, String planemdl, String portno, String fltattr, String flttype, String tkfp, String pass1, String pass2, String arrp, Date tkftm, Date arrtm, Date ptkftm, Date parrtm, Date rtkftm, Date rarrtm, String prividerno, String build, String airportid, String remartk, String by1, String by2, String by3, String by4, String by5, String by6, String by7, String by8, String by9, String by10) {
		this.id = id;
		this.plandatetime = plandatetime;
		this.aircorp = aircorp;
		this.fltno = fltno;
		this.fstatus = fstatus;
		this.planeno = planeno;
		this.planemdl = planemdl;
		this.portno = portno;
		this.fltattr = fltattr;
		this.flttype = flttype;
		this.tkfp = tkfp;
		this.pass1 = pass1;
		this.pass2 = pass2;
		this.arrp = arrp;
		this.tkftm = tkftm;
		this.arrtm = arrtm;
		this.ptkftm = ptkftm;
		this.parrtm = parrtm;
		this.rtkftm = rtkftm;
		this.rarrtm = rarrtm;
		this.prividerno = prividerno;
		this.build = build;
		this.airportid = airportid;
		this.remartk = remartk;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.by4 = by4;
		this.by5 = by5;
		this.by6 = by6;
		this.by7 = by7;
		this.by8 = by8;
		this.by9 = by9;
		this.by10 = by10;
	}

	@Override
	public String toString() {
		return "T_airport_dyndepflt{" +
				"id=" + id +
				", plandatetime='" + plandatetime + '\'' +
				", aircorp='" + aircorp + '\'' +
				", fltno='" + fltno + '\'' +
				", fstatus='" + fstatus + '\'' +
				", planeno='" + planeno + '\'' +
				", planemdl='" + planemdl + '\'' +
				", portno='" + portno + '\'' +
				", fltattr='" + fltattr + '\'' +
				", flttype='" + flttype + '\'' +
				", tkfp='" + tkfp + '\'' +
				", pass1='" + pass1 + '\'' +
				", pass2='" + pass2 + '\'' +
				", arrp='" + arrp + '\'' +
				", tkftm=" + tkftm +
				", arrtm=" + arrtm +
				", ptkftm=" + ptkftm +
				", parrtm=" + parrtm +
				", rtkftm=" + rtkftm +
				", rarrtm=" + rarrtm +
				", prividerno='" + prividerno + '\'' +
				", build='" + build + '\'' +
				", airportid='" + airportid + '\'' +
				", remartk='" + remartk + '\'' +
				", by1='" + by1 + '\'' +
				", by2='" + by2 + '\'' +
				", by3='" + by3 + '\'' +
				", by4='" + by4 + '\'' +
				", by5='" + by5 + '\'' +
				", by6='" + by6 + '\'' +
				", by7='" + by7 + '\'' +
				", by8='" + by8 + '\'' +
				", by9='" + by9 + '\'' +
				", by10='" + by10 + '\'' +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlandatetime() {
		return plandatetime;
	}

	public void setPlandatetime(String plandatetime) {
		this.plandatetime = plandatetime;
	}

	public String getAircorp() {
		return aircorp;
	}

	public void setAircorp(String aircorp) {
		this.aircorp = aircorp;
	}

	public String getFltno() {
		return fltno;
	}

	public void setFltno(String fltno) {
		this.fltno = fltno;
	}

	public String getFstatus() {
		return fstatus;
	}

	public void setFstatus(String fstatus) {
		this.fstatus = fstatus;
	}

	public String getPlaneno() {
		return planeno;
	}

	public void setPlaneno(String planeno) {
		this.planeno = planeno;
	}

	public String getPlanemdl() {
		return planemdl;
	}

	public void setPlanemdl(String planemdl) {
		this.planemdl = planemdl;
	}

	public String getPortno() {
		return portno;
	}

	public void setPortno(String portno) {
		this.portno = portno;
	}

	public String getFltattr() {
		return fltattr;
	}

	public void setFltattr(String fltattr) {
		this.fltattr = fltattr;
	}

	public String getFlttype() {
		return flttype;
	}

	public void setFlttype(String flttype) {
		this.flttype = flttype;
	}

	public String getTkfp() {
		return tkfp;
	}

	public void setTkfp(String tkfp) {
		this.tkfp = tkfp;
	}

	public String getPass1() {
		return pass1;
	}

	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	public String getArrp() {
		return arrp;
	}

	public void setArrp(String arrp) {
		this.arrp = arrp;
	}

	public Date getTkftm() {
		return tkftm;
	}

	public void setTkftm(Date tkftm) {
		this.tkftm = tkftm;
	}

	public Date getArrtm() {
		return arrtm;
	}

	public void setArrtm(Date arrtm) {
		this.arrtm = arrtm;
	}

	public Date getPtkftm() {
		return ptkftm;
	}

	public void setPtkftm(Date ptkftm) {
		this.ptkftm = ptkftm;
	}

	public Date getParrtm() {
		return parrtm;
	}

	public void setParrtm(Date parrtm) {
		this.parrtm = parrtm;
	}

	public Date getRtkftm() {
		return rtkftm;
	}

	public void setRtkftm(Date rtkftm) {
		this.rtkftm = rtkftm;
	}

	public Date getRarrtm() {
		return rarrtm;
	}

	public void setRarrtm(Date rarrtm) {
		this.rarrtm = rarrtm;
	}

	public String getPrividerno() {
		return prividerno;
	}

	public void setPrividerno(String prividerno) {
		this.prividerno = prividerno;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getAirportid() {
		return airportid;
	}

	public void setAirportid(String airportid) {
		this.airportid = airportid;
	}

	public String getRemartk() {
		return remartk;
	}

	public void setRemartk(String remartk) {
		this.remartk = remartk;
	}

	public String getBy1() {
		return by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	public String getBy2() {
		return by2;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	public String getBy3() {
		return by3;
	}

	public void setBy3(String by3) {
		this.by3 = by3;
	}

	public String getBy4() {
		return by4;
	}

	public void setBy4(String by4) {
		this.by4 = by4;
	}

	public String getBy5() {
		return by5;
	}

	public void setBy5(String by5) {
		this.by5 = by5;
	}

	public String getBy6() {
		return by6;
	}

	public void setBy6(String by6) {
		this.by6 = by6;
	}

	public String getBy7() {
		return by7;
	}

	public void setBy7(String by7) {
		this.by7 = by7;
	}

	public String getBy8() {
		return by8;
	}

	public void setBy8(String by8) {
		this.by8 = by8;
	}

	public String getBy9() {
		return by9;
	}

	public void setBy9(String by9) {
		this.by9 = by9;
	}

	public String getBy10() {
		return by10;
	}

	public void setBy10(String by10) {
		this.by10 = by10;
	}
}
