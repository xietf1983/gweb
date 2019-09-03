package com.xtsoft.kernel.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtsoft.kernel.sys.service.impl.MenuEntityServiceImpl;

@Service(value = "menuEntityServiceUtil")
public class MenuEntityServiceUtil {
	private static MenuEntityServiceImpl _service;

	public static MenuEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setService(MenuEntityServiceImpl service) {
		_service = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
}
