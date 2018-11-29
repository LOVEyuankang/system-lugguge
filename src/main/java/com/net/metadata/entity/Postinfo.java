package com.net.metadata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @description：岗位
 * @author：ccj
 * @date：2016/05/21 10:35
 */
public class Postinfo implements Serializable {

    private static final long serialVersionUID = 6700813629656881143L;

    private Long id;

    private String postname;

    private String deptid;

    private String postremark;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date createdate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
    
    public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getPostremark() {
		return postremark;
	}

	public void setPostremark(String postremark) {
		this.postremark = postremark;
	}

	public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    
}