package com.xtsoft.kernel.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.sys.entity.LogEventEntity;
import com.xtsoft.kernel.sys.entity.UserEntity;
import com.xtsoft.kernel.sys.service.persistence.LogEventEntityPersistence;

@Service(value = "logEventEntityService")
public class LogEventEntityServiceImpl {
	private LogEventEntityPersistence persistence;

	public LogEventEntityPersistence getPersistence() {
		return persistence;
	}

	@Autowired
	public void setLogEventEntityPersistence(LogEventEntityPersistence persistence) {
		this.persistence = persistence;
	}

	public long countWithDynamicQuery(List<ConditionFilter> list) {
		return getPersistence().countWithDynamicQuery(UserEntity.FINDWITHDYNAMICQUERY_COUNT + LogEventEntity.class.getSimpleName(), list);
	}

	public List<LogEventEntity> findWithDynamicQuery(List<ConditionFilter> list, int start, int limit) {

		return getPersistence().findWithDynamicQuery(LogEventEntity.FINDWITHDYNAMICQUERY_PAGELIST + LogEventEntity.class.getSimpleName(), list, start, limit);
	}

}
