package com.gbcom.common.template.xml.snmp;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.gbcom.common.template.xml.northful.NorthConfigManager;
import com.gbcom.omc.platform.da.xml.MyXMLException;
import com.gbcom.op.util.xml.XStreamUtil;

/**
 * MIB节点工厂类，支持item名称与oid节点集合的映射
 * 
 * @author SunYanzheng
 * @date 上午10:18:59
 * @version v1.0.0
 * @see MibNodeManager
 */
public final class MibNodeManager {
	private static final Logger LOG = Logger.getLogger(MibNodeManager.class);
	final String root = SnmpTempManager.getInstance().getDefaultSnmpConfig().getRootOID();
	private MibNodeContext context;

	private Map<String, Map<String, MibNode>> itemMap = new HashMap<String, Map<String, MibNode>>();
	private Map<String, MibNode> nameMap = new HashMap<String, MibNode>();
	private Map<String, Boolean> isVectorMap = new HashMap<String, Boolean>();

	private static MibNodeManager instance = new MibNodeManager();

	/**
	 * 获取单例
	 * 
	 * @return MibNodeManager
	 */
	public static MibNodeManager getInstance() {
		return instance;
	}

	private MibNodeManager() {
		try {
			load();
		} catch (Exception e) {
			LOG.error("******* LOAD MIBNODE FILE FAILED!!", e);
		}
	}
	/**
	 * 加载mibnode文件内容
	 * 
	 * @throws MyXMLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private void load() throws MyXMLException, IOException, URISyntaxException {
		final Class<?>[] classContext = { MibNodeContext.class,
				MibNodeCollection.class, MibNode.class };
		//首先加载核心mib contains  bblink ,so not bblink mibnode file.
		URL url = MibNodeManager.class.getResource("/config/snmp/mibnode/core_mibnode.xml");
		File file = new File(url.toURI());
		addFileInfo(file,classContext);
		
		//加载北向的，如果有
		List<String> names = NorthConfigManager.getInstance().getNorthConfig().getList();
		for (String name : names) {
			String path = null;
			//华数
			if(name.equals("wasu")){
				path = "/config/snmp/mibnode/wasu_mibnode.xml";
				addFileInfo(new File(MibNodeManager.class.getResource(path).toURI()),classContext);
			}/* else if (name.equals("peng")){//鹏博士
				path = "/config/snmp/mibnode/peng_mibnode.xml";
				addFileInfo(new File(MibNodeManager.class.getResource(path).toURI()),classContext);
			} else if (name.equals("zchuan")){//中传
				path = "/config/snmp/mibnode/zchuan_mibnode.xml";
				addFileInfo(new File(MibNodeManager.class.getResource(path).toURI()),classContext);
			}*/
		}
	}
	
	private void addFileInfo(File file, final Class<?>[] classContext){
		context = XStreamUtil.fromXML(MibNodeContext.class, file.toString(), classContext);
		final List<MibNodeCollection> collection = context.getCollectionList();
		for (MibNodeCollection mibNodeCollection : collection) {
			final List<MibNode> list = mibNodeCollection.getMibNodeList();
			if (list == null || list.isEmpty()) {
				continue;
			}
			//对应一项
			final String item = mibNodeCollection.getItem();
			final boolean isVector = mibNodeCollection.isVector();
			final Map<String, MibNode> tmp = new LinkedHashMap<String, MibNode>();
			for (MibNode node : list) {
				node.setOid(root + node.getOid());
				tmp.put(node.getName(), node);
				nameMap.put(node.getName(), node);
			}
			itemMap.put(item, tmp);
			isVectorMap.put(item, Boolean.valueOf(isVector));
		}
	}

	/**
	 * item 下某个name 的oid
	 * 
	 * @param item
	 *            String
	 * @param name
	 *            String
	 * @return String[]
	 */
	public String[] getOidbyName(final String item, final String... name) {
		final Map<String, MibNode> tmp = itemMap.get(item);
		final List<String> oidList = new ArrayList<String>();
		for (String each : name) {
			if (tmp.get(each) == null) {
				continue;
			}
			oidList.add(tmp.get(each).getOid());
		}
		if (oidList.isEmpty()) {
			return new String[0];
		}
		return oidList.toArray(new String[oidList.size()]);
	}

	/**
	 * 根据ITEM 获取 oid list：从配置文件中获取root oid
	 * 
	 * @param item
	 *            String
	 * @return String[]
	 */
	public String[] getNameByItem(final String item) {
		final Map<String, MibNode> tmp = itemMap.get(item);
		final Set<String> relativeNameList = tmp.keySet();
		return relativeNameList.toArray(new String[relativeNameList.size()]);
	}

	/**
	 * 根据ITEM 获取 oid list：从配置文件中获取root oid
	 * 
	 * @param item
	 *            String
	 * @return String[]
	 */
	public String[] getOidByItem(final String item) {
		try {
			final Map<String, MibNode> tmp = itemMap.get(item);
			final List<MibNode> relativeOidList = new ArrayList<MibNode>(tmp
					.values());
			if (relativeOidList.isEmpty()) {
				return new String[0];
			}
			String[] oids = new String[relativeOidList.size()];
			for (int i = 0; i < relativeOidList.size(); i++) {
				oids[i] = relativeOidList.get(i).getOid();
			}
			return oids;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error("EE", e);
		}
		return new String[0];
	}

	/**
	 * 根据ITEM获取 oid类型 矢量或者标量
	 * 
	 * @param item
	 *            String
	 * @return boolean
	 */
	public boolean isVectorByItem(final String item) {
		Assert.notNull(item);
		return isVectorMap.get(item);
	}

	/**
	 * 需要节点类型 无法解决重名问题
	 * 
	 * @param item
	 *            String
	 * @param name
	 *            String
	 * @return MibNode
	 */
	public MibNode getNodeByName(String item, String name) {
		// return nameMap.get(name);
		Map<String, MibNode> map = itemMap.get(item);
		if (map == null) {
			return null;
		}
		return map.get(name);

		// return itemMap.get(name);
	}

	/**
	 * @param item
	 *            String
	 * @return Map<String,MibNode>
	 */
	public Map<String, MibNode> getNodeByItem(String item) {
		return itemMap.get(item);
	}

	/**
	 * test
	 * 
	 * @param args
	 *            String[]
	 * @throws Exception
	 *             Exception
	 */
	public static void main(final String[] args) throws Exception {
		SnmpTempManager.getInstance().init();
		String[] oids = MibNodeManager.getInstance().getOidByItem("example");
		LOG.info(Arrays.asList(oids));
	}

}
