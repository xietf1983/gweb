package com.xtsoft.kernel.util;

import java.util.List;

import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.builder.combined.CombinedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropsUtil {

	public static boolean contains(String key) {
		return _instance._contains(key);
	}

	public static String get(String key) {
		return _instance._get(key);
	}

	public static String[] getArray(String key) {
		return _instance._getArray(key);
	}

	public static List getList(String key) {
		return _instance._getList(key);
	}

	public static void set(String key, String value) {
		_instance._set(key, value);
	}

	private PropsUtil() {
		try {

			Parameters params = new Parameters();
			CombinedConfigurationBuilder builder = new CombinedConfigurationBuilder().configure(params.fileBased().setURL(getClass().getClassLoader().getResource("app-config.xml")));
			_configuration = builder.getConfiguration();
		} catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to initialize PropsUtil", e);
			}
		}
	}

	private boolean _contains(String key) {
		return _getConfiguration().containsKey(key);
	}

	private String _get(String key) {
		return _getConfiguration().getString(key);
	}

	private String[] _getArray(String key) {
		return _getConfiguration().getStringArray(key);
	}

	private List _getList(String key) {
		return _getConfiguration().getList(key);
	}

	private CombinedConfiguration _getConfiguration() {
		return _configuration;
	}

	private void _set(String key, String value) {
		_getConfiguration().setProperty(key, value);
	}

	private static Log _log = LogFactory.getLog(PropsUtil.class);

	private static PropsUtil _instance = new PropsUtil();

	private CombinedConfiguration _configuration;

	public static void main(String args[]) {
		System.out.print(PropsUtil.get("brokerList"));
	}

}