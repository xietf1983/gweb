package com.xtsoft.kernel.sys.service.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtsoft.kernel.base.BasePersistence;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.sys.entity.SystemDataEntity;

@Repository("systemDataEntityPersistence")
public class SystemDataEntityPersistence extends BasePersistence<SystemDataEntity> {
	public List<SystemDataEntity> getSystemDataEntityList(String dataCode) {
		ConditionFilter filter = new ConditionFilter("DATACODE", dataCode, "×Öµä±àÂë");
		List<ConditionFilter> list = new ArrayList();
		list.add(filter);
		return findWithDynamicQuery(SystemDataEntity.FINDWITHDYNAMICQUERY_LIST + SystemDataEntity.class.getSimpleName(), list);
	}

}
