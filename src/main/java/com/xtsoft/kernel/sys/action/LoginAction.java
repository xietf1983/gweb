package com.xtsoft.kernel.sys.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtsoft.kernel.annotation.SystemLogAnnotation;
import com.xtsoft.kernel.base.BaseAction;
import com.xtsoft.kernel.constant.DataBaseConstant;
import com.xtsoft.kernel.constant.MessageConstant;
import com.xtsoft.kernel.constant.WebConstant;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.response.AjaxJsonResult;
import com.xtsoft.kernel.security.shiro.SecurityUtilSimple;
import com.xtsoft.kernel.security.shiro.realm.Principal;
import com.xtsoft.kernel.security.shiro.web.filter.authc.ShiroToken;
import com.xtsoft.kernel.sys.entity.MenuEntity;
import com.xtsoft.kernel.sys.entity.UserEntity;
import com.xtsoft.kernel.sys.service.MenuEntityServiceUtil;
import com.xtsoft.kernel.sys.service.impl.UserEntityServiceImpl;
import com.xtsoft.kernel.util.MD5;

@Controller("loginAction")
public class LoginAction extends BaseAction {
	private UserEntityServiceImpl _service;

	public UserEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setUserEntityServiceImpl(UserEntityServiceImpl service) {
		_service = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/checkUserAccount", method = RequestMethod.POST)
	@ResponseBody
	@SystemLogAnnotation(module = "loginAction", methods = "checkUserAccount", description = "用户登入", encrypted = "1")
	public AjaxJsonResult checkUserAccount(UserEntity userShort) {
		try {
			ConditionFilter filter = new ConditionFilter("USER", userShort.getEmailaddress(), "账号");
			List<ConditionFilter> list = new ArrayList();
			list.add(filter);
			List<UserEntity> userlist = getService().findWithDynamicQuery(list, 0, 1);

			if (userlist == null || userlist.size() == 0) {
				return AjaxJsonResult.buildResultFail(MessageConstant.USER_NOTFIND);
				// return;
			}
			UserEntity userEntity = userlist.get(0);
			if (!MD5.toMD5(userShort.getPassword()).equals(userEntity.getPassword())) {
				return AjaxJsonResult.buildResultFail(MessageConstant.USER_PWDERROE);

			}
			ShiroToken token = new ShiroToken(userEntity.getEmailaddress(), userEntity.getPassword(), userEntity.getUserId(), userEntity.getUserName());
			org.apache.shiro.SecurityUtils.getSubject().login(token);
			// System.out.println(1);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			if (logger.isErrorEnabled()) {
				logger.error("checkUserAccount" + sw.toString());
			}
			return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
		}
		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	@SystemLogAnnotation(module = "loginAction", methods = "logout", description = "用户登出", encrypted = "1")
	public AjaxJsonResult logout() {
		if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
			try {
				org.apache.shiro.SecurityUtils.getSubject().logout();
				return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				if (logger.isErrorEnabled()) {
					logger.error("checkUserAccount" + sw.toString());
				}
				return AjaxJsonResult.buildResultFail(MessageConstant.UNKNOWN_ERROR);
			}
		}
		return AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJsonResult index() {
		AjaxJsonResult result = null;
		try {
			if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
				Principal principal = SecurityUtilSimple.getPrincipal();
				String id = principal.getId();
				String userName = principal.getUsername();
				// 获取我的菜单(一级菜单)
				result = AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
				if (principal.getRealname() != null && !principal.getRealname().equals("")) {
					userName = principal.getRealname();
				}
				result.getExtend().put(WebConstant.WELCOME, userName);

				List<MenuEntity> menuList = MenuEntityServiceUtil.getService().findMenuEntityByUserIdAndParentIdTree(id, DataBaseConstant.ROOTID);
				// result =
				// AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);
				result.getExtend().put(WebConstant.MENU, menuList);

				// 获取默认首页
				MenuEntity menu = MenuEntityServiceUtil.getService().findHomeMenuEntityByUserId(id);
				if (menu == null) {
					menu = new MenuEntity();
					menu.setId("default");
					menu.setUrl(WebConstant.WELCOME_PAGE_URL);
					menu.setName(WebConstant.WELCOME_PAGE_NAME);
					menu.setIsshow((short) 1);
					menu.setMenuIcon(WebConstant.WELCOME_PAGE_ICON);
				}
				result.getExtend().put(WebConstant.HOMEPAGE, menu);

			} else {
				AjaxJsonResult.buildResultFail(MessageConstant.AUTHEN_ERROR);
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			if (logger.isErrorEnabled()) {
				logger.error("checkUserAccount" + sw.toString());
			}
			AjaxJsonResult.buildResultFail(MessageConstant.AUTHEN_ERROR);
		}
		return result;
	}

	@RequestMapping(value = "/accordingIndex", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJsonResult accordingIndex(String parentId) {
		AjaxJsonResult result = null;
		if (org.apache.shiro.SecurityUtils.getSubject().isAuthenticated()) {
			Principal principal = SecurityUtilSimple.getPrincipal();
			String id = principal.getId();
			List<MenuEntity> menuList = MenuEntityServiceUtil.getService().findMenuEntityByUserIdAndParentIdTree(id, parentId);
			result = AjaxJsonResult.buildResultSuccess(MessageConstant.SUECESS);

			result.getExtend().put(WebConstant.MENU, menuList);

		} else {
			AjaxJsonResult.buildResultFail(MessageConstant.AUTHEN_ERROR);
		}
		return result;

	}

}
