package com.gbcom.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.AbstractProcessingFilter;

/**
 * 自定义认证过滤器，使用spring-security机制
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午02:11:43
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.filter.CustomAuthenticationProcessingFilter
 */
public class CustomAuthenticationProcessingFilter implements Filter {
	/**
	 * VALIDATION_CODE
	 */
    public static final String VALIDATION_CODE = "VALIDATION_CODE";
    /**
     * J_VALIDATION_CODE
     */
    public static final String J_VALIDATION_CODE = "j_validation_code";

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if ((request != null) && request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();
            String code = StringUtils.defaultIfEmpty(req
                    .getParameter("j_validation_code"), "");

            if (session.getAttribute(VALIDATION_CODE) != null) {
                String sessionCode = StringUtils.defaultIfEmpty(
                        (String) session
                                .getAttribute(VALIDATION_CODE), "");

                if (!code.equals("") && !sessionCode.equals("")
                        && !code.equalsIgnoreCase(sessionCode)) {
                    session
                            .setAttribute(
                                    AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY,
                                    new BadCredentialsException("验证码错误!"));
                    RequestDispatcher rd = request
                            .getRequestDispatcher("/login.jsp?login_error=1");
                    req.setAttribute("SPRING_SECURITY_LAST_USERNAME", req
                            .getParameter("j_username"));
                    rd.forward(request, response);
                    return;
                }

                // session.removeAttribute(Constants.VALIDATION_CODE);
            }
            //已使用spring security自带方式判断session是否过期

            //非法字符过滤
            HttpServletResponse res = (HttpServletResponse) response;
            String queryString = req.getQueryString();
            if (StringUtils.isNotBlank(queryString)) {
                if (queryString.indexOf("<script>") > -1 || queryString.indexOf("%3Cscript%3E") > -1) {
//                throw new IOException("操作失败，请不要在链接中输入非法参数！");
                    res.sendError(404, "File not found!");
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }


    /**
     * 初始化配置
     * @param arg0 FilterConfig
     * @throws ServletException ServletException
     */
    public void init(FilterConfig arg0) throws ServletException {

    }
}
