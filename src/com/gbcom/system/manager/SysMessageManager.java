package com.gbcom.system.manager;

import java.util.List;

import com.gbcom.system.daoservice.SysMessageService;
import com.gbcom.system.domain.SysMessage;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * sysMessage
 * @author huanghaiyun
 * @createTime 2016-12-9下午01:30:06
 */
@Service
public class SysMessageManager {
	@Autowired
	private SysMessageService sysMessageService;
	
	/**
	 * 根据种类获取messge，由时间排序
	 * @param count 返回种类
	 * @param type 类型
	 * @return
	 */
	public List<SysMessage> getMessages(Integer type,Integer count){
		String hql="From SysMessage where 1=1 ";
		if(type!=null){
			hql=hql+"  and type="+type;
		}else{
			hql=hql+"  and type in('1','2')";
		}
		System.out.println();
		Query q=sysMessageService.getSession().createQuery(hql);
		
		q.setFirstResult(0);
		q.setMaxResults(count);
		List<SysMessage> list=q.list();
		return list;
	}
	/**
	 * 根据种类获取messge，由时间排序
	 * @param count 返回种类
	 * @param type 类型
	 * @return
	 */
	public List<SysMessage> getMessages(Integer type){
		return getMessages(type,2);
	}
	/**
	 * 获取未读通知数量
	 * @param type
	 * @return
	 */
	public int getUnreadNum(Integer type){
		String sql="select count(*) from Sys_Message where status=2 ";
		if(type!=null){
			sql=sql+"and type="+type;
		}
		Object total = sysMessageService.getSession().createSQLQuery(sql).uniqueResult();
		return Integer.parseInt(total.toString());
	}
	/**
	 * 获取未读通知数量
	 * @param type
	 * @return
	 */
	public int getUnreadNum(){
		return getUnreadNum(null);
	}

}
