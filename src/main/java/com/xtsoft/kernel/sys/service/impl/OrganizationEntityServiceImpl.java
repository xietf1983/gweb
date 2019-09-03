package com.xtsoft.kernel.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtsoft.kernel.constant.DataBaseConstant;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.sys.entity.OrganizationEntity;
import com.xtsoft.kernel.sys.service.persistence.OrganizationEntityPersistence;

@Transactional
@Service(value = "organizationEntityService")
public class OrganizationEntityServiceImpl {
	private OrganizationEntityPersistence entityPersistence;

	public OrganizationEntityPersistence getPersistence() {
		return entityPersistence;
	}

	@Autowired
	public void setMenuEntityPersistence(OrganizationEntityPersistence entityPersistence) {
		this.entityPersistence = entityPersistence;
	}

	public OrganizationEntity findEntityByPrimaryKey(String id) {
		OrganizationEntity o = new OrganizationEntity();
		o.setId(id);
		return (OrganizationEntity) getPersistence().findByPrimaryKey(o);
	}

	public OrganizationEntity updateSaveEntity(OrganizationEntity entity) {
		getPersistence().updatetEntity(entity);
		return entity;
	}

	public OrganizationEntity addSaveEntity(OrganizationEntity entity) {
		getPersistence().insertEntity(entity);
		return entity;
	}

	public long countWithDynamicQuery(List<ConditionFilter> list) {
		return getPersistence().countWithDynamicQuery(OrganizationEntity.FINDWITHDYNAMICQUERY_COUNT + OrganizationEntity.class.getSimpleName(), list);
	}

	public List<OrganizationEntity> findOrganizationEntityByParentId(String parentId) {
		List<ConditionFilter> list = new ArrayList();
		if (parentId != null && !parentId.equals("")) {
			ConditionFilter filter = new ConditionFilter("PARENTID", parentId, DataBaseConstant.PARENTID);
			list.add(filter);
		}
		return findWithDynamicQuery(list);
	}

	public void removeOrganizationEntity(String id) {
		OrganizationEntity e = new OrganizationEntity();
		e.setId(id);
		getPersistence().deleteEntity(e);
	}

	public List<OrganizationEntity> findOrganizationEntityByParentIdTree(String parentId) {
		List<ConditionFilter> list = new ArrayList();
		if (parentId != null && !parentId.equals("")) {
			ConditionFilter filter = new ConditionFilter("PARENTID", parentId, DataBaseConstant.PARENTID);
			list.add(filter);
		}
		List<OrganizationEntity> entityList = findWithDynamicQuery(list);
		if (entityList != null && entityList.size() > 0) {
			for (OrganizationEntity r : entityList) {
				if (r.isHasChildren()) {
					// 有子节点
					r.setChildren(findOrganizationEntityByParentIdTree(r.getId()));
				}
			}
		}
		return entityList;
	}

	public List<OrganizationEntity> findWithDynamicQuery(List<ConditionFilter> list) {
		return getPersistence().findWithDynamicQuery(OrganizationEntity.FINDWITHDYNAMICQUERY_LIST + OrganizationEntity.class.getSimpleName(), list);

	}

	public List<OrganizationEntity> findWithDynamicQuery(List<ConditionFilter> list, int start, int limit) {
		return getPersistence().findWithDynamicQuery(OrganizationEntity.FINDWITHDYNAMICQUERY_PAGELIST + OrganizationEntity.class.getSimpleName(), list, start, limit);
	}

}
