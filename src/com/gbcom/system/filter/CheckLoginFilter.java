package com.gbcom.system.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.gbcom.system.utils.ServerContext;

/**
 * 用于检测用户是否登陆的过滤器，如果未登录，则重定向到指的登录页面
 * 过滤器只需要继承Filter，并在web.xml配置一个filter和filtermapping
 * （指定的url）。servlet容器会自动初始化，自动doFilter，自动destroy
 * 
 * 配置参数 过滤器可作为拦截器 Filter在Servlet中有效 checkSessionKey 需检查的在 Session 中保存的关键字
 * redirectURL 如果用户未登录，则重定向到指定的页面，URL不包括 ContextPath notCheckURLList
 * 不做检查的URL列表，以分号分开，并且 URL 中不包括 ContextPath
 */
public class CheckLoginFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(CheckLoginFilter.class);
	protected FilterConfig filterConfig = null;
	private String redirectURL = null;
	private String urlRegx = "checkDogVerify"; //加密狗
	private List<String> notCheckURLList = new ArrayList<String>();
	private String sessionKey = null;

	/**
	 * 过滤链
	 * 
	 * @param servletRequest
	 *            ServletRequest
	 * @param servletResponse
	 *            ServletResponse
	 * @param filterChain
	 *            FilterChain
	 * @throws IOException
	 *             IOException
	 * @throws ServletException
	 *             ServletException
	 */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// 每次请求会将request对象 保存到threadlocal中。
		if (LOG.isDebugEnabled()) {
			LOG.info("/---------  HTTP-Thread --------- ");
			LOG.info("thread id = " + Thread.currentThread());
			LOG.info("request  hashcode=" + request.hashCode()
					+ " ; request URL=" + request.getRequestURI());
			LOG.info("SESSION  session hashcode="
					+ request.getSession().hashCode() + " ; session ID="
					+ request.getSession().getId());
			LOG.info("/---------  HTTP-Thread --------- ");
		}
		// spring is not need ,,user process need; login.jsp?error=expired
		ServerContext.setRequest(request);
		ServerContext.setResponse(response);
//		HttpSession session = request.getSession();
	
/*		 boolean flag =false;
        if(session==null ||null == session.getAttribute("domain")){
            for(String str : notCheckURLList){
                if(request.getRequestURI().indexOf(str)!=-1){
                    flag = true;
                    break;
                }
            }
            if(!flag){
            	LOG.info("session （domain discard） time out!!!!URL =" +request.getRequestURI()+";;redirectURL="+redirectURL);
            	response.sendRedirect(request.getContextPath() + redirectURL);
            	return;
            }
        }*/
		/*
		if (request.getRequestURI().contains(urlRegx)) {
			// 加密狗线程包括对session的判断
			HttpSession session = request.getSession();
			// 用户超时或没有登陆时跳转到登陆页面
			if (session==null || session.getAttribute("domain") == null) {
				LOG.info("session time out!!!!");
				response.sendRedirect(request.getContextPath() + redirectURL);
			} else {
				request.getSession().setAttribute("domain",
						request.getSession().getAttribute("domain"));
				//request.getSession().setMaxInactiveInterval(30 * 60);// 以秒为单位
			}
		} */

		filterChain.doFilter(servletRequest, servletResponse);
	}

	/**
	 * 注销
	 */
	public void destroy() {
		notCheckURLList.clear();
	}

	/**
	 * 初始化：过滤连初始化，被GWT filterChainproxy调用 在doFilter之前调用，web.xml配置文件中的参数会被加载
	 * 
	 * web.xml的<init-param>会自动封装到FilterConfig
	 * 
	 * @param filterConfig
	 *            FilterConfig
	 * @throws ServletException
	 *             ServletException
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		redirectURL = filterConfig.getInitParameter("redirectURL");
		sessionKey = filterConfig.getInitParameter("checkSessionKey");

		String notCheckURLListStr = filterConfig
				.getInitParameter("notCheckURLList");

		if (notCheckURLListStr != null) {
			StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
			notCheckURLList.clear();
			while (st.hasMoreTokens()) {
				notCheckURLList.add(st.nextToken());
			}
		}
	}
}