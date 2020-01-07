package com.xtsoft.police.service.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtsoft.kernel.base.BasePersistence;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.police.entity.DirectionEntity;
import com.xtsoft.police.entity.PlaceEntity;

@Repository("placeEntityPersistence")
public class PlaceEntityPersistence extends BasePersistence<PlaceEntity>{
	public List<DirectionEntity> getDirectionEntityList(List<ConditionFilter> list ) {
		return findWithDynamicQuery(DirectionEntity.FINDWITHDYNAMICQUERY_LIST + DirectionEntity.class.getSimpleName(), list);
	}
}
