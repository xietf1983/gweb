package com.xtsoft.police.action;

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
import com.xtsoft.kernel.constant.DataBaseConstant;
import com.xtsoft.kernel.constant.MessageConstant;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.response.AjaxJsonResult;
import com.xtsoft.police.entity.DirectionEntity;
import com.xtsoft.police.entity.PlaceEntity;
import com.xtsoft.police.service.impl.PlaceEntityServiceImpl;

@Controller("placeAction")
@RequestMapping(value = "/place")
public class PlaceAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private PlaceEntityServiceImpl _service;

	public PlaceEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setPlaceEntityServiceImpl(PlaceEntityServiceImpl service) {
		_service = service;
	}

	/**
	 * 获取场所的基本信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getPlaceEntity", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	@RequiresAuthentication
	public AjaxJsonResult getPlaceEntity(String id) {
		long startTime = System.currentTimeMillis();
		PlaceEntity data = null;
		try {
			PlaceEntity e = new PlaceEntity();
			e.setId(id);
			data = (PlaceEntity) getService().getPersistence().findByPrimaryKey(e);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("getPlaceActionEntity" + sw.toString());
			long endTime = System.currentTimeMillis();
			return AjaxJsonResult.bulidAjaxPageJsonFail((MessageConstant.UNKNOWN_ERROR), (endTime - startTime));
		}
		long endTime = System.currentTimeMillis();
		return AjaxJsonResult.buildResultSuccess("", data, (endTime - startTime));
	}

	/**
	 * 对应的方向信息与车道信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getDirectionEntityList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	@RequiresAuthentication
	public AjaxJsonResult getDirectionEntityList(String id) {
		long startTime = System.currentTimeMillis();
		List<DirectionEntity> results = null;
		long total = 0;
		List<ConditionFilter> list = new ArrayList();
		try {
			results = getService().getDirectionEntityList(id);
			if (results != null && results.size() > 0) {
				total = results.size();
			}

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("getDirectionEntityList" + sw.toString());
			long endTime = System.currentTimeMillis();
			return AjaxJsonResult.bulidAjaxPageJsonFail((MessageConstant.UNKNOWN_ERROR), (endTime - startTime));
		}

		long endTime = System.currentTimeMillis();
		return AjaxJsonResult.bulidAjaxPageJsonSucess(results, total, (endTime - startTime));
	}

	@RequestMapping(value = "/getPlaceEntityList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	@RequiresAuthentication
	public AjaxJsonResult getPlaceEntityList(int page, int rows, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		List<PlaceEntity> results = null;
		long total = 0;
		List<ConditionFilter> list = new ArrayList();
		try {
			String functiontype = request.getParameter("FUNCTIONTYPE");
			String type = request.getParameter("TYPE");
			String name = request.getParameter("NAME");
			String organizationId = request.getParameter("ORGANIZATIONID");
			if (organizationId != null && !organizationId.equals("") && !organizationId.equals(DataBaseConstant.ROOTID)) {
				ConditionFilter orgFilter = new ConditionFilter("ORGANIZATIONID", organizationId, "部门ID");
				list.add(orgFilter);
			}
			if (name != null && !name.equals("")) {
				ConditionFilter contentFilter = new ConditionFilter("NAME", name, "名称");
				list.add(contentFilter);
			}
			if (type != null && !type.equals("")) {
				ConditionFilter typeFilter = new ConditionFilter("TYPE", type, "场所类型");
				list.add(typeFilter);
			}
			if (functiontype != null && !functiontype.equals("")) {
				ConditionFilter typeFilter = new ConditionFilter("FUNCTIONTYPE", functiontype, "抓拍类型");
				list.add(typeFilter);
			}
			results = getService().findWithDynamicQuery(list, (page - 1) * rows, rows);
			total = getService().countWithDynamicQuery(list);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("getPlaceEntityList" + sw.toString());
			long endTime = System.currentTimeMillis();
			return AjaxJsonResult.bulidAjaxPageJsonFail((MessageConstant.UNKNOWN_ERROR), (endTime - startTime));
		}

		long endTime = System.currentTimeMillis();
		return AjaxJsonResult.bulidAjaxPageJsonSucess(results, total, (endTime - startTime));
	}

}
