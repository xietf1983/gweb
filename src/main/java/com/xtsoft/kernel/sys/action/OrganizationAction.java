package com.xtsoft.kernel.sys.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.xtsoft.kernel.response.AjaxJsonResult;
import com.xtsoft.kernel.security.shiro.SecurityUtilSimple;
import com.xtsoft.kernel.security.shiro.realm.Principal;
import com.xtsoft.kernel.sys.entity.OrganizationEntity;
import com.xtsoft.kernel.sys.service.CounterEntityServiceUtil;
import com.xtsoft.kernel.sys.service.impl.OrganizationEntityServiceImpl;
import com.xtsoft.kernel.sys.service.impl.UserEntityServiceImpl;

@Controller("organizationAction")
@RequestMapping(value = "/organization")
public class OrganizationAction extends BaseAction {
	private OrganizationEntityServiceImpl _service;

	public OrganizationEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setOrganizationEntityServiceImpl(OrganizationEntityServiceImpl service) {
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

	@RequestMapping(value = "/getOrganizationEntityTree", method = RequestMethod.POST)
	@ResponseBody
	public List<OrganizationEntity> getOrganizationEntityTree() {
		// 获取所有的权限
		List<OrganizationEntity> entityList = null;
		try {
			entityList = getService().findOrganizationEntityByParentIdTree(DataBaseConstant.ROOTID);
		} catch (Exception e) {
			int a = 0;
		}
		return entityList;
	}

	@RequestMapping(value = "/getOrganizationEntityTreeWithSelect", method = RequestMethod.POST)
	@ResponseBody
	public List<OrganizationEntity> getOrganizationEntityTreeWithSelect() {
		// 获取所有的权限
		List<OrganizationEntity> list = new ArrayList();
		OrganizationEntity o = new OrganizationEntity();
		o.setText("---请选择---");
		o.setId(DataBaseConstant.ROOTID);
		List<OrganizationEntity> entityList = null;
		try {
			entityList = getService().findOrganizationEntityByParentIdTree(DataBaseConstant.ROOTID);
		} catch (Exception e) {
			int a = 0;
		}
		o.setChildren(entityList);
		list.add(o);
		return list;
	}

	@RequestMapping(value = "/deleteOrganizationEntity", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("org:delete")
	public AjaxJsonResult deleteOrganizationEntity(String id) {
		try {
			List<OrganizationEntity> chrildList = getService().findOrganizationEntityByParentId(id);

			if (chrildList == null || chrildList.size() == 0) {
				getService().removeOrganizationEntity(id);
			} else {
				return AjaxJsonResult.buildResultFail(MessageConstant.REMOVE_CHRILD_MSG);
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("deleteOrganizationEntity" + sw.toString());
			return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
		}

		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	@RequestMapping(value = "/saveOrganizationEntity", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJsonResult saveOrganizationEntity(OrganizationEntity entity) {
		try {
			entity.setId(CounterEntityServiceUtil.getService().getPersistence().increment(OrganizationEntity.class.getName()) + "");
			if (entity.getParentId().equals(DataBaseConstant.ROOTID)) {
				entity.setFullPath(DataBaseConstant.ROOTID + "\\" + entity.getId());
				entity.setFullName(entity.getName());
			} else {
				OrganizationEntity o = getService().findEntityByPrimaryKey(entity.getParentId());
				entity.setFullName(o.getFullName() + "\\" + entity.getName());
				entity.setFullPath(o.getFullPath() + "\\" + entity.getId());
			}
			Principal principal = SecurityUtilSimple.getPrincipal();
			entity.setUserId(principal.getId());
			entity.setUserName(principal.getRealname());
			entity.setModifiedDate(new Date());
			entity.setSortId(Long.parseLong(entity.getId()));
			entity.setCreateDate(new Date());
			entity.setActive(1);
			getService().addSaveEntity(entity);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("saveRoleEntity" + sw.toString());
			return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
		}
		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	/**
	 * 组织机构排序
	 * 
	 * @param id
	 * @param sortId
	 * @return
	 */
	@RequestMapping(value = "/updateOrganizationEntitySort", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJsonResult updateOrganizationEntitySort(String id, long sortId) {
		try {
			OrganizationEntity entity = getService().findEntityByPrimaryKey(id);
			if (entity != null) {
				String parentId = entity.getParentId();
				List<OrganizationEntity> chrildList = getService().findOrganizationEntityByParentId(parentId);
				if (chrildList != null && chrildList.size() > 0) {
					for (int j = 0; j < chrildList.size(); j++) {
						OrganizationEntity e = chrildList.get(j);
						if (e.getId().equals(id)) {
							if (sortId > 0) {
								// 向上排序
								if (j == 0) {
									return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
								} else {
									OrganizationEntity pre = chrildList.get(j - 1);
									sortId = pre.getSortId();
									pre.setSortId(entity.getSortId());
									getService().updateSaveEntity(pre);
									entity.setSortId(sortId);
									getService().updateSaveEntity(entity);
									break;

								}
							} else {
								if (j == chrildList.size() - 1) {
									return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
								} else {
									OrganizationEntity pre = chrildList.get(j + 1);
									sortId = pre.getSortId();
									pre.setSortId(entity.getSortId());
									getService().updateSaveEntity(pre);
									entity.setSortId(sortId);
									getService().updateSaveEntity(entity);
									break;
								}

							}
						}
					}

				}
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("updateOrganizationEntitySort" + sw.toString());
			return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
		}
		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

}
