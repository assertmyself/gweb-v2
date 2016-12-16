package com.gbcom.system.controller;

import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysDeptService;
import com.gbcom.system.domain.SysDept;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.tree.ZTreeBranch;
import com.gbcom.system.tree.ZTreeNode;
import com.gbcom.system.utils.Constants;
import com.gbcom.system.utils.PrivilegeCode;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.JspHelper;
import com.hc.core.utils.ReflectionUtils;
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
import java.util.List;

/**
 * author:
 * create date:
 * modify date:
 */
@Controller
public class SysDeptController extends BaseCRUDActionController {
  
    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    protected SysUserManager sysUserManager;

    /**
     * 列表显示页面
     *
     * @param model .
     * @return String
     */
    @RequestMapping
    @UserLog
    public String grid(Model model) {
        return "view/system/sysDept/grid";
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
    @RequestMapping
    public void gridDataCustom(HttpServletResponse response, String filters, String columns, int page, int rows, HttpSession session) {
        try {
            Page<SysDept> pageModel = new Page<SysDept>(page, rows, true);
            String hql = "from SysDept order by treeId desc";
            //增加自定义查询条件

            //执行查询
            QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
            String query = queryTranslate.toString();
            pageModel = sysDeptService.findByPage(pageModel, query);
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
     * @return Stirng
     */
    @RequestMapping
    @UserLog
    public String addDw(Model model, Long parentId) {
        SysDept sysDept = new SysDept();

        //如需增加其他默认值请在此添加
        if (parentId != null) {
            sysDept.setParent(sysDeptService.get(parentId));
        }
        sysDept.setIsCompany(true);

        model.addAttribute("bean", sysDept);

        return "view/system/sysDept/inputDw";
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
    public String addDept(Model model, Long parentId) {
        SysDept sysDept = new SysDept();

        //如需增加其他默认值请在此添加
        if (parentId != null) {
            sysDept.setParent(sysDeptService.get(parentId));
        }
        sysDept.setIsCompany(false);

        model.addAttribute("bean", sysDept);

        return "view/system/sysDept/inputDept";
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
    public String modifyDw(Model model, Long id) {
        SysDept sysDept = sysDeptService.get(id);

        //处理其他业务逻辑

        model.addAttribute("bean", sysDept);
        return "view/system/sysDept/inputDw";
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
    public String modifyDept(Model model, Long id) {
        SysDept sysDept = sysDeptService.get(id);

        //处理其他业务逻辑

        model.addAttribute("bean", sysDept);
        return "view/system/sysDept/inputDept";
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
    public String viewDw(Model model, Long id) {
        SysDept sysDept = sysDeptService.get(id);

        model.addAttribute("bean", sysDept);
        return "view/system/sysDept/viewDw";
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
    public String viewDept(Model model, Long id) {
        SysDept sysDept = sysDeptService.get(id);

        model.addAttribute("bean", sysDept);
        return "view/system/sysDept/viewDept";
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
    public void save(HttpServletResponse response, @ModelAttribute("bean") SysDept entity, HttpServletRequest request) throws Exception {
        try {
            SysDept target;
            if (entity.getId() != null) {
                target = sysDeptService.get(entity.getId());
                ReflectionUtils.copyBean(entity, target, new String[]{
                        "code",
                        "name",
                        "shortName",
                        "organizationCode",
                        "contacter",
                        "telephone",
                        "address",
                        "orderNo",
                        "memo",
                        "isValid"
                });

            } else {
                target = entity;
                if (target.getParent() == null || target.getParent().getId() == null){
                	target.setParent(null);
                } else {
                    target.setParent(sysDeptService.get(target.getParent().getId()));
                }

            }

            sysDeptService.save(target);

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
        sysDeptService.delete(id);

        sendSuccessJSON(response, "删除成功");
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
    public String treeDataForSelect(String type, String id, String icon, Model model) {
        ZTreeBranch treeBranch = new ZTreeBranch();
        treeBranch.setIcons(icon.split(","));

        if (StringUtils.isEmpty(id)) {
            treeBranch.addTreeNode(treeBranch.getRootNode("根节点"));
        } else if (StringUtils.equals(id, "root")) {
            String hql = "from SysDept where parent.id is null order by treeId asc";
            List<SysDept> nodeList = sysDeptService.findByQuery(hql);
            for (SysDept data : nodeList) {
                boolean isLeaf = data.getIsLeaf();
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(String.valueOf(data.getId()));
                treeNode.setIsLeaf(isLeaf);
                treeNode.setName(data.getName());
                if (BooleanUtils.isTrue(data.getIsCompany())) {
                    treeNode.setIcon("1");
                    treeNode.setType("unit");
                } else {
                    treeNode.setIcon("2");
                    treeNode.setType("dept");
                }
                treeBranch.addTreeNode(treeNode);
            }
        } else if (StringUtils.equals(type, "unit") || StringUtils.equals(type, "dept")) {
            String hql = "from SysDept where parent.id=" + id + " order by treeId asc";
            List<SysDept> nodeList = sysDeptService.findByQuery(hql);
            for (SysDept data : nodeList) {
                boolean isLeaf = data.getSysPersonDepts().size() == 0;
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(String.valueOf(data.getId()));
                treeNode.setIsLeaf(isLeaf);
                treeNode.setName(data.getName());
                if (BooleanUtils.isTrue(data.getIsCompany())) {
                    treeNode.setIcon("1");
                    treeNode.setType("unit");
                } else {
                    treeNode.setIcon("2");
                    treeNode.setType("dept");
                }
                treeBranch.addTreeNode(treeNode);
            }
        }
        model.addAttribute("msg", treeBranch.toJsonString(true));
        return "common/msg";
    }

    /**
     * 树+列表显示页面
     *
     * @param model 。
     * @return String
     */
    @RequestMapping
    public String init(Model model) {

        return "view/system/sysDept/init";
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
        model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_DEPT_EDIT));

        model.addAttribute("clazz", SysDept.class.getName());
        return "view/system/sysDept/tree";
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
            String hql = "from SysDept where parent.id is null order by treeId asc";
            List<SysDept> nodeList = sysDeptService.findByQuery(hql);
            for (SysDept node : nodeList) {
                boolean isLeaf = BooleanUtils.isTrue(node.getIsLeaf());
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(JspHelper.getString(node.getId()));
                treeNode.setIsLeaf(isLeaf);
                treeNode.setName(node.getName());
                if (BooleanUtils.isTrue(node.getIsCompany())) {
                    treeNode.setIcon("1");
                    treeNode.setType("unit");
                } else {
                    treeNode.setIcon("2");
                    treeNode.setType("dept");
                }
                zTreeBranch.addTreeNode(treeNode);
            }
        } else if (StringUtils.equals(type, "unit") || StringUtils.equals(type, "dept")) {
            String hql = "from SysDept where parent.id=" + id + " order by treeId asc";
            List<SysDept> nodeList = sysDeptService.findByQuery(hql);
            for (SysDept node : nodeList) {
                boolean isLeaf = BooleanUtils.isTrue(node.getIsLeaf());
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(JspHelper.getString(node.getId()));
                treeNode.setIsLeaf(isLeaf);
                treeNode.setName(node.getName());
                if (BooleanUtils.isTrue(node.getIsCompany())) {
                    treeNode.setIcon("1");
                    treeNode.setType("unit");
                } else {
                    treeNode.setIcon("2");
                    treeNode.setType("dept");
                }
                zTreeBranch.addTreeNode(treeNode);
            }
        }
        model.addAttribute("msg", zTreeBranch.toJsonString(true));
        return "common/msg";
    }


}