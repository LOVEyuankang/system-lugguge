package com.net.metadata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 行李动态处理日志 Entity
 */
public class Bag_operlog {

    private Long id;			    //主键
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tm;	            //操作时间
    private String bgcode;	        //行李标签号
    private String plandatetime;	//航班日期，格式 YYYY-MM-DD
    private String aircorp;	        //航空公司，二字码
    private String fltno;	        //航班号
    private String Status;	        //行李状态 (已值机、待分拣、已装车、已出库)
    private String Opercontent;	    //操作描述
    private String Operater;	    //操作员
    private String Airportid;	    //机场三字码
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

    public Bag_operlog() {
        super();
    }

    public Bag_operlog(Long id) {
        this.id = id;
    }

    public Bag_operlog(Long id, Date tm, String bgcode, String plandatetime, String aircorp, String fltno, String status, String opercontent, String operater, String airportid, String remartk, String by1, String by2, String by3, String by4, String by5, String by6, String by7, String by8, String by9, String by10) {
        this.id = id;
        this.tm = tm;
        this.bgcode = bgcode;
        this.plandatetime = plandatetime;
        this.aircorp = aircorp;
        this.fltno = fltno;
        Status = status;
        Opercontent = opercontent;
        Operater = operater;
        Airportid = airportid;
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
        return "Bag_operlog{" +
                "id=" + id +
                ", tm=" + tm +
                ", bgcode='" + bgcode + '\'' +
                ", plandatetime='" + plandatetime + '\'' +
                ", aircorp='" + aircorp + '\'' +
                ", fltno='" + fltno + '\'' +
                ", Status='" + Status + '\'' +
                ", Opercontent='" + Opercontent + '\'' +
                ", Operater='" + Operater + '\'' +
                ", Airportid='" + Airportid + '\'' +
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

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public String getBgcode() {
        return bgcode;
    }

    public void setBgcode(String bgcode) {
        this.bgcode = bgcode;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getOpercontent() {
        return Opercontent;
    }

    public void setOpercontent(String opercontent) {
        Opercontent = opercontent;
    }

    public String getOperater() {
        return Operater;
    }

    public void setOperater(String operater) {
        Operater = operater;
    }

    public String getAirportid() {
        return Airportid;
    }

    public void setAirportid(String airportid) {
        Airportid = airportid;
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
