package com.gbcom.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gbcom.system.domain.SysUser;
import com.gbcom.system.manager.SysLogCustomManager;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.utils.Constants;
import com.gbcom.system.utils.UserSessionUtils;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.utils.StringHelper;

/**
 * <p>
 * 登录后页面跳转处理处理类
 * </p>
 * User: Hzxia
 * Date: 2012-2-20
 * Time: 13:33:50
 */
@Controller
public class LoginController extends BaseCRUDActionController {
    @Autowired
    private SysUserManager sysUserManager;

    @Autowired
    private SysLogCustomManager sysLogManager;

    /**
     * 登录后页面跳转处理（包含管理系统和网站监管平台）
     *
     * @param model    .
     * @param request  .
     * @param response .
     * @throws Exception .
     */
    @RequestMapping
    public void dispatch(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
			String contextPath = request.getContextPath();

			SysUser user = sysUserManager.getSysUser();
			String url = "/mainPage/index.do";

			if (user != null) {
			    sysUserManager.loadUserPrivileges(user.getId(), true);
			}
			url = contextPath + url;

			UserSessionUtils.getInstance().initDevDomain(user);//初始化管理域到session中。
			
			
			//记录系统日志
			sysLogManager.log(request, Constants.LOG_TYPE_LOGIN);
			//重定向  return "redirct:url"
			response.sendRedirect(url);
		} catch (Exception e) {
			super.processException(response, e);
		}
    }
    
    /**
     * 退出系统（包含管理系统和网站监管平台）
     *
     * @param session  .
     * @param request  .
     * @param response .
     * @param model    .
     * @return .
     * @throws Exception .
     */
    @RequestMapping
    public String logout(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //记录系统日志
        sysLogManager.log(request, Constants.LOG_TYPE_LOGOUT);

        if (session != null) {
            session.invalidate();
        }

        //判断页面来源，如果从监管平台退出则转到网站首页，否则转到系统登录页面
        String referer = request.getHeader("REFERER");
        String url = "login.jsp?error=expired";
        if (!StringHelper.isEmpty(referer)) {
            if (referer.indexOf("/platform/") >= 0) {
				url = "site/index.do";
			}
        }
        model.addAttribute("url", url);

        return "logout";
    }
}
