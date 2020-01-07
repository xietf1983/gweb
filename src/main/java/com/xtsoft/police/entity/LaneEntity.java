package com.xtsoft.police.entity;

import java.util.List;

/**
 * 车道的定义
 * 
 * @author xietf
 *
 */
public class LaneEntity {
	private String laneNum;// 车道号
	private String guidanceCode; // 行驶导向编码
	private String guidanceName; // 行驶导向名称
	private String laneId; // 车道ID
	private String laneCode; // 车道编码
	private List<DeviceEntity> deviceList;

	public String getLaneNum() {
		return laneNum;
	}

	public void setLaneNum(String laneNum) {
		this.laneNum = laneNum;
	}

	public String getGuidanceCode() {
		return guidanceCode;
	}

	public void setGuidanceCode(String guidanceCode) {
		this.guidanceCode = guidanceCode;
	}

	public String getGuidanceName() {
		return guidanceName;
	}

	public void setGuidanceName(String guidanceName) {
		this.guidanceName = guidanceName;
	}

	public String getLaneId() {
		return laneId;
	}

	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}

	public String getLaneCode() {
		return laneCode;
	}

	public void setLaneCode(String laneCode) {
		this.laneCode = laneCode;
	}

}
