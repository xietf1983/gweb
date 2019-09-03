package com.xtsoft.kernel.sys.entity;

import java.util.Date;
import java.util.List;

import com.xtsoft.kernel.common.entity.TreeEntity;

public class OrganizationEntity extends TreeEntity<OrganizationEntity> {
	private String fullPath;

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public long getSortId() {
		return sortId;
	}

	public void setSortId(long sortId) {
		this.sortId = sortId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<OrganizationEntity> getChildren() {
		return children;
	}

	public void setChildren(List<OrganizationEntity> children) {
		this.children = children;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	private long active;

	public final long getActive() {
		return active;
	}

	public final void setActive(long active) {
		this.active = active;
	}

	private long sortId;
	private boolean checked;
	private List<OrganizationEntity> children;
	private String fullName;
	private String text;
	private Long countAllValue;
	private String userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;

	public Long getCountAllValue() {
		return countAllValue;
	}

	public void setCountAllValue(Long countAllValue) {
		this.countAllValue = countAllValue;
	}

	private Long countValue;

	public Long getCountValue() {
		return countValue;
	}

	public void setCountValue(Long countValue) {
		this.countValue = countValue;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public final String getUserId() {
		return userId;
	}

	public final void setUserId(String userId) {
		this.userId = userId;
	}

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public final Date getCreateDate() {
		return createDate;
	}

	public final void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public final Date getModifiedDate() {
		return modifiedDate;
	}

	public final void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
