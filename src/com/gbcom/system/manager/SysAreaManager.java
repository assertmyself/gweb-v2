package com.gbcom.system.manager;

import com.gbcom.system.daoservice.*;
import com.gbcom.system.domain.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 管理区域 session + db 相关管理类。
 * 
 * <p>
 * 
 * @author syz
 *         <p>
 * @date 2015-5-27,下午03:32:49
 *       <p>
 * @version v1.0.0
 *          <p>
 * @see com.gbcom.system.manager.SysAreaManager
 */
@Service
public class SysAreaManager {
	private static final Logger logger = Logger.getLogger(SysAreaManager.class);
	@Autowired
	private SysUserManager sysUserManager;
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysAreaService sysAreaService;

	/**
	 * 是否采用 分组视图 仅对管理员账号有用，，当管理员没有分组默认显示商业wifi的组织结构。
	 * 
	 * @return boolean
	 */
	public boolean isGroupView() {
		return sysAreaService.findAll().size() != 0;
	}

	/**
	 * 获取所有管理网元集合。
	 * 
	 * @return Set<SysAreaNes>
	 */
	public Set<SysAreaNes> getAllAreaNes() {
		// return new HashSet<SysAreaNes>(sysAreaNesService.findAll());
		Set<SysAreaNes> sets = new HashSet<SysAreaNes>();
		List<SysArea> list = new ArrayList<SysArea>();
		findLeafChild(null, list);
		for (SysArea each : list) {
			sets.addAll(each.getNes());
		}
		return sets;

	}

	/**
	 * 根据登陆名获取用户
	 * 
	 * @param loginName
	 *            String
	 * @return SysUser
	 */
	public SysUser getSysUser(String loginName) {
		List<SysUser> list = sysUserService.findByProperty("loginName",
				loginName);
		if (list.size() > 0) {
			return list.iterator().next();
		}
		return null;
	}

	/**
	 * @param layer
	 *            Long
	 * @return List<SysArea>
	 */
	public List<SysArea> findByLayer(Long layer) {
		return sysAreaService.findByLayer(layer);

	}

	/**
	 * @param id
	 *            Long
	 * @return SysArea
	 */
	public SysArea getSysArea(Long id) {
		return sysAreaService.get(id);
	}

	/**
	 * 获取当前管理区域。
	 * 
	 * @return SysArea
	 */
	public SysArea getSysArea() {
		return sysUserManager.getSysUser().getArea();
	}

	/**
	 * 获取当前管理区域。
	 * 
	 * @param area
	 *            SysArea
	 * @return List<SysArea>
	 */
	public List<SysArea> getChild(SysArea area) {
		String hql = "from SysArea where parent.id=" + area.getId()
				+ " order by id asc";
		List<SysArea> nodeList = sysAreaService.findByQuery(hql);
		return nodeList;
	}

	/**
	 * 获取当前管理区域。
	 * 
	 * @param area
	 *            SysArea
	 * @param list
	 *            List<SysArea>
	 */
	public void findLeafChild(SysArea area, List<SysArea> list) {
		try {
			if (area == null) {
				for (SysArea each : findByLayer(1L)) {
					findLeafChild(each, list);
				}
				return;
			}
			if (area.getIsLeaf()) {
				list.add(area);
			} else {
				for (SysArea each : getChild(area)) {
					if (each != null) {
						findLeafChild(each, list);
					}
				}
			}
		} catch (Exception e) {
			logger.error("find leaf child failed!!! " + e);
		}
	}

	/**
	 * 制定地区的管理网元。
	 * 
	 * @param sysArea
	 *            SysArea
	 * @return Set<SysAreaNes>
	 */
	public Set<SysAreaNes> getAreaNes(SysArea sysArea) {
		if (sysArea != null) {
			return sysArea.getNes();
		}
		return new HashSet<SysAreaNes>();
	}

	/**
	 * 当前session
	 * 
	 * @return Set<SysAreaNes>
	 */
	public Set<SysAreaNes> getAreaNes() {
		return getAreaNes(getSysArea());
	}

	

	/**
	 * session-method
	 * 
	 * @return List<String>
	 */
	public List<String> sysAreaHots() {
		return sysAreaHots(getSysArea());
	}

	/**
	 * session-method
	 * 
	 * @param id
	 *            Long
	 * @return List<String>
	 */
	public List<String> sysAreaHots(Long id) {
		return sysAreaHots(getSysArea(id));
	}

	/**
	 * 返回所有未分组的设备
	 * 
	 * @return List<String>
	 */
	public List<String> allSysAreaNeIDs() {
		List<String> list = new ArrayList<String>();
		for (SysAreaNes ne : getAllAreaNes()) {
			if (ne != null) {
				list.add(ne.getNeID());
			}
		}
		return list;
	}

	/**
	 * 制定区域的。ID list 与设备
	 * 
	 * @param area
	 *            SysArea
	 * @return List<String>
	 */
	public List<String> sysAreaHots(SysArea area) {
		List<String> list = new ArrayList<String>();
		for (SysAreaNes ne : area.getNes()) {
			if (ne != null) {
				try {
					list.add(ne.getNeID());
				} catch (NumberFormatException e) {
					continue;
				}
			}
		}
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String arg1, String arg2) {
				return arg1.compareToIgnoreCase(arg2);
			}
		});
		return list;
	}

	/**
	 * @return String
	 */
	public String sysAreaNames() {
		return sysAreaNeNames(getSysArea());
	}

	/**
	 * name string ,,build.s
	 * 
	 * @param area
	 *            SysArea
	 * @return String
	 */
	public String sysAreaNeNames(SysArea area) {
		String keys = "";
		for (SysAreaNes ne : area.getNes()) {
			if (ne != null) {
			}
			keys += ne.getNeName() + ",";
		}
		return keys.contains(",") ? keys.substring(0, keys.lastIndexOf(","))
				: "";
	}

	/**
	 * @param area
	 *            SysArea
	 * @return List<String>
	 */
	public List<String> sysAreaHotsUnderSysArea(SysArea area) {

		List<SysArea> sysAreaList = new ArrayList<SysArea>();
		List<String> sysAreaHots = new ArrayList<String>();

		findLeafChild(area, sysAreaList);
		for (SysArea sys : sysAreaList) {
			List<String> list = sysAreaHots(sys);
			sysAreaHots.addAll(list);
		}
		return sysAreaHots;

	}

	/**
	 * session
	 * 
	 * @return List<String>
	 */
	public List<String> sysAreaHotsUnderSysArea() {
		SysUser sysUser = sysUserManager.getSysUser();
		return sysAreaHotsUnderSysArea(sysUser.getArea());
	}

	/**
	 * 获取某个区域下所有的neID
	 * 
	 * @param areaId
	 *            区域ID
	 * @return 该区域下所有的neID
	 */
	public List<String> sysAreaHotsUnderSysArea(Long areaId) {
		SysArea area = getSysArea(areaId);
		return sysAreaHotsUnderSysArea(area);

	}

	
}
