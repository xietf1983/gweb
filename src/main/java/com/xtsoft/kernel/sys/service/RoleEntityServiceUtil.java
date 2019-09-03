package com.xtsoft.kernel.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtsoft.kernel.sys.service.impl.RoleEntityServiceImpl;


@Service(value = "roleEntityServiceUtil")
public class RoleEntityServiceUtil {
	private static RoleEntityServiceImpl _service;

	public  static RoleEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setService(RoleEntityServiceImpl service) {
		_service = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
}
