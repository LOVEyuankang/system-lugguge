package com.net.metadata.entity;

/**
 * 拖车表 Entity
 */
public class T_airport_trailer {

	private Long id;			//主键
	private String code;		//拖车编号即RFID标签号
	private String type;		//拖车类型    车头/拖车
	private String status;		//拖车状态    正常、故障、停用
	private String airportid;	//机场三字码
	private String remark;		//备注
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

	public T_airport_trailer() {
		super();
	}

	public T_airport_trailer(Long id) {
		this.id = id;
	}

	public T_airport_trailer(Long id, String code, String type, String status, String airportid, String remark, String by1, String by2, String by3, String by4, String by5, String by6, String by7, String by8, String by9, String by10) {
		this.id = id;
		this.code = code;
		this.type = type;
		this.status = status;
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
		return "T_airport_trailer{" +
				"id=" + id +
				", code='" + code + '\'' +
				", type='" + type + '\'' +
				", status='" + status + '\'' +
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
