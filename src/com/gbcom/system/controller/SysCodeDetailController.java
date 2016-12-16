package com.gbcom.system.controller;

import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysCodeDetailService;
import com.gbcom.system.daoservice.SysCodeService;
import com.gbcom.system.domain.SysCodeDetail;
import com.gbcom.system.utils.Constants;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.ReflectionUtils;
import org.hibernate.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * author:
 * create date:
 * modify date:
 */
@Controller
public class SysCodeDetailController extends BaseCRUDActionController<SysCodeDetail> {

    @Autowired
    private SysCodeDetailService sysCodeDetailService;

    @Autowired
    private SysCodeService sysCodeService;

    /**
     * 树+列表显示页面
     *
     * @param model .
     * @return .
     */
    @RequestMapping
    public String init(Model model) {
        return "view/system/sysCodeDetail/init";
    }


    /**
     * 列表显示页面
     *
     * @param model .
     * @param id    .
     * @return .
     */
    @RequestMapping
    @UserLog
    public String grid(Model model, Long id) {

        //判断是否有编辑权限
        if (id != null) {
            model.addAttribute("codeId", id);
        }

        return "view/system/sysCodeDetail/grid";
    }

    /**
     * 获取列表数据
     *
     * @param response .
     * @param filters  .
     * @param columns  .
     * @param page     .
     * @param rows     .
     * @param codeId   .
     * @param session HttpSession
     */
    @RequestMapping
    public void gridDataCustom(HttpServletResponse response, String filters, String columns, int page, int rows, Long codeId, HttpSession session) {
        try {
            Page<SysCodeDetail> pageModel = new Page<SysCodeDetail>(page, rows, true);
            String hql = "from SysCodeDetail order by id desc";
            if (codeId != null) {
                hql = "from SysCodeDetail where sysCode.id=" + codeId + " order by id desc";
            }
            //增加自定义查询条件

            //执行查询
            QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
            String query = queryTranslate.toString();
            pageModel = sysCodeDetailService.findByPage(pageModel, query);
            session.setAttribute(Constants.GRID_SQL_KEY, query);

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
     * @param model    .
     * @param parentId .
     * @return .
     */
    @RequestMapping
    @UserLog
    public String add(Model model, Long parentId) {
        SysCodeDetail sysCodeDetail = new SysCodeDetail();

        //如需增加其他默认值请在此添加

        sysCodeDetail.setSysCode(sysCodeService.get(parentId));
        sysCodeDetail.setIsValid(true);

        model.addAttribute("bean", sysCodeDetail);

        return "view/system/sysCodeDetail/input";
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
        SysCodeDetail sysCodeDetail = sysCodeDetailService.get(id);

        //处理其他业务逻辑
        model.addAttribute("bean", sysCodeDetail);

        return "view/system/sysCodeDetail/input";
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
        SysCodeDetail sysCodeDetail = sysCodeDetailService.get(id);

        model.addAttribute("bean", sysCodeDetail);
        return "view/system/sysCodeDetail/view";
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
    public void save(HttpServletResponse response, @ModelAttribute("bean") SysCodeDetail entity, HttpServletRequest request) throws Exception {
        try {
            SysCodeDetail target;
            if (entity.getId() != null) {
                target = sysCodeDetailService.get(entity.getId());
                ReflectionUtils.copyBean(entity, target, new String[]{
                        "code",
                        "name",
                        "isReserved",
                        "tag",
                        "isValid",
                        "description"
                });

            } else {
                target = entity;
            }

            String codeId = request.getParameter("codeId");
            if (!StringHelper.isEmpty(codeId)) {
                target.setSysCode(sysCodeService.get(Long.valueOf(codeId)));
            }
            sysCodeDetailService.save(target);

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
        sysCodeDetailService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

}