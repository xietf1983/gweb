package com.xtsoft.police.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtsoft.police.service.impl.PlaceEntityServiceImpl;

@Service(value = "placeEntityServiceUtil")
public class PlaceEntityServiceUtil {
	private static  PlaceEntityServiceImpl _service;

	public static PlaceEntityServiceImpl getService() {
		return _service;
	}

	@Autowired
	public void setService(PlaceEntityServiceImpl service) {
		_service = service;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
}
