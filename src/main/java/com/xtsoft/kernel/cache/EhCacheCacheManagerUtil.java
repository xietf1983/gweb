package com.xtsoft.kernel.cache;

import java.util.List;

import org.springframework.cache.CacheManager;

import com.xtsoft.kernel.sys.entity.MenuEntity;

public class EhCacheCacheManagerUtil {
	private static CacheManager cacheManager;

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		EhCacheCacheManagerUtil.cacheManager = cacheManager;
	}

	public static List<MenuEntity> loadUserMenuEntityList(String userId) {
		return (List<MenuEntity>) EhCacheCacheManagerUtil.getCacheManager().getCache(CacheName.USERMENEPERSION).get(userId);
	}

	public static void putGatherGroupInfoCache(String key, List<MenuEntity> list) {
		EhCacheCacheManagerUtil.getCacheManager().getCache(CacheName.USERMENEPERSION).evict(key);
		EhCacheCacheManagerUtil.getCacheManager().getCache(CacheName.USERMENEPERSION).put(key, list);
	}

}
