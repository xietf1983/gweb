package com.xtsoft.kernel.sys.entity;

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class UserEntity extends DataEntity<UserEntity> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String emailaddress;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

	private String userId;

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getDefaultUser() {
		return defaultUser;
	}

	public void setDefaultUser(int defaultUser) {
		this.defaultUser = defaultUser;
	}

	public Date getPasswordModifiedDate() {
		return passwordModifiedDate;
	}

	public void setPasswordModifiedDate(Date passwordModifiedDate) {
		this.passwordModifiedDate = passwordModifiedDate;
	}

	public String getReminderQueryQuestion() {
		return reminderQueryQuestion;
	}

	public void setReminderQueryQuestion(String reminderQueryQuestion) {
		this.reminderQueryQuestion = reminderQueryQuestion;
	}

	public String getReminderQueryAnswer() {
		return reminderQueryAnswer;
	}

	public void setReminderQueryAnswer(String reminderQueryAnswer) {
		this.reminderQueryAnswer = reminderQueryAnswer;
	}

	public long getGraceLoginCount() {
		return graceLoginCount;
	}

	public void setGraceLoginCount(long graceLoginCount) {
		this.graceLoginCount = graceLoginCount;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public int getMale() {
		return male;
	}

	public void setMale(int male) {
		this.male = male;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	private Date modifiedDate;
	private int defaultUser;
	private Date passwordModifiedDate;
	private String reminderQueryQuestion;
	private String reminderQueryAnswer;
	private long graceLoginCount;
	private String screenName;//
	private String greeting;
	private String jobTitle;
	private Date loginDate;
	private String loginIP;
	private Date lastLoginDate;
	private String lastLoginIP;
	private int status;
	private String employeeNumber;
	private int male;
	private Date birthday;
	private String tel;
	private String organizationId;
	private String organizationFullName;

	public String getOrganizationFullName() {
		return organizationFullName;
	}

	public void setOrganizationFullName(String organizationFullName) {
		this.organizationFullName = organizationFullName;
	}

	private String description;
	private String userType;

	private String userTypeName;
	private String statusName;

	public final String getStatusName() {
		return statusName;
	}

	public final void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public final String getUserTypeName() {
		return userTypeName;
	}

	public final void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate;// 入职时间

	public final Date getEntryDate() {
		return entryDate;
	}

	public final void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String toString() {
		return (new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) {
			protected boolean accept(Field f) {
				return super.accept(f) && !f.getName().equals("password");
			}
		}).toString();
	}

}
