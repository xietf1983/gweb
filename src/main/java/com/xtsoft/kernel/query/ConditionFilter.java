package com.xtsoft.kernel.query;

import java.io.Serializable;

public class ConditionFilter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String property;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Object value;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ConditionFilter(String property, Object value, String description) {
		this.property = property;
		this.value = value;
		this.description = description;
	}
}
