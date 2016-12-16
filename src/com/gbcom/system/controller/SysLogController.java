package com.gbcom.system.controller;

import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysLogService;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.utils.Constants;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * author:
 * create date:
 * modify date:
 */
@Controller
public class SysLogController extends BaseCRUDActionController {
  
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表显示页面
     *
     * @param model .
     * @return String
     */
    @RequestMapping
    @UserLog
    public String grid(Model model) {
        return "view/system/sysLog/grid";
    }

    /**
     * 获取列表数据
     *
     * @param response .
     * @param filters  .
     * @param columns  .
     * @param page     .
     * @param rows     .
     * @param session HttpSession
     */
    @SuppressWarnings("unchecked")
	@RequestMapping
    public void gridDataCustom(HttpServletResponse response, String filters, String columns, int page, int rows, 
    		HttpSession session) {
        try {
            Page pageModel = new Page(page, rows, true);
            String hql = "from SysLog order by id desc";
            //执行查询
            QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
            String query = queryTranslate.toString();
            pageModel = sysLogService.findByPage(pageModel, query);
            session.setAttribute(Constants.GRID_SQL_KEY, query);

            //输出显示
            String json = GridJq.toJSON(columns, pageModel);
            sendJSON(response, json);

        } catch (Exception e) {
            super.processException(response, e);
        }
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
        SysLog sysLog = sysLogService.get(id);

        model.addAttribute("bean", sysLog);

        return "view/system/sysLog/view";
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
        sysLogService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

}