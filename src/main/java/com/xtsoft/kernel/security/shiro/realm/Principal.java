package com.xtsoft.kernel.security.shiro.realm;

import org.apache.shiro.SecurityUtils;

import com.xtsoft.kernel.security.shiro.web.filter.authc.ShiroToken;

/**
 * 授权用户信息
 */
public class Principal {

	private static final long serialVersionUID = 1L;

	private String id; // 编号
	private String username; // 登录名
	private String realname; // 姓名
	private boolean usbkey; // 是否证书登入

	public Principal(ShiroToken user, boolean usbkey) {
		this.id = user.getUserId();
		this.username = user.getUsername();
		this.realname = user.getRealname();
		this.usbkey = usbkey;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getRealname() {
		return realname;
	}

	public boolean getUsbkey() {
		return usbkey;
	}

	/**
	 * 获取SESSIONID
	 */
	public String getSessionid() {
		try {
			return (String) SecurityUtils.getSubject().getSession().getId();
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public String toString() {
		return id;
	}

}
