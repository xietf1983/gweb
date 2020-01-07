package com.xtsoft.kernel.sys.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtsoft.kernel.base.BaseAction;
import com.xtsoft.kernel.sys.entity.SystemDataEntity;
import com.xtsoft.kernel.sys.service.impl.SystemDataEntityServiceImpl;

@Controller("systemDataAction")
@RequestMapping(value = "/systemData")
public class SystemDataAction extends BaseAction {
	private SystemDataEntityServiceImpl _service;

	public SystemDataEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setSystemDataEntityServiceImpl(SystemDataEntityServiceImpl service) {
		_service = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取获取字典表
	 * 
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getSystemDataEntityListWithSelect", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	@RequiresAuthentication
	public List getSystemDataEntityListeWithSelect(String dataCode, int showAll) {
		List<SystemDataEntity> results = null;
		List<Map> data = new ArrayList();
		try {
			results = getService().getSystemDataEntityList(dataCode);
			if (results == null && results.size() == 0) {
				results = new ArrayList();
			}
			if (showAll == 1) {
				Map dataOption = new HashMap();
				dataOption.put("text", "---请选择---");
				dataOption.put("id", "");
				data.add(dataOption);
			}
			for (SystemDataEntity entity : results) {
				Map dataOption = new HashMap();
				dataOption.put("text", entity.getName());
				dataOption.put("id", entity.getValue());
				if (entity.getIconCls() != null && !entity.getIconCls().equals("")) {
					dataOption.put("iconCls", entity.getIconCls());
				}
				data.add(dataOption);
			}

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("getSystemDataEntityListeWithSelect" + sw.toString());
			return new ArrayList();
		}
		return data;
	}

}
