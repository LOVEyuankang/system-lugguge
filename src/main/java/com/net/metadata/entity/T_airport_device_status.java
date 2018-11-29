package com.net.metadata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 设备监听表 Entity
 */
public class T_airport_device_status {

    private Long id;			    //主键
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tm;	            //记录时间
    private String code;	        //设备编号
    private String type;	        //设备类型   航显工控机、识别工控机、天线、数据采集器、无线AP、手持终端、显示屏
    private String status;	        //设备状态,  开、关、故障
    private BigDecimal cpuload;	    //CPU 占用率
    private BigDecimal menload;	    //内存占用率
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

    public T_airport_device_status() {
        super();
    }

    public T_airport_device_status(Long id) {
        this.id = id;
    }

    public T_airport_device_status(Long id, Date tm, String code, String type, String status, BigDecimal cpuload, BigDecimal menload, String remartk, String by1, String by2, String by3, String by4, String by5, String by6, String by7, String by8, String by9, String by10) {
        this.id = id;
        this.tm = tm;
        this.code = code;
        this.type = type;
        this.status = status;
        this.cpuload = cpuload;
        this.menload = menload;
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
        return "T_airport_device_status{" +
                "id=" + id +
                ", tm=" + tm +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", cpuload=" + cpuload +
                ", menload=" + menload +
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getCpuload() {
        return cpuload;
    }

    public void setCpuload(BigDecimal cpuload) {
        this.cpuload = cpuload;
    }

    public BigDecimal getMenload() {
        return menload;
    }

    public void setMenload(BigDecimal menload) {
        this.menload = menload;
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
