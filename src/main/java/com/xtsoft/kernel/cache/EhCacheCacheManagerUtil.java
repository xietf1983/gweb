package com.xtsoft.kernel.cache;

import org.springframework.cache.CacheManager;

public class EhCacheCacheManagerUtil {
	private static CacheManager cacheManager;

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		EhCacheCacheManagerUtil.cacheManager = cacheManager;
	}

	public static void removeByKey(String key, String cacheName) {
		StringBuffer keyBuffer = new StringBuffer();
		keyBuffer.append(cacheName);
		keyBuffer.append("-");
		keyBuffer.append(key);
		getCacheManager().getCache(cacheName).evict(keyBuffer.toString());
	}

	public static void clearcacheName(String cacheName) {
		getCacheManager().getCache(cacheName).clear();
	}

	public static void putObject(String key, String cacheName, Object obj) {
		StringBuffer keyBuffer = new StringBuffer();
		keyBuffer.append(cacheName);
		keyBuffer.append("-");
		keyBuffer.append(key);
		getCacheManager().getCache(cacheName).put(key, obj);
	}

	public static Object loadCacheByKey(String key, String cacheName, Class obj) {
		StringBuffer keyBuffer = new StringBuffer();
		keyBuffer.append(cacheName);
		keyBuffer.append("-");
		keyBuffer.append(key);
		return getCacheManager().getCache(cacheName).get(key, obj);
	}

	
}
