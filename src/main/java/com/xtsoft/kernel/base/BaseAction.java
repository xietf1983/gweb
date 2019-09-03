BaseActionpackage com.xtsoft.kernel.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
import com.xtsoft.kernel.security.shiro.realm.CustomRealm.Principal;
import com.xtsoft.kernel.security.shiro.web.filter.authc.ShiroToken;

@Controller("baseaction")
public class BaseAction {
	private static final String ENCODING_PREFIX = "encoding";
	private static final String NOCACHE_PREFIX = "no-cache";
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	public static Principal getCurentPrincipal() {
		if (SecurityUtils.getSubject() != null) {
			return (Principal) SecurityUtils.getSubject().getPrincipal();
		} else {
			return null;
		}
	}

	public static void render(final HttpServletResponse response, final String contentType, final String content, final String... headers) {
		try {

			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else
					throw new IllegalArgumentException(headerName + "");
			}

			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);
			response.getWriter().flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ֱ
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final HttpServletResponse response, final String text, final String... headers) {
		render(response, "text/plain", text, headers);
	}

	/**
	 *
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final HttpServletResponse response, final String html, final String... headers) {
		render(response, "text/html", html, headers);
	}

	/**
	 *
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final HttpServletResponse response, final String xml, final String... headers) {
		render(response, "text/xml", xml, headers);
	}

	public static void renderPartJson(final HttpServletResponse response, final Object object, final String[] property, final String... headers) {
		String jsonString = JSON.toJSONString(object);
		render(response, "application/json", jsonString, headers);
	}

	public static void renderJson(final HttpServletResponse response, final Object object, final String... headers) {
		String jsonString = JSON.toJSONString(object);
		render(response, "application/json", jsonString, headers);
	}

	public static void renderDeepJson(final HttpServletResponse response, final Object object, final String... headers) {
		String jsonString = JSON.toJSONString(object);
		render(response, "application/json", jsonString, headers);
	}

	@ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
	public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
		if (isAjaxRequest(request)) {
			// 输出JSON
			Map<String, Object> map = new HashMap<>();
			map.put("code", "-999");
			map.put("message", "未登录");
			renderDeepJson(response,map);
			return null;
		} else {
			return "redirect:/system/login";
		}
	}

	@ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
	public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
		if (isAjaxRequest(request)) {
			// 输出JSON
			Map<String, Object> map = new HashMap<>();
			map.put("code", "-998");
			map.put("message", "无权限");
			renderDeepJson(response,map);
			return null;
		} else {
			return "redirect:/system/403";
		}
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("x-requested-with");
		if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
}
