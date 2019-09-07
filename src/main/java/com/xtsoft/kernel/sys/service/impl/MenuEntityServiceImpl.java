package com.xtsoft.kernel.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtsoft.kernel.cache.CacheName;
import com.xtsoft.kernel.cache.EhCacheCacheManagerUtil;
import com.xtsoft.kernel.constant.DataBaseConstant;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.sys.entity.MenuEntity;
import com.xtsoft.kernel.sys.entity.UserEntity;
import com.xtsoft.kernel.sys.service.persistence.MenuEntityPersistence;

@Transactional
@Service(value = "menuEntityService")
public class MenuEntityServiceImpl {
	private MenuEntityPersistence menuEntityPersistence;

	public MenuEntityPersistence getPersistence() {
		return menuEntityPersistence;
	}

	@Autowired
	public void setMenuEntityPersistence(MenuEntityPersistence menuEntityPersistence) {
		this.menuEntityPersistence = menuEntityPersistence;
	}

	public MenuEntity findEntityByPrimaryKey(String id) {

		return (MenuEntity) getPersistence().findByPrimaryKey(id);
	}

	public MenuEntity updateSaveMenuEntity(MenuEntity entity) {
		getPersistence().updatetEntity(entity);
		return entity;
	}

	public MenuEntity addSaveMenuEntity(MenuEntity entity) {
		getPersistence().insertEntity(entity);
		return entity;
	}

	public long countWithDynamicQuery(List<ConditionFilter> list) {
		return getPersistence().countWithDynamicQuery(UserEntity.FINDWITHDYNAMICQUERY_COUNT + MenuEntity.class.getSimpleName(), list);
	}

	public MenuEntity findHomeMenuEntityByUserId(String userId) {
		List<ConditionFilter> list = new ArrayList();
		if (userId != null && !userId.equals("")) {
			ConditionFilter filter = new ConditionFilter("USERID", userId, DataBaseConstant.USERID);
			list.add(filter);
		}
		List<String> typeList = new ArrayList<String>();
		typeList.add("0");
		ConditionFilter filter = new ConditionFilter("TYPES", typeList, DataBaseConstant.MENUTYPE);
		list.add(filter);
		List<MenuEntity> menuList = findWithDynamicQuery(list);
		if (menuList != null && menuList.size() > 0) {
			return menuList.get(0);
		}
		return null;
	}

	public List<MenuEntity> findMenuEntityByUserIdAndParentId(String userId, String parentId) {
		List<ConditionFilter> list = new ArrayList();
		if (userId != null && !userId.equals("")) {
			ConditionFilter filter = new ConditionFilter("USERID", userId, DataBaseConstant.USERID);
			list.add(filter);
		}
		if (parentId != null && !parentId.equals("")) {
			ConditionFilter filter = new ConditionFilter("PARENTID", parentId, DataBaseConstant.PARENTID);
			list.add(filter);
		}
		List<String> typeList = new ArrayList<String>();
		typeList.add("1");
		typeList.add("2");
		ConditionFilter filter = new ConditionFilter("TYPES", typeList, DataBaseConstant.MENUTYPE);
		list.add(filter);
		return findWithDynamicQuery(list);
	}

	public List<MenuEntity> findMenuEntityByUserIdAndParentIdTree(String userId, String parentId) {
		List<ConditionFilter> list = new ArrayList();
		if (userId != null && !userId.equals("")) {
			ConditionFilter filter = new ConditionFilter("USERID", userId, DataBaseConstant.USERID);
			list.add(filter);
		}
		if (parentId != null && !parentId.equals("")) {
			ConditionFilter filter = new ConditionFilter("PARENTID", parentId, DataBaseConstant.PARENTID);
			list.add(filter);
		}
		List<String> typeList = new ArrayList<String>();
		typeList.add("1");
		typeList.add("2");
		ConditionFilter filter = new ConditionFilter("TYPES", typeList, DataBaseConstant.MENUTYPE);
		list.add(filter);
		List<MenuEntity> menuList = findWithDynamicQuery(list);
		if (menuList != null && menuList.size() > 0) {
			for (MenuEntity r : menuList) {
				if (r.getType() != null && r.getType().equals(DataBaseConstant.MENUTYPE_1)) {
					if (r.isHasChildren()) {

						r.setMenuItemList(findMenuEntityByUserIdAndParentIdTree(userId, r.getId()));
					}
				}
			}
		}
		return menuList;
	}

	public List<MenuEntity> findMenuEntityByRoleId(String roleId) {
		List<ConditionFilter> list = new ArrayList();
		if (roleId != null && !roleId.equals("")) {
			ConditionFilter filter = new ConditionFilter("ROLEID", roleId, DataBaseConstant.ROLEID);
			list.add(filter);
		}
		return findWithDynamicQuery(list);
	}

	/**
	 * 获取
	 * 
	 * @param userId
	 * @return
	 */
	public List<MenuEntity> findMenuEntityButtonByUserId(String userId) {
		List<ConditionFilter> list = new ArrayList();
		if (userId != null && !userId.equals("")) {
			ConditionFilter filter = new ConditionFilter("USERID", userId, DataBaseConstant.USERID);
			list.add(filter);
		}
		List<String> typeList = new ArrayList<String>();
		typeList.add("2");
		ConditionFilter filterType = new ConditionFilter("TYPES", typeList, DataBaseConstant.MENUTYPE);
		list.add(filterType);
		List<MenuEntity> listRet = findWithDynamicQuery(list);
		//增加到缓存
		EhCacheCacheManagerUtil.putObject(userId, CacheName.USERMENEPERSION, ArrayList.class);
		return  listRet;
	}

	public List<MenuEntity> findWithDynamicQuery(List<ConditionFilter> list) {
		return getPersistence().findWithDynamicQuery(MenuEntity.FINDWITHDYNAMICQUERY_LIST + MenuEntity.class.getSimpleName(), list);

	}

	public List<MenuEntity> findWithDynamicQuery(List<ConditionFilter> list, int start, int limit) {
		return getPersistence().findWithDynamicQuery(UserEntity.FINDWITHDYNAMICQUERY_PAGELIST + MenuEntity.class.getSimpleName(), list, start, limit);
	}

}
