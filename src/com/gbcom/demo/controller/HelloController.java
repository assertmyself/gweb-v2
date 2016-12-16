package com.gbcom.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.demo.daoservice.HelloService;
import com.gbcom.demo.domain.Hello;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.utils.Constants;
import com.gbcom.system.utils.PrivilegeCode;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.DateTimeHelper;
import com.hc.core.utils.ReflectionUtils;
/**
 * 一个例子：hello
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-12-28,下午02:26:56
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.demo.controller.HelloController
 */
@Controller
public class HelloController extends BaseCRUDActionController<Hello> {
    @Autowired
    private HelloService helloService;
    @Autowired
    private SysUserManager sysUserManager;
	/**
	 * 显示一个页面，不使用DAO层
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping
	@UserLog
	public String index(Model model) {
		 return "view/demo/hello/index";
	}


    /**
     * 列表显示页面
     *
     * @param model .
     * @return String
     */
    @RequestMapping
    @UserLog
    public String grid(Model model) {
    	  //判断是否有编辑权限
        model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_USER_EDIT));

        return "view/demo/hello/grid";
    }
    /**
     * 获取列表数据: (HelloController.gridDataCustom)
     * @param response HttpServletResponse
     * @param filters String
     * @param columns String
     * @param page int
     * @param rows int
     * @param session HttpSession
     */
    @SuppressWarnings("unchecked")
	@RequestMapping
    public void gridDataCustom(HttpServletResponse response, String filters, String columns, int page, int rows, 
    		HttpSession session) {
        try {
            Page pageModel = new Page(page, rows, true);
            String hql = "from Hello order by id";
            //执行查询
            QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
            String query = queryTranslate.toString();
            pageModel = helloService.findByPage(pageModel, query);
            session.setAttribute(Constants.GRID_SQL_KEY, query);

            //输出显示
            String json = GridJq.toJSON(columns, pageModel);
            sendJSON(response, json);
        } catch (Exception e) {
            super.processException(response, e);
        }
    }
	/**
	 * 修改显示页面
	 * 
	 * @param id
	 *            Long
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping
    @UserLog
	public String modify(Model model, Long id) {
		Hello bean = helloService.get(id);

		// 处理其他业务逻辑
		model.addAttribute("bean", bean);
		model.addAttribute("isEdit", true);
		return "view/demo/hello/input";
	}

	/**
	 * 新增录入页面
	 * 
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping
    @UserLog
	public String add(Model model) {
		Hello bean = new Hello();

		// 如需增加其他默认值请在此添加
		model.addAttribute("bean", bean);

		return "view/demo/hello/input";
	}
    /**
     * 查看页面
     *
     * @param id    .
     * @param model .
     * @return String
     */
    @RequestMapping
    @UserLog
    public String view(Model model, Long id) {
    	Hello hello = helloService.get(id);
    	model.addAttribute("bean", hello);
    	return "view/demo/hello/view";
    }

    /**
     * 删除操作
     *
     * @param response .
     * @param id       .
     * @throws Exception .
     */
    @RequestMapping
    @UserLog
    public void delete(HttpServletResponse response, Long id) throws Exception {
        helloService.delete(id);
        sendSuccessJSON(response, "删除成功");
    }
    /**
	 * 保存操作
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param entity
	 *            Hotspot
	 * @param request
	 *            HttpServletRequest
	 * @throws Exception Exception
	 */
	@RequestMapping
	public void save(HttpServletResponse response,
			@ModelAttribute("bean") Hello entity, HttpServletRequest request)
			throws Exception {
		try {
			Hello target;
			if (entity.getId() != null) {
				target = helloService.get(entity.getId());
				ReflectionUtils.copyBean(entity, target, new String[] {
							"name", "age", "address",
							"description" });
			} else {
				target = entity;
				target.setCreateTime(DateTimeHelper.getTimestamp());
			}
			helloService.save(target);
			sendSuccessJSON(response, "保存成功");			
		} catch (Exception e) {
			super.processException(response, e);
		}

	}
	
    
}
