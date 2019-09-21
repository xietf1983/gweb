package com.xtsoft.kernel.sys.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class DataEntity<ID> extends AbstractEntity<ID> {

	private static final long serialVersionUID = 1L;
	@JSONField(serialize = false)
	protected String remarks;
	@JSONField(serialize = false)
	protected String createBy;
	@JSONField(serialize = false)
	protected Date createDate;
	@JSONField(serialize = false)
	protected String updateBy;
	@JSONField(serialize = false)
	protected Date updateDate;
	@JSONField(serialize = false)
	protected String delFlag = "0";
	
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

}
