package com.net.metadata.entity;

/**
 * 屏显航班对应表 Entity
 */
public class T_airport_bids_flight {

    private Long id;			    //主键
    private String bidscode;	    //屏显编号  从设备表获取
    private String plandatetime;	//对应航班日期，格式 YYYY-MM-DD 从出港航班动态表获取
    private String aircorp;	        //对应航空公司 二字码 从出港航班动态表获取
    private String fltno;	        //对应航班号 从出港航班动态表获取
    private String airportid;	    //机场三字码
    private String remark;		    //备注
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

    public T_airport_bids_flight() {
        super();
    }

    public T_airport_bids_flight(Long id) {
        this.id = id;
    }

    public T_airport_bids_flight(Long id, String bidscode, String plandatetime, String aircorp, String fltno, String airportid, String remark, String by1, String by2, String by3, String by4, String by5, String by6, String by7, String by8, String by9, String by10) {
        this.id = id;
        this.bidscode = bidscode;
        this.plandatetime = plandatetime;
        this.aircorp = aircorp;
        this.fltno = fltno;
        this.airportid = airportid;
        this.remark = remark;
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
        return "T_airport_bids_flight{" +
                "id=" + id +
                ", bidscode='" + bidscode + '\'' +
                ", plandatetime='" + plandatetime + '\'' +
                ", aircorp='" + aircorp + '\'' +
                ", fltno='" + fltno + '\'' +
                ", airportid='" + airportid + '\'' +
                ", remark='" + remark + '\'' +
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

    public String getBidscode() {
        return bidscode;
    }

    public void setBidscode(String bidscode) {
        this.bidscode = bidscode;
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

    public String getAirportid() {
        return airportid;
    }

    public void setAirportid(String airportid) {
        this.airportid = airportid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
