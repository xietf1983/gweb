package com.xtsoft.kernel.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtsoft.kernel.cache.CacheName;
import com.xtsoft.kernel.cache.EhCacheCacheManagerUtil;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.sys.entity.RoleEntity;
import com.xtsoft.kernel.sys.service.persistence.RoleEntityPersistence;
import com.xtsoft.kernel.sys.service.persistence.UserEntityPersistence;

@Transactional
@Service(value = "roleEntityService")
public class RoleEntityServiceImpl {
	private RoleEntityPersistence roleEntityPersistence;
	private UserEntityPersistence userEntityPersistence;

	public RoleEntityPersistence getPersistence() {
		return roleEntityPersistence;
	}

	@Autowired
	public void setRoleEntityPersistence(RoleEntityPersistence roleEntityPersistence) {
		this.roleEntityPersistence = roleEntityPersistence;
	}

	public UserEntityPersistence getUserEntityPersistence() {
		return userEntityPersistence;
	}

	@Autowired
	public void setUserEntityPersistence(UserEntityPersistence userEntityPersistence) {
		this.userEntityPersistence = userEntityPersistence;
	}

	public RoleEntity findEntityByPrimaryKey(String id) {

		return (RoleEntity) getPersistence().findByPrimaryKey(id);
	}

	public RoleEntity updateSaveRoleEntity(RoleEntity entity) {
		getPersistence().updatetEntity(entity);
		return entity;
	}

	public RoleEntity addSaveRoleEntity(RoleEntity entity) {
		getPersistence().insertEntity(entity);
		return entity;
	}

	public RoleEntity deleteRoleEntity(RoleEntity entity) {
		getPersistence().deleteEntity(entity);
		getPersistence().deleteRoleUserEntityList(entity.getRoleId());
		//清理缓存  用户权限
		
		EhCacheCacheManagerUtil.clearcacheName(CacheName.USERMENEPERSION);
		return entity;
	}

	public void updateSavedUserRoles(String[] userIds, String roleId) {
		if (userIds != null && userIds.length > 0) {
			for (String userId : userIds) {
				EhCacheCacheManagerUtil.removeByKey(userId, CacheName.USERMENEPERSION);
				getUserEntityPersistence().addUserRoles(userId, roleId);
			}
		}
	}

	public void removeUserRoles(String[] userIds, String roleId) {
		if (userIds != null && userIds.length > 0) {
			for (String userId : userIds) {
				EhCacheCacheManagerUtil.removeByKey(userId, CacheName.USERMENEPERSION);
				getUserEntityPersistence().removeUserRoles(userId, roleId);
			}
		}
	}

	public void saveRoleRoleMenuEntity(String[] menuIds, String roleId) {
		//清理缓存  用户权限
		EhCacheCacheManagerUtil.clearcacheName(CacheName.USERMENEPERSION);
		getPersistence().saveRoleRoleMenuEntity(roleId, menuIds);
	}

	public List<RoleEntity> findWithDynamicQuery(List<ConditionFilter> list) {
		return getPersistence().findWithDynamicQuery(RoleEntity.FINDWITHDYNAMICQUERY_LIST + RoleEntity.class.getSimpleName(), list);

	}

}
