package com.xtsoft.kernel.sys.entity;



public class UserGroupsPK extends AbstractEntity<UserGroupsPK> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String userId;
	public String groupId;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UserGroupsPK() {

	}
	public UserGroupsPK(String userId, String groupId) {
		this.userId = userId;
		this.groupId = groupId;
	}

	
}
