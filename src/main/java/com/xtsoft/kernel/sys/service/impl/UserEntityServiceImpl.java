package com.xtsoft.kernel.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtsoft.kernel.constant.DataBaseConstant;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.sys.entity.UserEntity;
import com.xtsoft.kernel.sys.entity.UserOrgsPK;
import com.xtsoft.kernel.sys.service.persistence.UserEntityPersistence;

@Transactional
@Service(value = "userEntityService")
public class UserEntityServiceImpl {
	private UserEntityPersistence userEntityPersistence;

	public UserEntityPersistence getPersistence() {
		return userEntityPersistence;
	}

	@Autowired
	public void setUserEntityPersistence(UserEntityPersistence userEntityPersistence) {
		this.userEntityPersistence = userEntityPersistence;
	}

	public UserEntity findEntityByPrimaryKey(UserEntity id) {
		return (UserEntity) getPersistence().findByPrimaryKey(id);
	}

	public UserEntity updateSaveUserEntity(UserEntity userEntity) {
		getPersistence().updatetEntity(userEntity);
		return userEntity;
	}

	public UserEntity updateSaveUserEntity(UserEntity userEntity, String[] orgIdList, String[] roleList, String[] userGroupList) {
		getPersistence().updatetEntity(userEntity);
		getPersistence().removeUserOrg(userEntity.getUserId(), null);
		getPersistence().removeUserRoles(userEntity.getUserId(), null);
		getPersistence().removeUserGroup(userEntity.getUserId(), null);
		if (orgIdList != null && orgIdList.length > 0) {
			for (String orgId : orgIdList) {
				getPersistence().addUserOrg(userEntity.getUserId(), orgId);
			}
		}
		if (roleList != null && roleList.length > 0) {
			for (String roleId : roleList) {
				getPersistence().addUserRoles(userEntity.getUserId(), roleId);
			}
		}
		if (userGroupList != null && userGroupList.length > 0) {
			for (String groupId : userGroupList) {
				getPersistence().addUserGroup(userEntity.getUserId(), groupId);
			}
		}
		return userEntity;
	}

	public UserEntity addSaveUserEntity(UserEntity userEntity, String[] orgIdList, String[] roleList, String[] userGroupList) {
		getPersistence().insertEntity(userEntity);
		if (orgIdList != null && orgIdList.length > 0) {
			for (String orgId : orgIdList) {
				getPersistence().addUserOrg(userEntity.getUserId(), orgId);
			}
		}
		if (roleList != null && roleList.length > 0) {
			for (String roleId : roleList) {
				getPersistence().addUserRoles(userEntity.getUserId(), roleId);
			}
		}
		if (userGroupList != null && userGroupList.length > 0) {
			for (String groupId : userGroupList) {
				getPersistence().addUserGroup(userEntity.getUserId(), groupId);
			}
		}
		return userEntity;
	}

	public long countWithDynamicQuery(List<ConditionFilter> list) {
		return getPersistence().countWithDynamicQuery(UserEntity.FINDWITHDYNAMICQUERY_COUNT_ + UserEntity.class.getSimpleName(), list);
	}

	public List findWithDynamicQuery(List<ConditionFilter> list) {
		return getPersistence().findWithDynamicQuery(UserEntity.FINDWITHDYNAMICQUERY_LIST_ + UserEntity.class.getSimpleName(), list);

	}

	public List findWithDynamicQuery(List<ConditionFilter> list, int start, int limit) {

		return getPersistence().findWithDynamicQuery(UserEntity.FINDWITHDYNAMICQUERY_PAGELIST_ + UserEntity.class.getSimpleName(), list, start, limit);
	}

	public UserEntity deleteUserEntity(UserEntity entity) {
		getPersistence().deleteEntity(entity);
		return entity;
	}

	public List<UserOrgsPK> findOrganizationIdListByUserId(String userId) {
		List<ConditionFilter> list = new ArrayList();
		ConditionFilter nameFilter = new ConditionFilter("USERID", userId, "用户名ID");
		list.add(nameFilter);
		return getPersistence().findWithDynamicQuery(DataBaseConstant.STATEMENT_FINDORGANIZATIONIDLISTBYUSERID, list);
	}

}
