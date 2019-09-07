package com.xtsoft.kernel.security.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.xtsoft.kernel.cache.CacheName;
import com.xtsoft.kernel.cache.EhCacheCacheManagerUtil;
import com.xtsoft.kernel.security.shiro.web.filter.authc.ShiroToken;
import com.xtsoft.kernel.sys.entity.MenuEntity;
import com.xtsoft.kernel.sys.service.MenuEntityServiceUtil;

public class CustomRealm extends AuthorizingRealm {
	/**
	 * 授权 获取权限
	 * 
	 * 
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Principal loginName = (Principal) SecurityUtils.getSubject().getPrincipal();
		if (loginName != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			String userId = loginName.getId();
			List<String> permissions = new ArrayList<String>();
			/**
			 * 获取菜单权限
			 * 
			 */
			List<MenuEntity> permissionList =(List<MenuEntity>) EhCacheCacheManagerUtil.loadCacheByKey(userId, CacheName.USERMENEPERSION, ArrayList.class);
			if(permissionList==null){
				permissionList = MenuEntityServiceUtil.getService().findMenuEntityButtonByUserId(userId);
				
			}
			if (permissionList != null && permissionList.size() > 0) {
				for (MenuEntity sysPermission : permissionList) {
					// 将数据库中的权限标签 符放入集合
					permissions.add(sysPermission.getCode());
				}
			}
			info.addStringPermissions(permissions);
			return info;
		}
		return null;
	}

	/**
	 * 登入验证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		ShiroToken token = (ShiroToken) authcToken;
		return new SimpleAuthenticationInfo(new Principal(token, false), token.getPswd(), getName());

	}

	
}
