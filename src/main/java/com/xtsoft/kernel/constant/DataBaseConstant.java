package com.xtsoft.kernel.constant;

public interface DataBaseConstant {
	/**
	 * 删除标记（0：正常；1：删除 ）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DELETE_SUFFIX = "_delete";
	public static final String INSERT_SUFFIX = "_insert";
	public static final String UPDATE_SUFFIX = "_update";
	public static final String FINDERBY_ONE = "_finderOne";

	public static final String ROOTID = "0";

	/****** UserEntity ***********/

	public static final String USERID = "用户ID";
	public static final String PARENTID = "父节点ID";
	public static final String ROLEID = "角色ID";
	public static final String MENUTYPE = "菜单类型";

	public static final String MENUTYPE_0 = "0";// 菜单首页

	public static final String MENUTYPE_1 = "1";// 资源菜单

	public static final String MENUTYPE_2 = "2";// 资源按钮

	/*** SYSDATA 字典表 */

	public static final String CODE_USERTYPE = "USERTYPE";// 岗位类型

	public static final String STATEMENT_FINDORGANIZATIONIDLISTBYUSERID = "findOrganizationIdListByUserId";

}
