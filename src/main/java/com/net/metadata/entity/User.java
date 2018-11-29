package com.net.metadata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @description：用户
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class User implements Serializable {

    private static final long serialVersionUID = 6700813629656881143L;

    private Long id;

    private String loginname;

    private String name;

    private String password;

    private Integer sex;

    private Integer age;

    private Integer usertype;

    private Integer status;

    private Integer organizationId;

    private String postId;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date createdate;

    private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User(Long id, String loginname, String name, String password,
			Integer sex, Integer age, Integer usertype, Integer status,
			Integer organizationId, String postId, Date createdate, String phone) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.usertype = usertype;
		this.status = status;
		this.organizationId = organizationId;
		this.postId = postId;
		this.createdate = createdate;
		this.phone = phone;
	}

	public User(String loginname, String name, String password, Integer sex,
			Integer age, Integer usertype, Integer status,
			Integer organizationId, String postId, Date createdate, String phone) {
		super();
		this.loginname = loginname;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.usertype = usertype;
		this.status = status;
		this.organizationId = organizationId;
		this.postId = postId;
		this.createdate = createdate;
		this.phone = phone;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", loginname='" + loginname + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", sex=" + sex +
				", age=" + age +
				", usertype=" + usertype +
				", status=" + status +
				", organizationId=" + organizationId +
				", postId='" + postId + '\'' +
				", createdate=" + createdate +
				", phone='" + phone + '\'' +
				'}';
	}
}