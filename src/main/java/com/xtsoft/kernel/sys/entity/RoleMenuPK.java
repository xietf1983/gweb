package com.xtsoft.kernel.sys.entity;

public class RoleMenuPK extends AbstractEntity<RoleMenuPK> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String roleId;
	public String menuId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public RoleMenuPK() {

	}

	public RoleMenuPK(String menuId, String roleId) {
		this.menuId = menuId;
		this.roleId = roleId;
	}

}
