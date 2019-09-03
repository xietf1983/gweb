package com.xtsoft.kernel.sys.service.persistence;

import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.xtsoft.kernel.base.BasePersistence;
import com.xtsoft.kernel.sys.entity.CounterEntity;
import com.xtsoft.kernel.sys.entity.CounterRegister;


@Repository("counterEntityPersistence")
public class CounterEntityPersistence extends BasePersistence<CounterEntity> {

	public long increment() {
		return increment(_NAME);
	}

	public long increment(String name) {
		return increment(name, _MINIMUM_INCREMENT_SIZE);
	}

	public long increment(String name, int size) {
		if (size < _MINIMUM_INCREMENT_SIZE) {
			size = _MINIMUM_INCREMENT_SIZE;
		}
		CounterRegister register = getCounterRegister(name);
		synchronized (register) {
			long newValue = register.getCurrentValue() + size;
			if (newValue > register.getRangeMax()) {

				CounterEntity model = new CounterEntity();
				model.setName(name);
				model.setCurrentId(newValue + register.getRangeSize());
				updatetEntity(model);
				long rangeMax = newValue + register.getRangeSize();
				register.setRangeMax(rangeMax);
				register.setCurrentValue(newValue);

			} else {
				register.setCurrentValue(newValue);
			}
			return newValue;
		}
	}

	protected CounterRegister getCounterRegister(String name) {
		CounterRegister register = _registerLookup.get(name);
		if (register == null) {
			register = createCounterRegister(name);
			_registerLookup.put(name, register);
		}
		return register;
	}

	protected CounterRegister createCounterRegister(String name) {

		return createCounterRegister(name, _COUNTER_INCREMENT);
	}

	protected CounterRegister createCounterRegister(String name, long size) {
		long rangeMin = 0;
		long rangeMax = 0;
		CounterEntity c = new CounterEntity();
		c.setName(name);
		CounterEntity model = (CounterEntity) findByPrimaryKey(c);
		if (model == null) {
			rangeMin = _DEFAULT_CURRENT_ID;
			model = new CounterEntity();
			model.setName(name);
			rangeMax = rangeMin + size;
			model.setCurrentId(rangeMax);
			insertEntity(model);
		} else {
			model.setName(name);
			rangeMin = model.getCurrentId();
			rangeMax = rangeMin + size;
			model.setCurrentId(rangeMax);
			updatetEntity(model);
		}

		CounterRegister register = new CounterRegister(name, rangeMin, rangeMax, _COUNTER_INCREMENT);
		return register;
	}

	private static final int _DEFAULT_CURRENT_ID = 10000;
	private static final int _MINIMUM_INCREMENT_SIZE = 1;
	private static final int _COUNTER_INCREMENT = 500;
	private Map<String, CounterRegister> _registerLookup = new ConcurrentHashMap<String, CounterRegister>();
	private static final String _NAME = CounterEntity.class.getName();
	private static Log _log = LogFactory.getLog(CounterEntityPersistence.class);

}
