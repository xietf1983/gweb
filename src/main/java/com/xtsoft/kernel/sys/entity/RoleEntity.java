package com.xtsoft.kernel.sys.entity;

public class RoleEntity extends DataEntity<RoleEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		if (this.name == null) {
			return StringPool.BLANK;
		} else {
			return this.name;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		if (this.title == null) {
			return StringPool.BLANK;
		} else {
			return this.title;
		}
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		if (this.description == null) {
			return StringPool.BLANK;
		} else {
			return this.description;
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String name;
	private String title;
	private String description;

}
