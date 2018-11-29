package com.net.metadata.entity;

import java.io.Serializable;


public class SysLog implements Serializable {

    private static final long serialVersionUID = -8690056878905494181L;

    private Long id;

    private String loginName;//登录名

    private String roleName;

    private String optContent;//操作内容

    private String clientIp;//本地Ip

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
    
    private String command;//操作命令
    
    private String operationobj;//操作对象

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getOptContent() {
        return optContent;
    }

    public void setOptContent(String optContent) {
        this.optContent = optContent == null ? null : optContent.trim();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", roleName='" + roleName + '\'' +
                ", optContent='" + optContent + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", createTime=" + createTime + '\'' +
                ", command=" + command + '\'' +
                ", operationobj=" + operationobj +
                '}';
    }

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		 this.command = command == null ? null : command.trim();
	}

	public String getOperationobj() {
		return operationobj;
	}

	public void setOperationobj(String operationobj) {
		this.operationobj = operationobj == null ? null : operationobj.trim();
	}
    
}