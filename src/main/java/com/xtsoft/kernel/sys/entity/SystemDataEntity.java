package com.xtsoft.kernel.sys.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SystemDataEntity extends DataEntity<SystemDataEntity> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String dataCode;
	private long sortId;
	private String name;
	private String value;
	private String iconCls;
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	private int status;
	private String description;

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getDataCode() {
		return dataCode;
	}

	public final void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public final long getSortId() {
		return sortId;
	}

	public final void setSortId(long sortId) {
		this.sortId = sortId;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getValue() {
		return value;
	}

	public final void setValue(String value) {
		this.value = value;
	}

	public final int getStatus() {
		return status;
	}

	public final void setStatus(int status) {
		this.status = status;
	}

	public String toString() {
		return (new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) {

		}).toString();
	}

}
