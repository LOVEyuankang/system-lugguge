package com.net.metadata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Brs_luggage_view implements Serializable {
    private static final long serialVersionUID = 6700813629656881143L;

    private Long id;

    private String plandate;

    private String aircorp;

    private String fltno;

    private String percode;

    private String bgcode;

    private String type;

    private String attr;

    private BigDecimal bgweight;

    private String bgphoto;

    private String bgstatus;

    private String trailercode;

    private String personcode;

    private String chutecode;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trailerTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date outTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inCabinTime;

    private String inCabinEmployeeId;

    private String cationArea;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date outCabinTime;

    private String outCabinEmployeeId;

    private String bglevel;

    private String chkdesk;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inChkTime;

    private String remartk;

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

    private String lkname;

    private String opname;
    
    private String flightno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlandate() {
        return plandate;
    }

    public void setPlandate(String plandate) {
        this.plandate = plandate == null ? null : plandate.trim();
    }

    public String getAircorp() {
        return aircorp;
    }

    public void setAircorp(String aircorp) {
        this.aircorp = aircorp == null ? null : aircorp.trim();
    }

    public String getFltno() {
        return fltno;
    }

    public void setFltno(String fltno) {
        this.fltno = fltno == null ? null : fltno.trim();
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode == null ? null : percode.trim();
    }

    public String getBgcode() {
        return bgcode;
    }

    public void setBgcode(String bgcode) {
        this.bgcode = bgcode == null ? null : bgcode.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr == null ? null : attr.trim();
    }

    public BigDecimal getBgweight() {
        return bgweight;
    }

    public void setBgweight(BigDecimal bgweight) {
        this.bgweight = bgweight;
    }

    public String getBgphoto() {
        return bgphoto;
    }

    public void setBgphoto(String bgphoto) {
        this.bgphoto = bgphoto == null ? null : bgphoto.trim();
    }

    public String getBgstatus() {
        return bgstatus;
    }

    public void setBgstatus(String bgstatus) {
        this.bgstatus = bgstatus == null ? null : bgstatus.trim();
    }

    public String getTrailercode() {
        return trailercode;
    }

    public void setTrailercode(String trailercode) {
        this.trailercode = trailercode == null ? null : trailercode.trim();
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode == null ? null : personcode.trim();
    }

    public String getChutecode() {
        return chutecode;
    }

    public void setChutecode(String chutecode) {
        this.chutecode = chutecode == null ? null : chutecode.trim();
    }

    public Date getTrailerTime() {
        return trailerTime;
    }

    public void setTrailerTime(Date trailerTime) {
        this.trailerTime = trailerTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getInCabinTime() {
        return inCabinTime;
    }

    public void setInCabinTime(Date inCabinTime) {
        this.inCabinTime = inCabinTime;
    }

    public String getInCabinEmployeeId() {
        return inCabinEmployeeId;
    }

    public void setInCabinEmployeeId(String inCabinEmployeeId) {
        this.inCabinEmployeeId = inCabinEmployeeId == null ? null : inCabinEmployeeId.trim();
    }

    public String getCationArea() {
        return cationArea;
    }

    public void setCationArea(String cationArea) {
        this.cationArea = cationArea == null ? null : cationArea.trim();
    }

    public Date getOutCabinTime() {
        return outCabinTime;
    }

    public void setOutCabinTime(Date outCabinTime) {
        this.outCabinTime = outCabinTime;
    }

    public String getOutCabinEmployeeId() {
        return outCabinEmployeeId;
    }

    public void setOutCabinEmployeeId(String outCabinEmployeeId) {
        this.outCabinEmployeeId = outCabinEmployeeId == null ? null : outCabinEmployeeId.trim();
    }

    public String getBglevel() {
        return bglevel;
    }

    public void setBglevel(String bglevel) {
        this.bglevel = bglevel == null ? null : bglevel.trim();
    }

    public String getChkdesk() {
        return chkdesk;
    }

    public void setChkdesk(String chkdesk) {
        this.chkdesk = chkdesk == null ? null : chkdesk.trim();
    }

    public Date getInChkTime() {
        return inChkTime;
    }

    public void setInChkTime(Date inChkTime) {
        this.inChkTime = inChkTime;
    }

    public String getRemartk() {
        return remartk;
    }

    public void setRemartk(String remartk) {
        this.remartk = remartk == null ? null : remartk.trim();
    }

    public String getBy1() {
        return by1;
    }

    public void setBy1(String by1) {
        this.by1 = by1 == null ? null : by1.trim();
    }

    public String getBy2() {
        return by2;
    }

    public void setBy2(String by2) {
        this.by2 = by2 == null ? null : by2.trim();
    }

    public String getBy3() {
        return by3;
    }

    public void setBy3(String by3) {
        this.by3 = by3 == null ? null : by3.trim();
    }

    public String getBy4() {
        return by4;
    }

    public void setBy4(String by4) {
        this.by4 = by4 == null ? null : by4.trim();
    }

    public String getBy5() {
        return by5;
    }

    public void setBy5(String by5) {
        this.by5 = by5 == null ? null : by5.trim();
    }

    public String getBy6() {
        return by6;
    }

    public void setBy6(String by6) {
        this.by6 = by6 == null ? null : by6.trim();
    }

    public String getBy7() {
        return by7;
    }

    public void setBy7(String by7) {
        this.by7 = by7 == null ? null : by7.trim();
    }

    public String getBy8() {
        return by8;
    }

    public void setBy8(String by8) {
        this.by8 = by8 == null ? null : by8.trim();
    }

    public String getBy9() {
        return by9;
    }

    public void setBy9(String by9) {
        this.by9 = by9 == null ? null : by9.trim();
    }

    public String getBy10() {
        return by10;
    }

    public void setBy10(String by10) {
        this.by10 = by10 == null ? null : by10.trim();
    }

    public String getLkname() {
        return lkname;
    }

    public void setLkname(String lkname) {
        this.lkname = lkname == null ? null : lkname.trim();
    }

    public String getOpname() {
        return opname;
    }

    public void setOpname(String opname) {
        this.opname = opname == null ? null : opname.trim();
    }

	public String getFlightno() {
		return flightno;
	}

	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}
}