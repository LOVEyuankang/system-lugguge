package com.net.metadata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class InitFltBags implements Serializable{
	private static final long serialVersionUID = 6700813629656881143L;
	private String plandate;
	
	private String aircorp;
	
	private String fltno;
	
	private String code;//滑槽号
	
	private String status;//滑槽状态
	
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date stm;//计划开始时间
	
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date etm;//计划结束时间
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pstm;//预计开始时间
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date petm;//预计结束时间
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date rstm;//实际开始时间
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date retm;//实际结束时间
	
	private String trainlercode;//拖车编号
	
	private String name;//操作员
	
	private String ip;//设备ip
	
	private int total;//行李总件数
	
	private int vipbags;//贵宾行李件数
	
	private int zcbags;//装车行李件数
	
	private int wsbbags;//未识别行李件数
	
	private int sjajbags;//三级安检行李件数
	
	private int bigbags;//大件行李
	
	private String arrp;//目的地

	public String getPlandate() {
		return plandate;
	}

	public void setPlandate(String plandate) {
		this.plandate = plandate;
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

	public Date getStm() {
		return stm;
	}

	public void setStm(Date stm) {
		this.stm = stm;
	}

	public Date getEtm() {
		return etm;
	}

	public void setEtm(Date etm) {
		this.etm = etm;
	}

	public Date getPstm() {
		return pstm;
	}

	public void setPstm(Date pstm) {
		this.pstm = pstm;
	}

	public Date getPetm() {
		return petm;
	}

	public void setPetm(Date petm) {
		this.petm = petm;
	}

	public Date getRstm() {
		return rstm;
	}

	public void setRstm(Date rstm) {
		this.rstm = rstm;
	}

	public Date getRetm() {
		return retm;
	}

	public void setRetm(Date retm) {
		this.retm = retm;
	}

	public String getTrainlercode() {
		return trainlercode;
	}

	public void setTrainlercode(String trainlercode) {
		this.trainlercode = trainlercode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getVipbags() {
		return vipbags;
	}

	public void setVipbags(int vipbags) {
		this.vipbags = vipbags;
	}

	public int getZcbags() {
		return zcbags;
	}

	public void setZcbags(int zcbags) {
		this.zcbags = zcbags;
	}

	public int getWsbbags() {
		return wsbbags;
	}

	public void setWsbbags(int wsbbags) {
		this.wsbbags = wsbbags;
	}

	public int getSjajbags() {
		return sjajbags;
	}

	public void setSjajbags(int sjajbags) {
		this.sjajbags = sjajbags;
	}

	public int getBigbags() {
		return bigbags;
	}

	public void setBigbags(int bigbags) {
		this.bigbags = bigbags;
	}

	public String getArrp() {
		return arrp;
	}

	public void setArrp(String arrp) {
		this.arrp = arrp;
	}
	
	
}
