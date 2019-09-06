
package com.xtsoft.kernel.sys.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.xtsoft.kernel.base.BaseEntity;


@SuppressWarnings("serial")
public abstract class AbstractEntity<T> extends BaseEntity {

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	/**
	 * 判断缓存是否启用
	 * @return
	 */
	public boolean  getCacheEable(){
		
	}
	

}
