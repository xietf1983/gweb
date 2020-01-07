package com.xtsoft.police.entity;

import java.util.List;

import com.xtsoft.kernel.sys.entity.DataEntity;

public class DirectionEntity extends DataEntity<DirectionEntity> {
	private String code;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<LaneEntity> getList() {
		return list;
	}
	public void setList(List<LaneEntity> list) {
		this.list = list;
	}
	private List<LaneEntity> list;

}
