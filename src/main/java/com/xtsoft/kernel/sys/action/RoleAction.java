package com.xtsoft.kernel.sys.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtsoft.kernel.base.BaseAction;
import com.xtsoft.kernel.constant.DataBaseConstant;
import com.xtsoft.kernel.constant.MessageConstant;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.response.AjaxJsonResult;
import com.xtsoft.kernel.security.shiro.SecurityUtilSimple;
import com.xtsoft.kernel.security.shiro.realm.Principal;
import com.xtsoft.kernel.sys.entity.MenuEntity;
import com.xtsoft.kernel.sys.entity.RoleEntity;
import com.xtsoft.kernel.sys.entity.UserEntity;
import com.xtsoft.kernel.sys.service.CounterEntityServiceUtil;
import com.xtsoft.kernel.sys.service.MenuEntityServiceUtil;
import com.xtsoft.kernel.sys.service.impl.RoleEntityServiceImpl;
import com.xtsoft.kernel.sys.service.impl.UserEntityServiceImpl;

@Controller("roleAction")
@RequestMapping(value = "/role")
public class RoleAction extends BaseAction {
	private RoleEntityServiceImpl _service;

	public RoleEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setRoleEntityServiceImpl(RoleEntityServiceImpl service) {
		_service = service;
	}

	private UserEntityServiceImpl _userService;

	public UserEntityServiceImpl getUserService() {
		return _userService;
	}

	@Autowired
	public void setUserEntityServiceImpl(UserEntityServiceImpl service) {
		_userService = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/getRoleEntityList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJsonResult getRoleEntityList() {
		long startTime = System.currentTimeMillis();
		List<RoleEntity> results = null;
		if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
			try {
				results = getService().findWithDynamicQuery(null);
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.error("getRoleEntityList" + sw.toString());
				long endTime = System.currentTimeMillis();
				return AjaxJsonResult.bulidAjaxPageJsonFail((MessageConstant.UNKNOWN_ERROR), (endTime - startTime));
			}
		}
		long endTime = System.currentTimeMillis();
		return AjaxJsonResult.bulidAjaxPageJsonSucess(results, results.size(), (endTime - startTime));
	}

	@RequestMapping(value = "/getUsersRoleEntityList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJsonResult getUsersRoleEntityList(int page, int rows, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		List<UserEntity> results = null;
		long total = 0;
		if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
			try {
				String roleId = request.getParameter("roleId");
				String name = request.getParameter("name");
				List<ConditionFilter> list = new ArrayList();
				ConditionFilter roleFilter = new ConditionFilter("ROLEID", roleId, "角色ID");
				list.add(roleFilter);
				if (name != null && !name.equals("")) {
					ConditionFilter nameFilter = new ConditionFilter("KEYNAME", "%" + name + "%", "");
					list.add(nameFilter);
				}
				results = getUserService().findWithDynamicQuery(list, (page - 1) * rows+1, rows);
				total = getUserService().countWithDynamicQuery(list);
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.error("getUsersRoleEntityList" + sw.toString());
				long endTime = System.currentTimeMillis();
				return AjaxJsonResult.bulidAjaxPageJsonFail((MessageConstant.UNKNOWN_ERROR), (endTime - startTime));
			}
		}
		long endTime = System.currentTimeMillis();
		return AjaxJsonResult.bulidAjaxPageJsonSucess(results, total, (endTime - startTime));

	}

	@RequestMapping(value = "/getRemainUsersRoleEntityList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJsonResult getRemainUsersRoleEntityList(int page, int rows, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		List<UserEntity> results = null;
		long total = 0;
		if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
			try {
				String roleId = request.getParameter("roleId");
				String name = request.getParameter("name");
				List<ConditionFilter> list = new ArrayList();
				ConditionFilter roleFilter = new ConditionFilter("NOTROLEID", roleId, "角色ID");
				list.add(roleFilter);
				if (name != null && !name.equals("")) {
					ConditionFilter nameFilter = new ConditionFilter("KEYNAME", "%" + name + "%", "");
					list.add(nameFilter);
				}
				results = getUserService().findWithDynamicQuery(list, (page - 1) * rows + 1, rows);
				total = getUserService().countWithDynamicQuery(list);
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.error("getRemainUsersRoleEntityList" + sw.toString());
				long endTime = System.currentTimeMillis();
				return AjaxJsonResult.bulidAjaxPageJsonFail((MessageConstant.UNKNOWN_ERROR), (endTime - startTime));
			}
		}
		long endTime = System.currentTimeMillis();
		return AjaxJsonResult.bulidAjaxPageJsonSucess(results, total, (endTime - startTime));
	}

	@RequestMapping(value = "/deleteRoleEntity", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("role:delete")
	public AjaxJsonResult deleteRoleEntity(RoleEntity entity) {
		if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
			try {
				getService().deleteRoleEntity(entity);
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.error("deleteRoleEntity" + sw.toString());
				return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
			}
		}
		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	@RequestMapping(value = "/saveRoleEntity", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("role:create")
	public AjaxJsonResult saveRoleEntity(RoleEntity roleEntity) {
		if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
			try {
				if (roleEntity.getRoleId() == null || roleEntity.getRoleId().equals("")) {
					roleEntity.setRoleId(CounterEntityServiceUtil.getService().getPersistence().increment(RoleEntity.class.getName()) + "");
					Principal principal = SecurityUtilSimple.getPrincipal();
					roleEntity.setCreateBy(principal.getId());
					roleEntity.setCreateDate(new Date());
					getService().addSaveRoleEntity(roleEntity);
				} else {
					getService().updateSaveRoleEntity(roleEntity);
				}
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.error("saveRoleEntity" + sw.toString());
				return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
			}
		}
		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	@RequestMapping(value = "/saveRoleUsersEntity", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("role:user")
	public AjaxJsonResult saveRoleUsersEntity(String[] userIds, String roleId) {
		if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
			try {
				getService().updateSavedUserRoles(userIds, roleId);
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.error("saveRoleEntity" + sw.toString());
				return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
			}
		}
		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	@RequestMapping(value = "/removeUserRoles", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("role:user")
	public AjaxJsonResult deleteRoleUserEntity(String[] userIds, String roleId) {
		if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
			try {
				getService().removeUserRoles(userIds, roleId);
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.error("removeUserRoles" + sw.toString());
				return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
			}
		}
		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	@RequestMapping(value = "/saveRoleMenuEntity", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("role:set")
	public AjaxJsonResult saveRoleRoleMenuEntity(String[] menuIds, String roleId) {
		if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
			try {
				getService().saveRoleRoleMenuEntity(menuIds, roleId);
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.error("saveRoleRoleMenuEntity" + sw.toString());
				return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
			}
		}
		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	@RequestMapping(value = "/getRoleMenuEntityTree", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getRoleMenuEntityTree(String roleId) {
		List<Map<String, Object>> list = new ArrayList();
		// 获取所有的权限
		List<String> typeList = new ArrayList();
		List<MenuEntity> menuList = MenuEntityServiceUtil.getService().findMenuEntityByUserIdAndParentIdTree("", DataBaseConstant.ROOTID,typeList);
		// 获取拥有的权限

		List<MenuEntity> hasmenuList = MenuEntityServiceUtil.getService().findMenuEntityByRoleId(roleId);
		Map<String, String> menuMap = new HashMap<String, String>();
		if (hasmenuList != null && hasmenuList.size() > 0) {
			for (MenuEntity m : hasmenuList) {
				menuMap.put(m.getId(), m.getId());
			}
		}
		if (menuList != null && menuList.size() > 0) {
			list = createTreeChildren(menuList, DataBaseConstant.ROOTID, menuMap);
		}

		return list;
	}

	private List<Map<String, Object>> createTreeChildren(List<MenuEntity> childrenList, String fid, Map<String, String> menuMap) {
		List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
		for (int j = 0; j < childrenList.size(); j++) {
			Map<String, Object> map = new HashMap();
			MenuEntity treeChild = (MenuEntity) childrenList.get(j);
			map.put("id", treeChild.getId());
			map.put("text", treeChild.getName());
			if (menuMap != null && menuMap.get(treeChild.getId()) != null) {
				map.put("checked", true);
			}
			map.put("parentId", fid);

			if (treeChild.getMenuItemList() != null && treeChild.getMenuItemList().size() > 0) {
				map.put("children", createTreeChildren(treeChild.getMenuItemList(), treeChild.getId(), menuMap));
			} else {
				map.put("iconCls", treeChild.getMenuIcon());
			}
			if (map != null)
				childList.add(map);
		}
		return childList;
	}

}
