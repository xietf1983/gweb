package com.xtsoft.kernel.sys.entity;

import java.util.Date;
import java.util.List;


public class MenuEntity extends TreeEntity<MenuEntity> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String menuIcon;
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Short getIsshow() {
		return isshow;
	}
	public void setIsshow(Short isshow) {
		this.isshow = isshow;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	private String type;

	private String url;

	private String permission;

	private Short isshow;

	private Integer sort;

	private String remarks;
	
	private String createBy;
	
	private Date createDate;
	
	private String updateBy;
	
	private Date updateDate;
	
	private String delFlag = "0";
	
	private List<MenuEntity> menuItemList;
	public List<MenuEntity> getMenuItemList() {
		return menuItemList;
	}
	public void setMenuItemList(List<MenuEntity> menuItemList) {
		this.menuItemList = menuItemList;
	}
}
