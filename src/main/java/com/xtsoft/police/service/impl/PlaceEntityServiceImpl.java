package com.xtsoft.police.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtsoft.kernel.constant.PoliceConstant;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.sys.entity.LogEventEntity;
import com.xtsoft.kernel.sys.entity.UserEntity;
import com.xtsoft.police.entity.DirectionEntity;
import com.xtsoft.police.entity.PlaceEntity;
import com.xtsoft.police.service.persistence.PlaceEntityPersistence;

@Transactional
@Service(value = "placeEntityService")
public class PlaceEntityServiceImpl {
	private PlaceEntityPersistence placeEntityPersistence;

	public PlaceEntityPersistence getPersistence() {
		return placeEntityPersistence;
	}

	@Autowired
	public void setPlaceEntityPersistence(PlaceEntityPersistence placeEntityPersistence) {
		this.placeEntityPersistence = placeEntityPersistence;
	}

	public PlaceEntity findEntityByPrimaryKey(String id) {
		PlaceEntity placeEntity = (PlaceEntity) getPersistence().findByPrimaryKey(id);
		return placeEntity;
	}
	
	public List<DirectionEntity> getDirectionEntityList(String id) {
		List<ConditionFilter> list = new ArrayList();
		ConditionFilter filter = new ConditionFilter("PLACEID", id, PoliceConstant.PLACEID_SHOWNAME);
		return getPersistence().getDirectionEntityList(list);
	}
	
	public long countWithDynamicQuery(List<ConditionFilter> list) {
		return getPersistence().countWithDynamicQuery(UserEntity.FINDWITHDYNAMICQUERY_COUNT + PlaceEntity.class.getSimpleName(), list);
	}

	public List<PlaceEntity> findWithDynamicQuery(List<ConditionFilter> list, int start, int limit) {

		return getPersistence().findWithDynamicQuery(LogEventEntity.FINDWITHDYNAMICQUERY_PAGELIST + PlaceEntity.class.getSimpleName(), list, start, limit);
	}
}
