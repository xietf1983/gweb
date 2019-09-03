package com.xtsoft.kernel.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtsoft.kernel.sys.service.impl.SystemDataEntityServiceImpl;

@Service(value = "systemDataEntityServiceUtil")
public class SystemDataEntityServiceUtil {
	private static SystemDataEntityServiceImpl _service;

	public  static SystemDataEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setService(SystemDataEntityServiceImpl service) {
		_service = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
}
