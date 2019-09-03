package com.xtsoft.kernel.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtsoft.kernel.sys.service.impl.LogEventEntityServiceImpl;
@Service(value = "logEventEntityServiceUtil")
public class LogEventEntityServiceUtil {
	private static LogEventEntityServiceImpl _service;

	public static LogEventEntityServiceImpl getService() {
		return _service;
	}
	@Autowired
	public void setService(LogEventEntityServiceImpl service) {
		_service = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
}
