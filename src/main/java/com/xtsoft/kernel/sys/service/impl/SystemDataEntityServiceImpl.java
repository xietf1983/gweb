package com.xtsoft.kernel.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtsoft.kernel.sys.entity.SystemDataEntity;
import com.xtsoft.kernel.sys.service.persistence.SystemDataEntityPersistence;

@Transactional
@Service(value = "systemDataEntityService")
public class SystemDataEntityServiceImpl {
	private SystemDataEntityPersistence systemDataEntityPersistence;

	public SystemDataEntityPersistence getPersistence() {
		return systemDataEntityPersistence;
	}

	@Autowired
	public void setSystemDataEntityPersistence(SystemDataEntityPersistence systemDataEntityPersistence) {
		this.systemDataEntityPersistence = systemDataEntityPersistence;
	}

	public SystemDataEntity findEntityByPrimaryKey(SystemDataEntity id) {
		return (SystemDataEntity) getPersistence().findByPrimaryKey(id);
	}

	public List<SystemDataEntity> getSystemDataEntityList(String dataCode) {
		return getPersistence().getSystemDataEntityList(dataCode);
	}

}
