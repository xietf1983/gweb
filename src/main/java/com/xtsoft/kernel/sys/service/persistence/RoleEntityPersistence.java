package com.xtsoft.kernel.sys.service.persistence;

import org.springframework.stereotype.Repository;

import com.xtsoft.kernel.base.BasePersistence;
import com.xtsoft.kernel.sys.entity.RoleEntity;
import com.xtsoft.kernel.sys.entity.RoleMenuPK;

@Repository("roleEntityPersistence")
public class RoleEntityPersistence extends BasePersistence<RoleEntity> {
	public void saveRoleRoleMenuEntity(String roleId, String[] menuIds) {
		RoleMenuPK para = new RoleMenuPK();
		para.setRoleId(roleId);
		getSqlSession().delete(para.getClass().getSimpleName() + RoleMenuPK.DELETEENTITY, para);
		if (menuIds != null && menuIds.length > 0) {
			for (String menuId : menuIds) {
				RoleMenuPK roleMenu = new RoleMenuPK();
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(menuId);
				//insertEntity(roleMenu);
				getSqlSession().insert(roleMenu.getClass().getSimpleName() + RoleMenuPK.INSERTENTITY, roleMenu);
			}
		}
	}

	public void deleteRoleUserEntityList(String roleId) {
		getSqlSession().delete("deleteRoleUserEntityList_ByRoleID", roleId);
	}

}
