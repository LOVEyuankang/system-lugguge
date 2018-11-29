package com.net.metadata.entity;

import java.util.List;
import java.util.Map;

public class PrintSearch {

	private String flightno;
	private int xl_total;
	private int cl_total;
	private String time;
	private Map<String,List<Brs_luggage_view>> map;
	public String getFlightno() {
		return flightno;
	}
	public int getXl_total() {
		return xl_total;
	}
	public int getCl_total() {
		return cl_total;
	}
	public String getTime() {
		return time;
	}
	public Map<String, List<Brs_luggage_view>> getMap() {
		return map;
	}
	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}
	public void setXl_total(int xl_total) {
		this.xl_total = xl_total;
	}
	public void setCl_total(int cl_total) {
		this.cl_total = cl_total;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setMap(Map<String, List<Brs_luggage_view>> map) {
		this.map = map;
	}
	
	
}
