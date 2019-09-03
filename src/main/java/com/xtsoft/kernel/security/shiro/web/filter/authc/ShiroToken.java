package com.xtsoft.kernel.security.shiro.web.filter.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroToken extends UsernamePasswordToken implements java.io.Serializable {
	private static final long serialVersionUID = -6451794657814516274L;

	public ShiroToken(String username, String pswd,String userId,String realname) {
		super(username, pswd);
		this.pswd = pswd;
		this.userId=userId;
		this.realname=realname;
	}

	/** 登录密码[字符串类型] 因为父类是char[] ] **/
	private String pswd;
	
	private String userId;
	
	private String realname;

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
}
