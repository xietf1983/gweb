package com.xtsoft.kernel.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtsoft.kernel.sys.service.impl.UserEntityServiceImpl;

@Service(value = "userEntityServiceUtil")
public class UserEntityServiceUtil {
	private static  UserEntityServiceImpl _service;

	public static UserEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setService(UserEntityServiceImpl service) {
		_service = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
}
