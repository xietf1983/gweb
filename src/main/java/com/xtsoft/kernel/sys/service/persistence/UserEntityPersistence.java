package com.xtsoft.kernel.sys.service.persistence;

import org.springframework.stereotype.Repository;

import com.xtsoft.kernel.base.BasePersistence;
import com.xtsoft.kernel.sys.entity.UserEntity;
import com.xtsoft.kernel.sys.entity.UserGroupsPK;
import com.xtsoft.kernel.sys.entity.UserOrgsPK;
import com.xtsoft.kernel.sys.entity.UserRolesPK;

@Repository("userEntityPersistence")
public class UserEntityPersistence extends BasePersistence<UserEntity> {
	/**
	 * 判断是否有此角色
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public boolean containsRoles(String userId, String roleId) {
		UserRolesPK para = new UserRolesPK();
		para.setUserId(userId);
		para.setRoleId(roleId);
		UserRolesPK userRoles = (UserRolesPK) findByPrimaryKey(para);
		if (userRoles != null) {
			return true;
		}
		return false;
	}

	public void addUserRoles(String userId, String roleId) {
		if (!containsRoles(userId, roleId)) {
			UserRolesPK para = new UserRolesPK();
			para.setUserId(userId);
			para.setRoleId(roleId);
			getSqlSession().insert(UserRolesPK.class.getSimpleName() + UserRolesPK.INSERTENTITY, para);
		}
	}

	public void removeUserRoles(String userId, String roleId) {
		UserRolesPK para = new UserRolesPK();
		para.setUserId(userId);
		para.setRoleId(roleId);
		getSqlSession().delete(UserRolesPK.class.getSimpleName() + UserRolesPK.DELETEENTITY, para);
	}

	/**
	 * 判断是否有此角色
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public boolean containsOrg(String userId, String orgId) {
		UserOrgsPK para = new UserOrgsPK();
		para.setUserId(userId);
		para.setOrganizationId(orgId);
		UserRolesPK userOrgs = (UserRolesPK) findByPrimaryKey(para);
		if (userOrgs != null) {
			return true;
		}
		return false;
	}

	public void addUserOrg(String userId, String orgId) {
		if (!containsOrg(userId, orgId)) {
			UserOrgsPK para = new UserOrgsPK();
			para.setUserId(userId);
			para.setOrganizationId(orgId);
			getSqlSession().insert(UserOrgsPK.class.getSimpleName() + UserOrgsPK.INSERTENTITY, para);
		}
	}

	public void removeUserOrg(String userId, String orgId) {
		UserOrgsPK para = new UserOrgsPK();
		para.setUserId(userId);
		para.setOrganizationId(orgId);
		getSqlSession().delete(UserOrgsPK.class.getSimpleName() + UserOrgsPK.DELETEENTITY, para);
	}

	/**
	 * 判断是否有此角色
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public boolean containsUserGroup(String userId, String groupId) {
		UserGroupsPK para = new UserGroupsPK();
		para.setUserId(userId);
		para.setGroupId(groupId);
		UserGroupsPK userGroupsPK = (UserGroupsPK) findByPrimaryKey(para);
		if (userGroupsPK != null) {
			return true;
		}
		return false;
	}

	public void addUserGroup(String userId, String groupId) {
		if (!containsUserGroup(userId, groupId)) {
			UserGroupsPK para = new UserGroupsPK();
			para.setUserId(userId);
			para.setGroupId(groupId);
			getSqlSession().insert(UserGroupsPK.class.getSimpleName() + UserGroupsPK.INSERTENTITY, para);
		}
	}

	public void removeUserGroup(String userId, String groupId) {
		UserGroupsPK para = new UserGroupsPK();
		para.setUserId(userId);
		para.setGroupId(groupId);
		getSqlSession().delete(UserGroupsPK.class.getSimpleName() + UserGroupsPK.DELETEENTITY, para);
	}

}
