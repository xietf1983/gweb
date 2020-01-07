package com.xtsoft.police.entity;

import java.util.List;

import com.xtsoft.kernel.sys.entity.DataEntity;

/**
 * 地点
 * 
 * @author xietf
 *
 */
public class PlaceEntity extends DataEntity<PlaceEntity> {
	private String id;// ID
	private String name;// 名称
	private Double longitude;// 经度
	private Double latitude;// 纬度
	private String placeType;// 场所类型
	private String placeTypeShowValue;
	private String createDateStr;
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public String getPlaceTypeShowValue() {
		return placeTypeShowValue;
	}
	public void setPlaceTypeShowValue(String placeTypeShowValue) {
		this.placeTypeShowValue = placeTypeShowValue;
	}
	private String eqpId;// 所属区域
	private String eqpName;// 所属区域
	public String getEqpName() {
		return eqpName;
	}
	public void setEqpName(String eqpName) {
		this.eqpName = eqpName;
	}
	private String code;// 场所编号(如国标 等)
	private String installation;// 安装的位置 字典
	private List<DirectionEntity> list;// 方向
	private Integer videoCount;//路口下面摄像机数量
	public Integer getVideoCount() {
		return videoCount;
	}
	public void setVideoCount(Integer videoCount) {
		this.videoCount = videoCount;
	}
	private String crossingType;// 路口类型 如十字路口，环行路口等 字典
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
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getPlaceType() {
		return placeType;
	}
	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}
	public String getEqpId() {
		return eqpId;
	}
	public void setEqpId(String eqpId) {
		this.eqpId = eqpId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInstallation() {
		return installation;
	}
	public void setInstallation(String installation) {
		this.installation = installation;
	}
	public List<DirectionEntity> getList() {
		return list;
	}
	public void setList(List<DirectionEntity> list) {
		this.list = list;
	}
	public String getCrossingType() {
		return crossingType;
	}
	public void setCrossingType(String crossingType) {
		this.crossingType = crossingType;
	}

}
