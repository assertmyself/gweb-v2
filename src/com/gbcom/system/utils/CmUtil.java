/**
 * @(#)CmUtil.java      07/10
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.system.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 系统工具类，封装静态util方法
 * 
 * 工具类比较混乱，历史遗留
 * 
 * 
 * 公共文件，，修改需要添加注释
 * 
 * @author SunYanzheng
 * @date 上午9:44:13
 * @version v1.0.0
 * @see CmUtil
 */
public class CmUtil {
	/** 原子计数器 * */
	@SuppressWarnings("unused")
	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	/**
	 * ip to ipv6
	 * 
	 * @param xip
	 *            srcIP
	 * @return ipv6的ip
	 */
	public static String convertIpToIpv6(String xip) {
		String ip = xip.replace(",", ".");
		String[] ips = ip.split("\\.");
		if (ips.length == 16) {
			return ip;
		}

		String ipv6 = "0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0";
		return ip + ipv6.substring(ips.length * 2);
	}

	/**
	 * 根据ip地址计算出long型的数据
	 * 
	 * @param strIP
	 *            ip地址
	 * @return long型的数据
	 */
	public static long ip2Long(String strIP) {
		long[] ip = new long[4];

		// 先找到IP地址字符串中.的位置
		int position1 = strIP.indexOf(".");
		int position2 = strIP.indexOf(".", position1 + 1);
		int position3 = strIP.indexOf(".", position2 + 1);

		// 将每个.之间的字符串转换成整型
		ip[0] = Long.parseLong(strIP.substring(0, position1));
		ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIP.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @param strIp
	 *            strIp
	 * @return omtID
	 */
	public static int ip2Int(String strIp) {
		String[] parts = strIp.split("\\.");
		if (parts == null || parts.length != 4) {
			return -1;
		}

		long[] ip = new long[4];
		long omtID;
		try {
			ip[0] = Long.parseLong(parts[0]);
			ip[1] = Long.parseLong(parts[1]);
			ip[2] = Long.parseLong(parts[2]);
			ip[3] = Long.parseLong(parts[3]);
			omtID = (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
		} catch (ArrayIndexOutOfBoundsException e) {
			return (int) Math.random() * 100000;
		}
		return (int) omtID;
	}

	/**
	 * 将10进制整数形式转换成127.0.0.1形式的IP地址
	 * 
	 * @param longIP
	 *            10进制整数形式转
	 * 
	 * 
	 * @return 转换成127.0.0.1形式的IP地址
	 */
	public static String long2IP(long longIP) {
		StringBuffer sb = new StringBuffer("");

		// 直接右移24位
		sb.append(String.valueOf(longIP >>> 24));
		sb.append(".");

		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longIP & 0x000000FF));
		return sb.toString();
	}

	/**
	 * 判断子网掩码参数值是否合法，符合1+0+的模式
	 * 
	 * 
	 * 
	 * 
	 * @param strNetMask
	 *            子网掩码参数
	 * @return 是否合法
	 */
	public static boolean checkNetMaskValid(String strNetMask) {
		return false;
	}

	/**
	 * 检查两个IP地址在指定的子网掩码是否在同一个子网中
	 * 
	 * @param strIpAddr1
	 *            strIpAddr1
	 * @param strIpAddr2
	 *            strIpAddr2
	 * @param strNetMask
	 *            strNetMask
	 * @return 检查两个IP地址在指定的子网掩码是否在同一个子网中
	 */
	public static boolean checkNetSegmentValid(String strIpAddr1,
			String strIpAddr2, String strNetMask) {
		InetAddress net1 = null;
		InetAddress net2 = null;
		InetAddress netMask = null;
		byte[] value1 = null;
		byte[] value2 = null;
		byte[] valueMask = null;

		try {
			net1 = InetAddress.getByName(strIpAddr1);
			net2 = InetAddress.getByName(strIpAddr2);
			netMask = InetAddress.getByName(strNetMask);

			value1 = net1.getAddress();
			value2 = net2.getAddress();
			valueMask = netMask.getAddress();

			if (value1.length != 4 || value2.length != 4
					|| valueMask.length != 4) {
				return false;
			}

			for (int i = 0; i < 4; i++) {
				if ((value1[i] & valueMask[i]) != (value2[i] & valueMask[i])) {
					return false;
				}
			}

			return true;
		} catch (UnknownHostException e) {
			return false;
		}
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @param ip1
	 *            ip1
	 * @param mask1
	 *            mask1
	 * @param ip2
	 *            ip2
	 * @param mask2
	 *            mask2
	 * @return boolean
	 */
	public static boolean checkNetSegmentSame(String ip1, String mask1,
			String ip2, String mask2) {
		try {
			InetAddress net1 = null;
			InetAddress netMask1 = null;

			net1 = InetAddress.getByName(ip1);
			netMask1 = InetAddress.getByName(mask1);

			byte[] value1 = net1.getAddress();
			byte[] valueMask1 = netMask1.getAddress();

			if (value1.length != 4 || valueMask1.length != 4) {
				return false;
			}

			InetAddress net2 = InetAddress.getByName(ip2);
			InetAddress netMask2 = InetAddress.getByName(mask2);
			byte[] value2 = net2.getAddress();
			byte[] valueMask2 = netMask2.getAddress();

			if (value2.length != 4 || valueMask2.length != 4) {
				return false;
			}

			int i = 0;
			for (i = 0; i < 4; i++) {
				if ((value1[i] & valueMask1[i]) != (value2[i] & valueMask2[i])) {
					break;
				}
			}

			if (i == 4) {
				return true;
			}

			return false;
		} catch (UnknownHostException e1) {
			return false;
		}
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @param beginIp1
	 *            beginIp1
	 * @param endIp1
	 *            endIp1
	 * @param beginIp2
	 *            beginIp2
	 * @param endIp2
	 *            endIp2
	 * @return boolean
	 */
	public static boolean checkIpSegConflict(String beginIp1, String endIp1,
			String beginIp2, String endIp2) {
		long b1 = CmUtil.ip2Long(beginIp1);
		long b2 = CmUtil.ip2Long(beginIp2);
		long e1 = CmUtil.ip2Long(endIp1);
		long e2 = CmUtil.ip2Long(endIp2);
		if ((b1 >= b2 && b1 <= e2) || (b2 >= b1 && b2 <= e1)) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @param beginIp
	 *            beginIp
	 * @param endIp
	 *            endIp
	 * @return boolean
	 */
	public static boolean checkBeginEndIpValid(String beginIp, String endIp) {
		long ip1 = ip2Long(beginIp);
		long ip2 = ip2Long(endIp);

		return !(ip1 >= ip2);
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @param resvIp
	 *            resvIp
	 * @param beginIp
	 *            beginIp
	 * @param endIp
	 *            endIp
	 * @return boolean
	 */
	public static boolean checkResvIpValid(String resvIp, String beginIp,
			String endIp) {
		long ip1 = ip2Long(beginIp);
		long ip2 = ip2Long(endIp);

		long ip = ip2Long(resvIp);

		return !(ip < ip1 || ip > ip2);
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @param time
	 *            time
	 * @return dateToString
	 */
	public static String dateToString(Date time) {
		// pujing修改
		return dateToString(time, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @param time
	 *            time
	 * @param timeFormat
	 *            timeFormat
	 * @return dateToString
	 */
	public static String dateToString(Date time, String timeFormat) {
		if (time == null || time.getTime() <= 0) {
			return "";
		} else {
			return new SimpleDateFormat(timeFormat).format(time);
		}
	}

	/**
	 * 将字符串转化成MAC形式
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public static String strToMac(String s) {
		if (s.length() != 12) {
			return s;
		}
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			result += s.charAt(i++);
			result += s.charAt(i) + ":";
		}

		return result.substring(0, result.lastIndexOf(":"));
	}

	/**
	 * ascllToMac
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String ascllToMac(String str) {
		StringBuffer sb = new StringBuffer();
		str = str.substring(3, str.length() - 2);
		for (String s : str.split("\\.")) {
			sb.append((char) Integer.parseInt(s));
		}
		return sb.toString();
	}

	/**
	 * macToAscll
	 * 
	 * @param str
	 *            String
	 * @param index
	 *            String
	 * @return String
	 */
	public static String macToAscll(String str, String index) {
		str = str.toUpperCase();
		StringBuffer sb = new StringBuffer();
		sb.append(".17.");
		for (char c : str.toCharArray()) {
			sb.append(new Integer(c));
			sb.append(".");
		}
		sb.append(index);
		String macIndex = sb.toString();
		return macIndex.substring(0, macIndex.length() - 1);
	}

	/**
	 * 根据站点位置信息，获取热点信息，热点名称是唯一的。
	 * 
	 * @param location
	 *            位置信息 宝鸡-丰县-刘官镇-流管站
	 * @return 流管站
	 */
	public static String subHotspotStr(String location) {

		// 截取最后一位 站点信息
		String[] lcts = location.split("-");
		String hotspot = "";
		for (int i = 3; i < lcts.length; i++) {
			if (i == lcts.length - 1) {
				hotspot += lcts[i];
			} else {

				hotspot += lcts[i] + "-";
			}
		}

		return hotspot;

	}

	/**
	 * MD5
	 * @param s
	 *            String
	 * @return String
	 */
	//off checkstyle
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 判断路径下是否有对应文件
	 * 
	 * @param srcPath
	 *            srcPath
	 * @param fileName
	 *            fileName
	 * @return file
	 */
	public static File searchFile(String srcPath, String fileName) {
		File dir = new File(srcPath);
		File[] files = dir.listFiles();

		if (files == null) {
			return null;

		}

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				// 扫描子目录，可以不进行
				searchFile(files[i].getAbsolutePath(), fileName);
			} else {
				// 找到文件
				if (files[i].getName().equals(fileName)) {
					return files[i];
				}
			}
		}
		return null;
	}

	/**
	 * 读取属性文件
	 * 
	 * @param fileUrl
	 *            文件路径
	 * @return 文件内容
	 * @throws IOException
	 *             IOException
	 */
	public static Map<String, String> readPropertyFile(String fileUrl)
			throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		File file = new File(fileUrl);
		if (file.exists() && file.isFile()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				Properties p = new Properties();
				p.load(inputStream);
				Set<Object> keySet = p.keySet();
				for (Object object : keySet) {
					String property = p.getProperty((String) object);
					map.put((String) object, property);
				}
				return map;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		} else {
			return new HashMap<String, String>();
		}

	}

	/***
	 * 将指定路径下的文件读取 并保持到List中
	 * 
	 * @param srcPath
	 *            srcPath
	 * @param list
	 *            list
	 */
	public static void scanDir(String srcPath, List<File> list) {
		File dir = new File(srcPath);
		File[] files = dir.listFiles();

		if (files == null) {
			return;
		}

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				// 扫描子目录，可以不进行
				// scanDir(files[i].getAbsolutePath(), list);
			} else {
				list.add(files[i]);
			}
		}
	}

	/**
	 * @param value
	 *            String
	 * @return String
	 */
	public static String ascii2mac(String value) {
		String mac = value;
		if (!value.matches(RegexConst.REGEX_MAC)) {
			byte[] bytes = TransformUtil.ascii2bytes(value);
			mac = TransformUtil.getMacString(bytes);
		}
		return mac;

	}

	/**
	 * 获取服务器所有IP。。
	 * 
	 * @return LinkedHashMap<String,String>
	 */
	public static LinkedHashMap<String, String> getLocalAddress() {

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					String ip = ips.nextElement().getHostAddress();
					if (!ip.equals("127.0.0.1")
							&& ip.matches(RegexConst.REGEX_IP)) {
						valueMap.put(ip, ip);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("get ip failed", e);
		}
		return valueMap;

	}

}
