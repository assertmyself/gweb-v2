package com.gbcom.system.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 服务上下文
 * 
 * @author Suyuejia
 * 
 */
public class ServerContext {
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

	/**
	 * 
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) requestLocal.get();
	}

	/**
	 * 
	 * @param request
	 *            request
	 */
	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	/**
	 * 
	 * @return HttpServletResponse
	 */
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) responseLocal.get();
	}

	/**
	 * 
	 * @param response
	 *            response
	 */
	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}

	/**
	 * 
	 * @return HttpSession
	 */
	public static HttpSession getSession() {
		// 拦截器异常
		HttpServletRequest request = (HttpServletRequest) requestLocal.get();
		if (request != null) {
			HttpSession session = null;
			try {
				session = (HttpSession) request.getSession();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return session;
		} else {
			return null;
		}
	}
}