package com.gbcom.system.daoservice;

import java.util.List;

import com.gbcom.system.domain.SysArea;
import com.hc.core.orm.hibernate.EntityService;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @see com.gbcom.system.daoservice.SysAreaService
 */
@Service
public class SysAreaService extends EntityService<SysArea, Long> {
	/**
	 * SysArea初始化方法
	 * @param sessionFactory SessionFactory
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		initDao(sessionFactory, SysArea.class);
	}

	/**
	 * 按照层次获取地区。
	 * 
	 * @param layer Long
	 * @return List<SysArea>
	 */
	public List<SysArea> findByLayer(Long layer) {
		/*
		 * String hql = "from SysArea a where a.layer=?"; List<SysArea> list =
		 * find(hql, new Object []{layer}); return list;
		 */

		Criteria cri = getSession().createCriteria(SysArea.class);
		return cri.add(Restrictions.eq("layer", layer)).addOrder(Order.asc("id")).list();
	}

}