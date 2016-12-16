package com.gbcom.system.controller;

import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysPrivilegeService;
import com.gbcom.system.domain.SysCodeDetail;
import com.gbcom.system.domain.SysPrivilege;
import com.gbcom.system.manager.SysCodeManager;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.tree.ZTreeBranch;
import com.gbcom.system.tree.ZTreeNode;
import com.gbcom.system.utils.Constants;
import com.gbcom.system.utils.PrivilegeCode;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.JspHelper;
import com.hc.core.utils.ReflectionUtils;
import com.hc.core.utils.StringHelper;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * author:
 * create date:
 * modify date:
 */
@Controller
public class SysPrivilegeController extends BaseCRUDActionController {

    @Autowired
    private SysUserManager sysUserManager;

    @Autowired
    private SysPrivilegeService sysPrivilegeService;

    @Autowired
    private SysCodeManager sysCodeManager;

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
        model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_PRIVILEGE_EDIT));

        return "view/system/sysPrivilege/grid";
    }

    /**
     * 获取列表数据
     *
     * @param response .
     * @param filters  .
     * @param columns  .
     * @param page     .
     * @param rows     .
     * @param session .
     */
    @RequestMapping
    public void gridDataCustom(HttpServletResponse response, String filters, String columns, int page, int rows, HttpSession session) {
        try {
            Page<SysPrivilege> pageModel = new Page<SysPrivilege>(page, rows, true);
            String hql = "from SysPrivilege order by treeId desc";
            //增加自定义查询条件

            //执行查询
            QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
            String query = queryTranslate.toString();
            pageModel = sysPrivilegeService.findByPage(pageModel, query);
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
     * @param model .
     * @param parentId Long
     * @return String
     */
    @RequestMapping
    @UserLog
    public String add(Model model, Long parentId) {
        SysPrivilege sysPrivilege = new SysPrivilege();

        //如需增加其他默认值请在此添加
        if (parentId != null) {
            sysPrivilege.setParent(sysPrivilegeService.get(parentId));
        }

        model.addAttribute("bean", sysPrivilege);
        model.addAttribute("typeCode", Constants.SYS_PRIVILEGE_TYPE);

        return "view/system/sysPrivilege/input";
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
        SysPrivilege sysPrivilege = sysPrivilegeService.get(id);

        //处理其他业务逻辑
        model.addAttribute("bean", sysPrivilege);
        model.addAttribute("typeCode", Constants.SYS_PRIVILEGE_TYPE);

        return "view/system/sysPrivilege/input";
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
        SysPrivilege sysPrivilege = sysPrivilegeService.get(id);

        model.addAttribute("bean", sysPrivilege);
        return "view/system/sysPrivilege/view";
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
    public void save(HttpServletResponse response, @ModelAttribute("bean") SysPrivilege entity, HttpServletRequest request) throws Exception {
        try {
            SysPrivilege target;
            if (entity.getId() != null) {
                target = sysPrivilegeService.get(entity.getId());
                ReflectionUtils.copyBean(entity, target, new String[]{
                        "code",
                        "name",
                        "tag",
                        "url",
                        "definition",
                        "description",
                        "type"
                });

            } else {
                target = entity;
            }
            sysPrivilegeService.save(target);

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
        List<SysPrivilege> list = sysPrivilegeService.findByQuery("from SysRolePrivilege where privilege.id=" + id);
        if (list.size() > 0) {
            sendErrorJSON(response, "删除失败，已被角色引用");
        } else {
            sysPrivilegeService.delete(id);
            sendSuccessJSON(response, "删除成功");
        }
    }

    /**
     * 树+列表显示页面
     *
     * @param model 。
     * @return String
     */
    @RequestMapping
    public String init(Model model) {

        return "view/system/sysPrivilege/init";
    }

    /**
     * 树显示页面
     *
     * @param model 。
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping
    public String tree(Model model, HttpServletRequest request) {
        //判断是否有编辑权限
        model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_PRIVILEGE_EDIT));

        model.addAttribute("clazz", SysPrivilege.class.getName());
        return "view/system/sysPrivilege/tree";
    }

    /**
     * 获取树数据
     * @param type String
     * @param id String
     * @param icon String
     * @param model Model
     * @return String
     */
    @RequestMapping
    public String treeData(String type, String id, String icon, Model model) {
        ZTreeBranch zTreeBranch = new ZTreeBranch();
        zTreeBranch.setIcons(icon.split(","));

        if (StringUtils.isEmpty(id)) {
            zTreeBranch.addTreeNode(zTreeBranch.getRootNode("根节点"));
        } else if (StringUtils.equals(id, "root")) {
            String hql = "from SysPrivilege where parent.id is null order by treeId asc";
            List<SysPrivilege> nodeList = sysPrivilegeService.findByQuery(hql);
            for (SysPrivilege node : nodeList) {
                boolean isLeaf = BooleanUtils.isTrue(node.getIsLeaf());
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(JspHelper.getString(node.getId()));
                treeNode.setIsLeaf(isLeaf);
                treeNode.setName(node.getName());
                treeNode.setIcon("1");
                treeNode.setType("data");
                zTreeBranch.addTreeNode(treeNode);
            }
        } else if (StringUtils.equals(type, "data")) {
            //按钮权限
            SysCodeDetail privilegeType = sysCodeManager.getCodeDetailByCode(Constants.SYS_PRIVILEGE_TYPE, Constants.SYS_PRIVILEGE_TYPE_BUTTON);

            String hql = "from SysPrivilege where parent.id=" + id + " order by treeId asc";
            List<SysPrivilege> nodeList = sysPrivilegeService.findByQuery(hql);
            for (SysPrivilege node : nodeList) {
                boolean isLeaf = BooleanUtils.isTrue(node.getIsLeaf());
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(JspHelper.getString(node.getId()));
                treeNode.setIsLeaf(isLeaf);
                treeNode.setName(node.getName());

                treeNode.setIcon("1");
                if (privilegeType != null && node.getType().getId().equals(privilegeType.getId())) {
                    treeNode.setIcon("2");
                }

                treeNode.setType("data");
                zTreeBranch.addTreeNode(treeNode);
            }
        }
        model.addAttribute("msg", zTreeBranch.toJsonString(true));
        return "common/msg";
    }

    /**
     * 批量创建按钮权限
     *
     * @param model Model
     * @param parentId Long
     * @return String
     */
    @RequestMapping
    public String batchInput(Model model, Long parentId) {
        SysPrivilege sysPrivilege = new SysPrivilege();

        //如需增加其他默认值请在此添加
        if (parentId != null) {
            sysPrivilege.setParent(sysPrivilegeService.get(parentId));
        }
        model.addAttribute("bean", sysPrivilege);

        return "view/system/sysPrivilege/batchInput";
    }

    /**
     * 批量创建按钮权限--保存
     *
     * @param response HttpServletResponse
     * @param request HttpServletRequest
     * @throws Exception .
     */
    @RequestMapping
    public void batchInputSave(HttpServletResponse response, HttpServletRequest request) throws Exception {
        try {
            String parentId = request.getParameter("parent");
            String[] batchTypes = request.getParameterValues("batchType");
            if (!StringHelper.isEmpty(parentId)) {
                if (batchTypes != null && batchTypes.length > 0) {
                    SysPrivilege parent = sysPrivilegeService.get(Long.valueOf(parentId));
                    SysCodeDetail privilegeType = sysCodeManager.getCodeDetailByCode(Constants.SYS_PRIVILEGE_TYPE, Constants.SYS_PRIVILEGE_TYPE_BUTTON);
                    if (privilegeType != null) {
                        String name = "";
                        for (String batchType : batchTypes) {
                            if ("edit".equals(batchType)) {
                                name = "编辑";
                            } else if ("audit".equals(batchType)) {
                                name = "审核";
                            }
                            SysPrivilege data = new SysPrivilege();
                            data.setCode(parent.getCode() + "_" + batchType);
                            data.setName(parent.getName() + "_" + name);
                            data.setType(privilegeType);
                            data.setParent(parent);
                            System.out.println("data = " + data.toString());
                            sysPrivilegeService.save(data);
                        }
                    }
                }
            }
            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            super.processException(response, e);
        }
    }

    /**
     * 查看移动权限
     *
     * @param id    .
     * @param model .
     * @return .
     */
    @RequestMapping
    public String move(Long id, Model model) {
        SysPrivilege sysPrivilege = sysPrivilegeService.findUniqueByProperty("id", id);
        List<SysPrivilege> sysPrivilegeList;
        //去掉子节点和自身节点
        SysCodeDetail codeDetailByCode = sysCodeManager.getCodeDetailByCode(Constants.SYS_PRIVILEGE_TYPE, Constants.SYS_PRIVILEGE_TYPE_BUTTON);
        String hql = "from SysPrivilege where type<> " + codeDetailByCode.getId() + " and tree_id not like '" + sysPrivilege.getTreeId() + "%'   order by treeId asc";
        sysPrivilegeList = sysPrivilegeService.find(hql);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (SysPrivilege sys : sysPrivilegeList) {
            map = new HashMap<String, Object>();
            map.put("id", sys.getId());
            map.put("name", JspHelper.getLevelStr(sys.getTreeId(), "&nbsp;&nbsp;&nbsp;") + sys.getName());//树形节点前加空格
            list.add(map);
        }
        model.addAttribute("bean", sysPrivilege);
        model.addAttribute("sysList", list);
        return "view/system/sysPrivilege/move";
    }

    /**
     * 保存权限
     *
     * @param response .
     * @param request  .
     */
    @RequestMapping
    public void moveSave(HttpServletResponse response, HttpServletRequest request) {
        String id = request.getParameter("id");
        String parentid = request.getParameter("parentid");
        SysPrivilege sysPrivilege = sysPrivilegeService.findUniqueByProperty("id", Long.parseLong(id));

        Long oldParentId = null;
        SysPrivilege oldParent = sysPrivilege.getParent();
        if (oldParent != null) {
            oldParentId = oldParent.getId();
        }
        if (StringHelper.isEmpty(parentid)) {
            sysPrivilege.setParent(null);
        } else {
            SysPrivilege parentsysPrivilege = sysPrivilegeService.findUniqueByProperty("id", Long.parseLong(parentid));
            sysPrivilege.setParent(parentsysPrivilege);

            if (parentsysPrivilege.getIsLeaf()) {
                parentsysPrivilege.setIsLeaf(false);
                sysPrivilegeService.save(parentsysPrivilege);
            }
        }
        sysPrivilegeService.save(sysPrivilege);

        if (oldParentId != null) {
            List<SysPrivilege> list = sysPrivilegeService.findByQuery("from SysPrivilege where parent.id=" + oldParentId + " and id<>" + id);
            if (list.size() == 0) {
                oldParent.setIsLeaf(true);
                sysPrivilegeService.save(oldParent);
            }
        }

        sendSuccessJSON(response, "保存成功");
    }
}