package com.xtsoft.kernel.sys.entity;

public class UserOrgsPK extends AbstractEntity<UserOrgsPK> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String organizationId;

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public UserOrgsPK() {

	}

	public UserOrgsPK(String userId, String roleId) {
		this.userId = userId;
		this.organizationId = roleId;
	}

}
