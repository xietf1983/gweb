package com.xtsoft.kernel.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtsoft.kernel.sys.service.impl.CounterEntityServiceImpl;

@Service(value = "counterEntityServiceUtil")
public class CounterEntityServiceUtil {
	private static CounterEntityServiceImpl _service;

	public static CounterEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setService(CounterEntityServiceImpl service) {
		_service = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
}
