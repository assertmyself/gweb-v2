package com.gbcom.system.controller;

import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysPersonDeptService;
import com.gbcom.system.domain.SysPersonDept;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * author:
 * create date:
 * modify date:
 */
@Controller
public class SysPersonDeptController extends BaseCRUDActionController {

    @Autowired
    private SysPersonDeptService sysPersonDeptService;

    /**
     * 列表显示页面
     *
     * @param model .
     * @return String
     */
    @RequestMapping
    @UserLog
    public String grid(Model model) {
        return "view/system/sysPersonDept/grid";
    }

    /**
     * 获取列表数据
     *
     * @param response .
     * @param filters  .
     * @param columns  .
     * @param page     .
     * @param rows     .
     */
    @RequestMapping
    public void gridDataCustom(HttpServletResponse response, String filters, String columns, int page, int rows) {
        try {
            Page<SysPersonDept> pageModel = new Page<SysPersonDept>(page, rows, true);
            String hql = "from SysPersonDept order by id desc";
            //增加自定义查询条件

            //执行查询
            QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
            pageModel = sysPersonDeptService.findByPage(pageModel, queryTranslate.toString());

            //输出显示
            String json = GridJq.toJSON(columns, pageModel);
            sendJSON(response, json);

        } catch (Exception e) {
            super.processException(response, e);
        }
    }

    /**
     * 新增录入页面
     *
     * @param model .
     * @param parentId .
     * @return String
     */
    @RequestMapping
    @UserLog
    public String add(Model model, Long parentId) {
        SysPersonDept sysPersonDept = new SysPersonDept();

        //如需增加其他默认值请在此添加
        //if (parentId != null) {
        //    sysPersonDept.setParent(sysPersonDeptService.get(parentId));
        //}

        model.addAttribute("bean", sysPersonDept);

        return "view/system/sysPersonDept/input";
    }

    /**
     * 修改显示页面
     *
     * @param id    .
     * @param model .
     * @return .
     */
    @RequestMapping
    @UserLog
    public String modify(Model model, Long id) {
        SysPersonDept sysPersonDept = sysPersonDeptService.get(id);

        //处理其他业务逻辑

        model.addAttribute("bean", sysPersonDept);
        return "view/system/sysPersonDept/input";
    }

    /**
     * 查看页面
     *
     * @param id    .
     * @param model .
     * @return .
     */
    @RequestMapping
    @UserLog
    public String view(Model model, Long id) {
        SysPersonDept sysPersonDept = sysPersonDeptService.get(id);

        model.addAttribute("bean", sysPersonDept);
        return "view/system/sysPersonDept/view";
    }

    /**
     * 保存操作
     *
     * @param response .
     * @param entity   .
     * @param request  .
     * @throws Exception .
     */
    @RequestMapping
    public void save(HttpServletResponse response, @ModelAttribute("bean") SysPersonDept entity, HttpServletRequest request) throws Exception {
        try {
            SysPersonDept target;
            if (entity.getId() != null) {
                target = sysPersonDeptService.get(entity.getId());
                ReflectionUtils.copyBean(entity, target, new String[]{
                        "position",
                        "orderNo",
                        "isValid",
                        "isManager"
                });

            } else {
                target = entity;
            }
            sysPersonDeptService.save(target);

            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            super.processException(response, e);
        }
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
        sysPersonDeptService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

}