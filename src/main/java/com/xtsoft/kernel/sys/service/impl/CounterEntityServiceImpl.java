package com.xtsoft.kernel.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtsoft.kernel.sys.service.persistence.CounterEntityPersistence;


@Service(value = "counterEntityService")
public class CounterEntityServiceImpl {
	private CounterEntityPersistence persistence;

	public CounterEntityPersistence getPersistence() {
		return persistence;
	}

	@Autowired
	public void setCounterEntityPersistence(CounterEntityPersistence persistence) {
		this.persistence = persistence;
	}

}
