package com.xtsoft.kernel.sys.entity;

import java.util.Date;

import com.xtsoft.kernel.common.entity.AbstractEntity;

public class LogEventEntity extends AbstractEntity<LogEventEntity> {

	private static final long serialVersionUID = 1L;

	private String id;
	private String userId;
	private String userAccount;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	private String methods;
	private Date operTime;
	private String userIP;
	private String className;
	private String arguments;

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	private int sucess;
	private String excetption;
	private String description;
	private long exeTime;
	private String returnObject;
	public String getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(String returnObject) {
		this.returnObject = returnObject;
	}

	public long getExeTime() {
		return exeTime;
	}

	public void setExeTime(long exeTime) {
		this.exeTime = exeTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getSucess() {
		return sucess;
	}

	public void setSucess(int sucess) {
		this.sucess = sucess;
	}

	public String getExcetption() {
		return excetption;
	}

	public void setExcetption(String excetption) {
		this.excetption = excetption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
