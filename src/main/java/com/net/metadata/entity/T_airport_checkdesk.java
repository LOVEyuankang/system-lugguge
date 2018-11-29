package com.net.metadata.entity;

/**
 * 值机柜台信息 Entity
 */
public class T_airport_checkdesk {

    private Long id;            //主键
    private String landcode;    //值机岛编号
    private String code;        //值机柜台编号
    private String status;      //值机柜台状态    正常、故障、停用
    private String attr;        //值机柜台属性    国际、国内
    private String chkpid;      //对应离港柜台PID号，BSM报中有PID号且每个柜台PID号不一样
    private String chktype;     //柜台类型  普通/大件/VIP
    private String airportid;   //机场三字码
    private String remark;      //备注
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

    public T_airport_checkdesk() {
        super();
    }

    public T_airport_checkdesk(Long id) {
        this.id = id;
    }

    public T_airport_checkdesk(Long id, String landcode, String code, String status, String attr, String chkpid, String chktype, String airportid, String remark, String by1, String by2, String by3, String by4, String by5, String by6, String by7, String by8, String by9, String by10, String by12, String by13, String by14, String by15, String by16, String by17, String by18, String by19, String by20) {
        this.id = id;
        this.landcode = landcode;
        this.code = code;
        this.status = status;
        this.attr = attr;
        this.chkpid = chkpid;
        this.chktype = chktype;
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
        return "T_airport_checkdesk{" +
                "id=" + id +
                ", landcode='" + landcode + '\'' +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", attr='" + attr + '\'' +
                ", chkpid='" + chkpid + '\'' +
                ", chktype='" + chktype + '\'' +
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

    public String getLandcode() {
        return landcode;
    }

    public void setLandcode(String landcode) {
        this.landcode = landcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getChkpid() {
        return chkpid;
    }

    public void setChkpid(String chkpid) {
        this.chkpid = chkpid;
    }

    public String getChktype() {
        return chktype;
    }

    public void setChktype(String chktype) {
        this.chktype = chktype;
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
