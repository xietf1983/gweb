package com.xtsoft.kernel.sys.entity;







/*******************************************************************************
 * 
 * @author x61
 * 
 */
public class CounterEntity extends DataEntity<CounterEntity> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private long currentId;

	public CounterEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCurrentId() {
		return currentId;
	}

	public void setCurrentId(long currentId) {
		this.currentId = currentId;
	}

	

}