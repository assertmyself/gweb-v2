package com.gbcom.system.daoservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbcom.system.domain.SysAreaNes;
import com.gbcom.system.utils.CollectionUtil;
import com.hc.core.orm.hibernate.EntityService;

/**
 * sysarea service
 * 
 * <p>
 * 
 * @author syz
 *         <p>
 * @date 2015-5-23,上午09:54:03
 *       <p>
 * @version v1.0.0
 *          <p>
 * @see SysAreaNesService
 */
@Service
public class SysAreaNesService extends EntityService<SysAreaNes, Long> {
	/**
	 * SysAreaNes初始化方法
	 * 
	 * @param sessionFactory
	 *            SessionFactory
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		initDao(sessionFactory, SysAreaNes.class);
	}

	/**
	 * 删除无效的SysAreaNes
	 */
	public void deleteInvalid() {
		String hql = "from SysAreaNes a  where a.area  = null";
		List<SysAreaNes> list = find(hql);
		for (SysAreaNes each : list) {
			delete(each);
		}
	}

	/**
	 * 获取所有分组设备id
	 * 
	 * @return 所有分组设备id
	 */
	public List<String> getAllHots() {
		List<String> neIdList = new ArrayList<String>();
		String hql = "from SysAreaNes";
		List<SysAreaNes> list = find(hql);
		for (SysAreaNes ne : list) {
			neIdList.add(ne.getNeID());
		}
		return neIdList;
	}

	/**
	 * 根据热点找到区域
	 * 
	 * @param gwId
	 *            热点标识
	 * @return 区域对象和热点对应关系
	 */
	public SysAreaNes getSysAreaNes(String gwId) {
		String hql = "from SysAreaNes a where a.neID = ?";
		SysAreaNes sysAreaNes = findUnique(hql, new Object[] { gwId });
		return sysAreaNes;
	}

	/**
	 * 根据热点id删除区域和设备的关联关系
	 * 
	 * @param gwId
	 *            热点标识数组
	 */
	public void deleteSysAreaNesByNeIds(String[] gwId) {
		String neIds = CollectionUtil.listToString(Arrays.asList(gwId));
		String hql = "DELETE from SysAreaNes a WHERE a.neID IN( " + neIds
				+ " )";
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}
}