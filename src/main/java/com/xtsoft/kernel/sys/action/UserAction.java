package com.xtsoft.kernel.sys.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
import com.xtsoft.kernel.sys.entity.UserEntity;
import com.xtsoft.kernel.sys.service.CounterEntityServiceUtil;
import com.xtsoft.kernel.sys.service.impl.UserEntityServiceImpl;
import com.xtsoft.kernel.util.MD5;

@Controller("userAction")
@RequestMapping(value = "/user")
public class UserAction extends BaseAction {
	private UserEntityServiceImpl _userService;

	public UserEntityServiceImpl getUserService() {
		return _userService;
	}

	@Autowired
	public void setUserEntityServiceImpl(UserEntityServiceImpl service) {
		_userService = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取用户列表
	 * 
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUsersEntityList", method = RequestMethod.POST)
	@ResponseBody
	@RequiresAuthentication
	public AjaxJsonResult getUsersEntityList(int page, int rows, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		List<UserEntity> results = null;
		long total = 0;

		try {
			String organizationId = request.getParameter("ORGANIZATIONID");
			String keyName = request.getParameter("KEYNAME");
			String status = request.getParameter("STATUS");
			List<ConditionFilter> list = new ArrayList();
			if (organizationId != null && !organizationId.equals("") && !organizationId.equals(DataBaseConstant.ROOTID)) {
				ConditionFilter orgFilter = new ConditionFilter("ORGANIZATIONID", organizationId, "部门ID");
				list.add(orgFilter);
			}
			if (keyName != null && !keyName.equals("")) {
				ConditionFilter nameFilter = new ConditionFilter("KEYNAME", "%" + keyName + "%", "用户名");
				list.add(nameFilter);
			}
			if (status != null && !status.equals("")) {
				ConditionFilter statusFilter = new ConditionFilter("STATUS", status, "用户状态");
				list.add(statusFilter);
			}
			results = getUserService().findWithDynamicQuery(list, (page - 1) * rows, rows);
			total = getUserService().countWithDynamicQuery(list);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("getUsersEntityList" + sw.toString());
			long endTime = System.currentTimeMillis();
			return AjaxJsonResult.bulidAjaxPageJsonFail((MessageConstant.UNKNOWN_ERROR), (endTime - startTime));
		}

		long endTime = System.currentTimeMillis();
		return AjaxJsonResult.bulidAjaxPageJsonSucess(results, total, (endTime - startTime));
	}

	/**
	 * 保存用户
	 * 
	 * @param userEntity
	 * @param orgIdList
	 * @param roleList
	 * @param userGroupList
	 * @return
	 */
	@RequestMapping(value = "/saveUserEntity", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("user:create")
	public AjaxJsonResult saveUserEntity(UserEntity userEntity) {
		try {
			Principal principal = SecurityUtilSimple.getPrincipal();
			if (userEntity.getUserId() == null || userEntity.getUserId().equals("")) {
				List<ConditionFilter> list = new ArrayList();
				ConditionFilter statusFilter = new ConditionFilter("USER", userEntity.getEmailaddress(), "账号");
				list.add(statusFilter);
				if (getUserService().countWithDynamicQuery(list) > 0) {
					return AjaxJsonResult.buildResultFail(MessageConstant.USER_EXIS);
				} else {
					userEntity.setUserId(CounterEntityServiceUtil.getService().getPersistence().increment(UserEntity.class.getName()) + "");
					userEntity.setCreateBy(principal.getId());
					userEntity.setCreateDate(new Date());
					userEntity.setPassword(MD5.toMD5(userEntity.getPassword()));
					getUserService().addSaveUserEntity(userEntity, null, null, null);
				}
			} else {
				userEntity.setUpdateBy(principal.getId());
				userEntity.setUpdateDate(new Date());
				userEntity.setPassword(MD5.toMD5(userEntity.getPassword()));
				getUserService().updateSaveUserEntity(userEntity, null, null, null);
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("saveUserEntity" + sw.toString());
			return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
		}

		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	/**
	 * 删除用户，不建议使用
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/deleteUserEntity", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("user:delete")
	public AjaxJsonResult deleteUserEntity(UserEntity entity) {

		try {
			getUserService().deleteUserEntity(entity);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("deleteUserEntity" + sw.toString());
			return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
		}
		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	/**
	 * 设置新密码
	 * 
	 * @param oldpwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "/updateUsersPwbEntity", method = RequestMethod.POST)
	@ResponseBody
	@RequiresAuthentication
	public AjaxJsonResult updateUsersPwbEntity(String oldpwd, String newPwd) {
		Principal principal = SecurityUtilSimple.getPrincipal();
		try {
			UserEntity u = new UserEntity();
			u.setUserId(principal.getId());
			u = getUserService().findEntityByPrimaryKey(u);
			if (u != null && u.getPassword().equals(MD5.toMD5(oldpwd))) {
				u.setPassword(MD5.toMD5(newPwd));
				u.setUpdateBy(principal.getId());
				u.setUpdateDate(new Date());
				getUserService().updateSaveUserEntity(u);
			} else {
				return AjaxJsonResult.buildResultFail(MessageConstant.USER_PWDERROE);
			}

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("updateUsersEntityList" + sw.toString());
			return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
		}

		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	/**
	 * 更新用户状态
	 * 
	 * @param userId
	 * @param ative
	 * @return
	 */
	@RequestMapping(value = "/updateSetUsersActive", method = RequestMethod.POST)
	@ResponseBody
	@RequiresAuthentication
	public AjaxJsonResult updateSetUsersActive(String userId, int ative) {
		Principal principal = SecurityUtilSimple.getPrincipal();
		try {
			UserEntity u = new UserEntity();
			u.setUserId(principal.getId());
			u = getUserService().findEntityByPrimaryKey(u);
			if (u != null) {
				u.setStatus(ative);
				u.setUpdateDate(new Date());
				getUserService().updateSaveUserEntity(u);
			} else {
				return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
			}

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("updateSetUsersActive" + sw.toString());
			return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
		}

		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

}
