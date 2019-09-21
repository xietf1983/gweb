package com.xtsoft.kernel.base;

import java.io.Serializable;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FINDWITHDYNAMICQUERY_COUNT= "findWithDynamicQuery_Count_";

	public static final String FINDWITHDYNAMICQUERY_LIST = "findWithDynamicQuery_List_";

	public static final String FINDWITHDYNAMICQUERY_PAGELIST = "findWithDynamicQuery_PageList_";

	public static final String INSERTENTITY = "_insert";
	
	public static final String UPDATEENTITY = "_update";
	
	public static final String DELETEENTITY = "_delete";
	
	public static final String FINDPRIMARYKEY= "_finderOne";
	
}
