package com.net.metadata.entity;

/**
 * 设备管理 Entity
 */
public class T_airport_device {

	private Long id;					//主键
	private String code;				//设备编号
	private String type;				//设备类型   航显工控机、识别工控机、天线、数据采集器、无线AP、手持终端、显示屏
	private String status;				//设备状态    正常、故障、停用
	private String position;			//设备位置描述
	private String ip;					//设备IP，包括航显工控机、识别工控机、天线、数据采集器
	private Integer flightnum;			//显示航班数，最多支持2个航班数  显示屏专用
	private String ledtype;				//显示屏类型  特殊行李、正常行李  显示屏专用
	private String flightsqlcontent;	//查询航班sql语句中文描述 识别工控机专用
	private String flightsql;			//查询航班sql语句  识别工控机专用
	private String deviceip;			//对应采集器IP  航显工控机专用
	private String antecode;			//对应采集器天线编号  航显工控机专用
	private String ssid;				//无线AP专用
	private String wifipwd;				//无线AP专用
	private String is_wifi;				//是否支持WIFI, 是/否
	private String airportid;			//是否支持移动网络, 是/否
	private String is_mobile;			//机场三字码
	private String remark;				//备注
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

	public T_airport_device() {
		super();
	}

	public T_airport_device(Long id) {
		this.id = id;
	}

	public T_airport_device(Long id, String code, String type, String status, String position, String ip, Integer flightnum, String ledtype, String flightsqlcontent, String flightsql, String deviceip, String antecode, String ssid, String wifipwd, String is_wifi, String airportid, String is_mobile, String remark, String by1, String by2, String by3, String by4, String by5, String by6, String by7, String by8, String by9, String by10) {
		this.id = id;
		this.code = code;
		this.type = type;
		this.status = status;
		this.position = position;
		this.ip = ip;
		this.flightnum = flightnum;
		this.ledtype = ledtype;
		this.flightsqlcontent = flightsqlcontent;
		this.flightsql = flightsql;
		this.deviceip = deviceip;
		this.antecode = antecode;
		this.ssid = ssid;
		this.wifipwd = wifipwd;
		this.is_wifi = is_wifi;
		this.airportid = airportid;
		this.is_mobile = is_mobile;
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
		return "T_airport_device{" +
				"id=" + id +
				", code='" + code + '\'' +
				", type='" + type + '\'' +
				", status='" + status + '\'' +
				", position='" + position + '\'' +
				", ip='" + ip + '\'' +
				", flightnum=" + flightnum +
				", ledtype='" + ledtype + '\'' +
				", flightsqlcontent='" + flightsqlcontent + '\'' +
				", flightsql='" + flightsql + '\'' +
				", deviceip='" + deviceip + '\'' +
				", antecode='" + antecode + '\'' +
				", ssid='" + ssid + '\'' +
				", wifipwd='" + wifipwd + '\'' +
				", is_wifi='" + is_wifi + '\'' +
				", airportid='" + airportid + '\'' +
				", is_mobile='" + is_mobile + '\'' +
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getFlightnum() {
		return flightnum;
	}

	public void setFlightnum(Integer flightnum) {
		this.flightnum = flightnum;
	}

	public String getLedtype() {
		return ledtype;
	}

	public void setLedtype(String ledtype) {
		this.ledtype = ledtype;
	}

	public String getFlightsqlcontent() {
		return flightsqlcontent;
	}

	public void setFlightsqlcontent(String flightsqlcontent) {
		this.flightsqlcontent = flightsqlcontent;
	}

	public String getFlightsql() {
		return flightsql;
	}

	public void setFlightsql(String flightsql) {
		this.flightsql = flightsql;
	}

	public String getDeviceip() {
		return deviceip;
	}

	public void setDeviceip(String deviceip) {
		this.deviceip = deviceip;
	}

	public String getAntecode() {
		return antecode;
	}

	public void setAntecode(String antecode) {
		this.antecode = antecode;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getWifipwd() {
		return wifipwd;
	}

	public void setWifipwd(String wifipwd) {
		this.wifipwd = wifipwd;
	}

	public String getIs_wifi() {
		return is_wifi;
	}

	public void setIs_wifi(String is_wifi) {
		this.is_wifi = is_wifi;
	}

	public String getAirportid() {
		return airportid;
	}

	public void setAirportid(String airportid) {
		this.airportid = airportid;
	}

	public String getIs_mobile() {
		return is_mobile;
	}

	public void setIs_mobile(String is_mobile) {
		this.is_mobile = is_mobile;
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
