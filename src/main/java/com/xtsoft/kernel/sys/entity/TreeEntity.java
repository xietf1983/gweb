package com.xtsoft.kernel.sys.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class TreeEntity<T> extends AbstractEntity<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id; 

	private String name; 
	private String parentId;
	private String parentIds; 
	
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	private Boolean expanded = Boolean.FALSE;

	private Boolean loaded = Boolean.TRUE;

	private boolean hasChildren;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getLoaded() {
		return loaded;
	}

	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public boolean isRoot() {
		if (getParentId() == null || getParentId().equals("0") || getParentId().equals("00")
				|| getParentId().equals("")) {
			return true;
		}
		return false;
	}

	public Boolean isLeaf() {
		if (isHasChildren()) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	public String getSeparator() {
		return "/";
	}

	public Long getLevel() {
		if (parentIds == null) {
			return (long) 0;
		}
		String[] parentIdArr = parentIds.split("/");
		List<String> idsList = new ArrayList<String>();
		for (String id : parentIdArr) {
			if (!StringUtils.isEmpty(id)) {
				idsList.add(id);
			}
		}
		return (long) (idsList.size());
	}

}
