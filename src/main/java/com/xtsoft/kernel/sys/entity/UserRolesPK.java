package com.xtsoft.kernel.sys.entity;

public class UserRolesPK extends AbstractEntity<UserRolesPK> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String userId;
	public String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserRolesPK() {

	}

	public UserRolesPK(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

}
