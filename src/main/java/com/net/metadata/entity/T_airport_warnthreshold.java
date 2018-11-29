package com.net.metadata.entity;

import java.math.BigDecimal;

/**
 * 预警阀值 Entity
 */
public class T_airport_warnthreshold {

    private Long id;			    //主键
    private BigDecimal threshold1;	//实现出港航班行李交接时间临近阀值（单位：分钟）
    private BigDecimal threshold2;	//行李运输质量考核预警阀值（单位：分钟）
    private BigDecimal threshold3;	//实际行李扫描数据与离港系统不一致预警阀值（单位：分钟）
    private BigDecimal threshold4;	//早到行李阀值（单位：分钟）
    private BigDecimal threshold5;	//备用阈值
    private BigDecimal threshold6;	//备用阈值
    private BigDecimal threshold7;	//备用阈值
    private BigDecimal threshold8;	//备用阈值
    private BigDecimal threshold9;	//备用阈值
    private String airportid;	    //机场三字码
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
    private String by12;
    private String by13;
    private String by14;
    private String by15;
    private String by16;
    private String by17;
    private String by18;
    private String by19;
    private String by20;

    public T_airport_warnthreshold() {
        super();
    }

    public T_airport_warnthreshold(Long id) {
        this.id = id;
    }

    public T_airport_warnthreshold(Long id, BigDecimal threshold1, BigDecimal threshold2, BigDecimal threshold3, BigDecimal threshold4, BigDecimal threshold5, BigDecimal threshold6, BigDecimal threshold7, BigDecimal threshold8, BigDecimal threshold9, String airportid, String by1, String by2, String by3, String by4, String by5, String by6, String by7, String by8, String by9, String by10, String by12, String by13, String by14, String by15, String by16, String by17, String by18, String by19, String by20) {
        this.id = id;
        this.threshold1 = threshold1;
        this.threshold2 = threshold2;
        this.threshold3 = threshold3;
        this.threshold4 = threshold4;
        this.threshold5 = threshold5;
        this.threshold6 = threshold6;
        this.threshold7 = threshold7;
        this.threshold8 = threshold8;
        this.threshold9 = threshold9;
        this.airportid = airportid;
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
        this.by12 = by12;
        this.by13 = by13;
        this.by14 = by14;
        this.by15 = by15;
        this.by16 = by16;
        this.by17 = by17;
        this.by18 = by18;
        this.by19 = by19;
        this.by20 = by20;
    }

    @Override
    public String toString() {
        return "T_airport_warnthreshold{" +
                "id=" + id +
                ", threshold1=" + threshold1 +
                ", threshold2=" + threshold2 +
                ", threshold3=" + threshold3 +
                ", threshold4=" + threshold4 +
                ", threshold5=" + threshold5 +
                ", threshold6=" + threshold6 +
                ", threshold7=" + threshold7 +
                ", threshold8=" + threshold8 +
                ", threshold9=" + threshold9 +
                ", airportid='" + airportid + '\'' +
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
                ", by12='" + by12 + '\'' +
                ", by13='" + by13 + '\'' +
                ", by14='" + by14 + '\'' +
                ", by15='" + by15 + '\'' +
                ", by16='" + by16 + '\'' +
                ", by17='" + by17 + '\'' +
                ", by18='" + by18 + '\'' +
                ", by19='" + by19 + '\'' +
                ", by20='" + by20 + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(BigDecimal threshold1) {
        this.threshold1 = threshold1;
    }

    public BigDecimal getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(BigDecimal threshold2) {
        this.threshold2 = threshold2;
    }

    public BigDecimal getThreshold3() {
        return threshold3;
    }

    public void setThreshold3(BigDecimal threshold3) {
        this.threshold3 = threshold3;
    }

    public BigDecimal getThreshold4() {
        return threshold4;
    }

    public void setThreshold4(BigDecimal threshold4) {
        this.threshold4 = threshold4;
    }

    public BigDecimal getThreshold5() {
        return threshold5;
    }

    public void setThreshold5(BigDecimal threshold5) {
        this.threshold5 = threshold5;
    }

    public BigDecimal getThreshold6() {
        return threshold6;
    }

    public void setThreshold6(BigDecimal threshold6) {
        this.threshold6 = threshold6;
    }

    public BigDecimal getThreshold7() {
        return threshold7;
    }

    public void setThreshold7(BigDecimal threshold7) {
        this.threshold7 = threshold7;
    }

    public BigDecimal getThreshold8() {
        return threshold8;
    }

    public void setThreshold8(BigDecimal threshold8) {
        this.threshold8 = threshold8;
    }

    public BigDecimal getThreshold9() {
        return threshold9;
    }

    public void setThreshold9(BigDecimal threshold9) {
        this.threshold9 = threshold9;
    }

    public String getAirportid() {
        return airportid;
    }

    public void setAirportid(String airportid) {
        this.airportid = airportid;
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

    public String getBy12() {
        return by12;
    }

    public void setBy12(String by12) {
        this.by12 = by12;
    }

    public String getBy13() {
        return by13;
    }

    public void setBy13(String by13) {
        this.by13 = by13;
    }

    public String getBy14() {
        return by14;
    }

    public void setBy14(String by14) {
        this.by14 = by14;
    }

    public String getBy15() {
        return by15;
    }

    public void setBy15(String by15) {
        this.by15 = by15;
    }

    public String getBy16() {
        return by16;
    }

    public void setBy16(String by16) {
        this.by16 = by16;
    }

    public String getBy17() {
        return by17;
    }

    public void setBy17(String by17) {
        this.by17 = by17;
    }

    public String getBy18() {
        return by18;
    }

    public void setBy18(String by18) {
        this.by18 = by18;
    }

    public String getBy19() {
        return by19;
    }

    public void setBy19(String by19) {
        this.by19 = by19;
    }

    public String getBy20() {
        return by20;
    }

    public void setBy20(String by20) {
        this.by20 = by20;
    }
}
