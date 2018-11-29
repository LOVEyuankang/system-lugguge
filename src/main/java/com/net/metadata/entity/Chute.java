package com.net.metadata.entity;

import java.io.Serializable;

public class Chute implements Serializable {
	private static final long serialVersionUID = 6700813629656881143L;

	private Long id;

	private String ccode;// 滑槽号

	private String ctype;// 滑槽类型

	private String cstatus;// 滑槽状态

	private String dcode;// 设备编号

	private String dtype;// 设备类型

	private String dstatus;// 设备状态

	private String ip;// 设备ip

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

	public String getDcode() {
		return dcode;
	}

	public void setDcode(String dcode) {
		this.dcode = dcode;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getDstatus() {
		return dstatus;
	}

	public void setDstatus(String dstatus) {
		this.dstatus = dstatus;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
