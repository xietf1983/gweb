package com.xtsoft.kernel.sys.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtsoft.kernel.base.BaseAction;
import com.xtsoft.kernel.constant.MessageConstant;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.response.AjaxJsonResult;
import com.xtsoft.kernel.security.shiro.SecurityUtilSimple;
import com.xtsoft.kernel.security.shiro.realm.Principal;
import com.xtsoft.kernel.sys.entity.LogEventEntity;
import com.xtsoft.kernel.sys.entity.UserEntity;
import com.xtsoft.kernel.sys.service.UserEntityServiceUtil;
import com.xtsoft.kernel.sys.service.impl.LogEventEntityServiceImpl;

@Controller("logAction")
@RequestMapping(value = "/log")
public class LogAction extends BaseAction {
	private LogEventEntityServiceImpl _service;

	public LogEventEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setLogEventEntityServiceImpl(LogEventEntityServiceImpl service) {
		_service = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/getLogEventEntity", method = RequestMethod.POST)
	@ResponseBody
	@RequiresAuthentication
	public AjaxJsonResult getLogEventEntity(String id) {
		long startTime = System.currentTimeMillis();
		LogEventEntity data = null;
		try {
			LogEventEntity e = new LogEventEntity();
			e.setId(id);
			data = (LogEventEntity) getService().getPersistence().findByPrimaryKey(e);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("getLogEventEntity" + sw.toString());
			long endTime = System.currentTimeMillis();
			return AjaxJsonResult.bulidAjaxPageJsonFail((MessageConstant.UNKNOWN_ERROR), (endTime - startTime));
		}
		long endTime = System.currentTimeMillis();
		return AjaxJsonResult.buildResultSuccess("", data, (endTime - startTime));
	}

	@RequestMapping(value = "/getLogEventEntityList", method = RequestMethod.POST)
	@ResponseBody
	@RequiresAuthentication
	public AjaxJsonResult getLogEventEntityList(int page, int rows, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		List<LogEventEntity> results = null;
		long total = 0;
		List<ConditionFilter> list = new ArrayList();
		try {
			Principal principal = SecurityUtilSimple.getPrincipal();
			String startTimeStr = request.getParameter("STARTTIME");
			String endTimeStr = request.getParameter("ENDTIME");
			String status = request.getParameter("STATUS");
			String user = request.getParameter("USER");
			String content = request.getParameter("CONTENT");
			UserEntity u = new UserEntity();
			u.setUserId(principal.getId());
			u = UserEntityServiceUtil.getService().findEntityByPrimaryKey(u);
			if (u.getDefaultUser() == 1) {
				// 管理员账户
			} else {
				// 只能查询自己的日志
				ConditionFilter orgFilter = new ConditionFilter("USERID", principal.getId(), "用户ID");
				list.add(orgFilter);
			}
			if (status != null && !status.equals("")) {

				ConditionFilter statusFilter = new ConditionFilter("SUCESS", Integer.parseInt(status), "成功标识");
				list.add(statusFilter);
			}
			if (user != null && !user.equals("")) {
				ConditionFilter userFilter = new ConditionFilter("USER", user, "用户标识");
				list.add(userFilter);
			}
			if (content != null && !content.equals("")) {
				List<String> contentlist = new ArrayList();
				for (String s : content.split(",")) {
					contentlist.add(s);
				}
				ConditionFilter contentFilter = new ConditionFilter("CONETENT", contentlist, "对象标识");
				list.add(contentFilter);
			}
			ConditionFilter timeStartFilter = new ConditionFilter("STARTTIME", startTimeStr + " 00:00:00", "开始时间");
			list.add(timeStartFilter);
			ConditionFilter timeEndFilter = new ConditionFilter("ENDTIME", endTimeStr + " 23:59:59", "结束时间");
			list.add(timeEndFilter);
			results = getService().findWithDynamicQuery(list, (page - 1) * rows, rows);
			total = getService().countWithDynamicQuery(list);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("getLogEventEntityList" + sw.toString());
			long endTime = System.currentTimeMillis();
			return AjaxJsonResult.bulidAjaxPageJsonFail((MessageConstant.UNKNOWN_ERROR), (endTime - startTime));
		}

		long endTime = System.currentTimeMillis();
		return AjaxJsonResult.bulidAjaxPageJsonSucess(results, total, (endTime - startTime));
	}

}
