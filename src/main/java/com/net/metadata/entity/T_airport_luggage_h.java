package com.net.metadata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 旅客行李信息历史表 Entity
 */
public class T_airport_luggage_h {

    private Long id;                    //主键
    private String plandatetime;        //航班日期，格式 YYYY-MM-DD
    private String aircorp;             //航空公司，二字码
    private String fltno;               //航班号
    private String percode;             //身份证号
    private String bgcode;              //行李标签号
    private String type;                //行李类型  值机行李/大件行李/贵宾行李/临时增加
    private String attr;                //行李属性  国际/国内
    private BigDecimal bgweight;        //重量 (单位：kg)
    private String bgphoto;             //行李照片路径
    private String bgdisttype;          //绑定图像时写入    1 RFID识别  0 条码识别 2都未识别
    private String bgstatus;            //行李状态 已值机办理、待分拣、已装车、已出库、已删除  无状态的或已删除的或无法识别的不在装车报表里面体现。
    private String trailercode;         //当前对应拖车编号
    private String personcode;          //当前对应操作员编号
    private String chutecode;           //当前对应滑槽号   潮汕项目暂不用
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trailerTime;           //装车时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date outTime;               //出库时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inCabinTime;           //进舱时间  潮汕项目暂不用
    private String inCabinEmployeeId;   //进舱操作员编号  潮汕项目暂不用
    private String cationArea;          //进舱分区  潮汕项目暂不用
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date outCabinTime;          //出舱时间   潮汕项目暂不用
    private String outCabinEmployeeId;  //出舱操作员编号 潮汕项目暂不用
    private String bglevel;             //行李级别
    private String chkdesk;             //值机柜台号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inChkTime;             //值机时间
    private String bsmcontente;         //bsm原始报文
    private String airportid;           //机场三字码
    private String remartk;             //备注
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

    public T_airport_luggage_h() {
        super();
    }

    public T_airport_luggage_h(Long id) {
        this.id = id;
    }

    public T_airport_luggage_h(Long id, String plandatetime, String aircorp, String fltno, String percode, String bgcode, String type, String attr, BigDecimal bgweight, String bgphoto, String bgdisttype, String bgstatus, String trailercode, String personcode, String chutecode, Date trailerTime, Date outTime, Date inCabinTime, String inCabinEmployeeId, String cationArea, Date outCabinTime, String outCabinEmployeeId, String bglevel, String chkdesk, Date inChkTime, String bsmcontente, String airportid, String remartk, String by1, String by2, String by3, String by4, String by5, String by6, String by7, String by8, String by9, String by10) {
        this.id = id;
        this.plandatetime = plandatetime;
        this.aircorp = aircorp;
        this.fltno = fltno;
        this.percode = percode;
        this.bgcode = bgcode;
        this.type = type;
        this.attr = attr;
        this.bgweight = bgweight;
        this.bgphoto = bgphoto;
        this.bgdisttype = bgdisttype;
        this.bgstatus = bgstatus;
        this.trailercode = trailercode;
        this.personcode = personcode;
        this.chutecode = chutecode;
        this.trailerTime = trailerTime;
        this.outTime = outTime;
        this.inCabinTime = inCabinTime;
        this.inCabinEmployeeId = inCabinEmployeeId;
        this.cationArea = cationArea;
        this.outCabinTime = outCabinTime;
        this.outCabinEmployeeId = outCabinEmployeeId;
        this.bglevel = bglevel;
        this.chkdesk = chkdesk;
        this.inChkTime = inChkTime;
        this.bsmcontente = bsmcontente;
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
        return "T_airport_luggage{" +
                "id=" + id +
                ", plandatetime='" + plandatetime + '\'' +
                ", aircorp='" + aircorp + '\'' +
                ", fltno='" + fltno + '\'' +
                ", percode='" + percode + '\'' +
                ", bgcode='" + bgcode + '\'' +
                ", type='" + type + '\'' +
                ", attr='" + attr + '\'' +
                ", bgweight=" + bgweight +
                ", bgphoto='" + bgphoto + '\'' +
                ", bgdisttype='" + bgdisttype + '\'' +
                ", bgstatus='" + bgstatus + '\'' +
                ", trailercode='" + trailercode + '\'' +
                ", personcode='" + personcode + '\'' +
                ", chutecode='" + chutecode + '\'' +
                ", trailerTime=" + trailerTime +
                ", outTime=" + outTime +
                ", inCabinTime=" + inCabinTime +
                ", inCabinEmployeeId='" + inCabinEmployeeId + '\'' +
                ", cationArea='" + cationArea + '\'' +
                ", outCabinTime=" + outCabinTime +
                ", outCabinEmployeeId='" + outCabinEmployeeId + '\'' +
                ", bglevel='" + bglevel + '\'' +
                ", chkdesk='" + chkdesk + '\'' +
                ", inChkTime=" + inChkTime +
                ", bsmcontente='" + bsmcontente + '\'' +
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

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode;
    }

    public String getBgcode() {
        return bgcode;
    }

    public void setBgcode(String bgcode) {
        this.bgcode = bgcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
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
        this.bgphoto = bgphoto;
    }

    public String getBgdisttype() {
        return bgdisttype;
    }

    public void setBgdisttype(String bgdisttype) {
        this.bgdisttype = bgdisttype;
    }

    public String getBgstatus() {
        return bgstatus;
    }

    public void setBgstatus(String bgstatus) {
        this.bgstatus = bgstatus;
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

    public String getChutecode() {
        return chutecode;
    }

    public void setChutecode(String chutecode) {
        this.chutecode = chutecode;
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
        this.inCabinEmployeeId = inCabinEmployeeId;
    }

    public String getCationArea() {
        return cationArea;
    }

    public void setCationArea(String cationArea) {
        this.cationArea = cationArea;
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
        this.outCabinEmployeeId = outCabinEmployeeId;
    }

    public String getBglevel() {
        return bglevel;
    }

    public void setBglevel(String bglevel) {
        this.bglevel = bglevel;
    }

    public String getChkdesk() {
        return chkdesk;
    }

    public void setChkdesk(String chkdesk) {
        this.chkdesk = chkdesk;
    }

    public Date getInChkTime() {
        return inChkTime;
    }

    public void setInChkTime(Date inChkTime) {
        this.inChkTime = inChkTime;
    }

    public String getBsmcontente() {
        return bsmcontente;
    }

    public void setBsmcontente(String bsmcontente) {
        this.bsmcontente = bsmcontente;
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
