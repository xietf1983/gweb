
package com.xtsoft.kernel.sys.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.alibaba.fastjson.annotation.JSONField;
import com.xtsoft.kernel.base.BaseEntity;
import com.xtsoft.kernel.util.PropsUtil;

public abstract class AbstractEntity<T> extends BaseEntity {

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 判断缓存是否启用
	 * 
	 * @return
	 */
	@JSONField(serialize = false)
	public boolean getCacheEable() {
		String cacheEnable = PropsUtil.get("CACHEEABLE");
		if (cacheEnable != null && cacheEnable.equals("1")) {
			return true;
		} else {
			return false;
		}
	}

}
