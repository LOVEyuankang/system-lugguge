package com.net.metadata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 旅客值机信息历史表 Entity
 */
public class T_airport_passenger_h {

    private Long id;			    //主键
    private String plandatetime;	//航班日期 ，格式 YYYY-MM-DD
    private String aircorp;		    //航空公司，二字码
    private String fltno;		    //航班号
    private String name;		    //旅客姓名
    private String code;		    //身份证号
    private String cw;		        //舱位
    private Long chknum;		    //办理值机序号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date chktm;		        //办理值机时间
    private String airportid;	    //机场三字码
    private String remartk;	        //备注
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

    public T_airport_passenger_h() {
        super();
    }

    public T_airport_passenger_h(Long id) {
        this.id = id;
    }

    public T_airport_passenger_h(Long id, String plandatetime, String aircorp, String fltno, String name, String code, String cw, Long chknum, Date chktm, String airportid, String remartk, String by1, String by2, String by3, String by4, String by5, String by6, String by7, String by8, String by9, String by10) {
        this.id = id;
        this.plandatetime = plandatetime;
        this.aircorp = aircorp;
        this.fltno = fltno;
        this.name = name;
        this.code = code;
        this.cw = cw;
        this.chknum = chknum;
        this.chktm = chktm;
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
        return "T_airport_passenger_h{" +
                "id=" + id +
                ", plandatetime='" + plandatetime + '\'' +
                ", aircorp='" + aircorp + '\'' +
                ", fltno='" + fltno + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", cw='" + cw + '\'' +
                ", chknum=" + chknum +
                ", chktm=" + chktm +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCw() {
        return cw;
    }

    public void setCw(String cw) {
        this.cw = cw;
    }

    public Long getChknum() {
        return chknum;
    }

    public void setChknum(Long chknum) {
        this.chknum = chknum;
    }

    public Date getChktm() {
        return chktm;
    }

    public void setChktm(Date chktm) {
        this.chktm = chktm;
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
