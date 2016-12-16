package com.gbcom.system.manager;

import com.gbcom.system.daoservice.SysDeptService;
import com.gbcom.system.daoservice.SysPersonDeptService;
import com.gbcom.system.daoservice.SysPersonService;
import com.gbcom.system.domain.SysDept;
import com.gbcom.system.domain.SysPerson;
import com.gbcom.system.domain.SysPersonDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Project:bcscmis
 * <p/>
 * <p>
 * 人员管理逻辑处理类
 * </p>
 * <p/>
 * Create On 2009-12-19 下午04:24:35
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class SysPersonManager {
    @Autowired
    private SysPersonService sysPersonService;

    @Autowired
    private SysPersonDeptService sysPersonDeptService;

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 取得人员所在的部门
     *
     * @param personId 人员ID
     * @return 人员所在的部门
     */
    public SysDept getDeptByPersonId(Long personId) {
        if (personId != null) {
            SysPerson person = sysPersonService.get(personId);
            Set<SysPersonDept> sysPersonDepts = person.getSysPersonDepts();
            if (sysPersonDepts.size() > 0) {
                SysPersonDept next = sysPersonDepts.iterator().next();
                return next.getDept();
            }
        }
        return null;
    }

    /**
     * 保存人员信息
     *
     * @param bean 需要保存的SysPerson对象
     */
    public void save(SysPerson bean) {
        // 保存人员信息
        sysPersonService.save(bean);

        Long deptId = null;
        // bean.getDeptId();

        if (deptId != null && deptId > 0) {
            SysDept dept = sysDeptService.get(deptId);

            SysPersonDept entity = new SysPersonDept();
            entity.setDept(dept);
            entity.setPerson(bean);
            sysPersonDeptService.save(entity);

            // 更新orderNo字段
            entity.setOrderNo(entity.getId());
            sysPersonDeptService.save(entity);
        }
    }

    /**
     * 更新人员信息：(1)更新已有的人员部门信息;<br/>
     * (2)调用SysPersonService的save方法保存人员信息
     *
     * @param bean 人员
     */
    @SuppressWarnings("null")
	public void update(SysPerson bean) {
        // 更新已有的人员部门信息
        Set<SysPersonDept> set = null; // bean.getSysPersonDepts();

        for (SysPersonDept tmp : set) {
            // tmp.setDept(sysDeptService.get(bean.getDeptId()));
            sysPersonDeptService.save(tmp);
        }

        sysPersonService.save(bean);
    }

    /**
     * 取得人员所在的部门
     *
     * @param person 人员
     * @return 人员所在的部门列表
     */
    public List<SysPersonDept> getPersonDept(SysPerson person) {
        String hql = "from SysPersonDept where person = " + person.getId();

        return sysPersonDeptService.find(hql);
    }

    /**
     * 查询某一部门对应的人员
     *
     * @param departId 部门ID
     * @param isOver   是否离职
     * @param distinct 是否过滤已经增加用户的人员
     * @return 人员列表
     */
    @SuppressWarnings("unchecked")
    public List<SysPerson> getPersonByDept(Long departId, Boolean isOver,
                                           Boolean distinct) {
        StringBuffer hql = new StringBuffer("from SysPerson t left join "
                + "t.sysPersonDepts t1 where 1=1 ");

        // 默认查询未离职的用户
        if (isOver != null && isOver) {
            hql.append(" and t.isOver = ").append(isOver);
        } else {
            hql.append(" and (t.isOver = null or t.isOver =").append(isOver)
                    .append(")");
        }

        if (departId != null) {
            hql.append(" and t1.dept = ").append(departId);
        }

        if (distinct != null && distinct) {
            hql.append(" and t.id not in (select person from SysUser)");
        }

        hql.append(" order by code asc");

        List list = sysPersonService.find(hql.toString());
        List<SysPerson> ret = new ArrayList<SysPerson>();

        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);

            ret.add((SysPerson) obj[0]);
        }

        return ret;
    }

    /**
     * 删除人员信息 1) 删除人员所在的部门关联信息 2) 删除人员信息
     *
     * @param id 人员ID
     */
    @SuppressWarnings("null")
	public void delete(Long id) {
        @SuppressWarnings("unused")
		SysPerson bean = sysPersonService.get(id);
        Iterator<SysPersonDept> it = null; // bean.getSysPersonDepts().iterator();

        while (it.hasNext()) {
            SysPersonDept entity = it.next();

            sysPersonDeptService.delete(entity);
        }

        sysPersonService.delete(id);
    }

    /**
     * 人员上移/下移处理
     *
     * @param id       人员ID
     * @param isMoveUp 是否上移
     */
    public void move(Long id, boolean isMoveUp) {
        @SuppressWarnings("unused")
		SysPerson bean = sysPersonService.get(id);

        Set<SysPersonDept> depts = null; //bean.getSysPersonDepts();
        SysPersonDept dept = null;

        if (depts != null && depts.size() > 0) {
            // 取得当前用户所在的部门
            for (SysPersonDept tmp : depts) {
                dept = tmp;
            }

            String hql = "from SysPersonDept where dept = "
                    + dept.getDept().getId();

            if (isMoveUp) {
                // 上移，取得比当前用户orderNo小的用户
                hql += " and orderNo < " + dept.getOrderNo();
            } else {
                // 下移，取得比当前用户orderNo大的用户
                hql += " and orderNo > " + dept.getOrderNo();
            }

            hql += " order by orderNo desc";

            List<SysPersonDept> list = sysPersonDeptService.find(hql);

            if (list != null && list.size() > 0) {
                SysPersonDept swapDept = list.get(0);

                Long swapOrderNo = swapDept.getOrderNo();
                swapDept.setOrderNo(dept.getOrderNo());
                dept.setOrderNo(swapOrderNo);

                sysPersonDeptService.update(swapDept);
                sysPersonDeptService.update(dept);
            }
        }
    }

    /**
     * 人员上移： <br/>
     * 1)取得当前人员同一部门的人员orderNo；<br/>
     * 2)将当前人员的orderNo和他上一个人员的orderNo调换。
     *
     * @param id 人员ID
     */
    public void moveUp(Long id) {
        this.move(id, true);
    }

    /**
     * 人员下移： <br/>
     * 1)取得当前人员同一部门的人员orderNo；<br/>
     * 2)将当前人员的orderNo和他上一个人员的orderNo调换。
     *
     * @param id 人员ID
     */
    public void moveDown(Long id) {
        this.move(id, false);
    }
}
