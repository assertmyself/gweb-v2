/**
 * Copyright 2009-2010 GBCOM Co.,Ltd. All rights reserved.
 * GB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gbcom.system.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gbcom.op.util.Assert;
import com.hc.core.utils.XMLGregorianCalendarConversionUtils;

/**
 * 服务端日期处理的类
 * 
 * @author duanxiongwen
 * @version 1.50.00, 2010-3-25
 */
public class DateUtil {
	/**
	 * 日志记录器
	 */
	public static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 给指定的日期增加指定的时间
	 * 
	 * @param date
	 *            日期
	 * @param field
	 *            如#Calendar.MONTH #Calendar.DAY
	 * @param amount
	 *            数目,如1 加一天 -1减一天
	 * @return 增加指定时间的日期
	 */
	public static Date add(Date date, int field, int amount) {
		Calendar calendar = getCalendar(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 将传入的日期转换成今天的时间
	 * 
	 * @param date
	 *            传入的日期
	 * @return 返回今天的时间
	 */
	public static Date getTodayTime(Date date) {
		Calendar cNow = getCalendar(new Date());

		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.YEAR, cNow.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, cNow.get(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_YEAR, cNow.get(Calendar.DAY_OF_YEAR));

		return calendar.getTime();
	}

	/**
	 * 返回指定日期是一周中的第几天
	 * 
	 * @param date
	 *            指定日期
	 * @return 返回指定日期是一周中的第几天
	 */
	public static int getWeek(Date date) {
		Calendar calendar = getCalendar(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Date转换成Calendar
	 * 
	 * @param date
	 *            Date
	 * @return Calendar
	 */
	public static Calendar getCalendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 两个日期相减,date1-date2,取得相差几天
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 取得相差几天
	 */
	public static int sub(Date date1, Date date2) {
		Assert.notNull(date1);
		Assert.notNull(date2);
		long tem = date1.getTime() - date2.getTime();
		return Integer.parseInt(String.valueOf(tem / (24 * 60 * 60 * 1000)));
	}

	/**
	 * 合并时间,
	 * 
	 * @param date
	 *            年月日
	 * @param time
	 *            时分秒
	 * @return 合并后的日期
	 */
	@SuppressWarnings("deprecation")
	public static Date mergeDate(Date date, Date time) {
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.MINUTE, time.getMinutes());
		calendar.set(Calendar.HOUR_OF_DAY, time.getHours());
		return calendar.getTime();
	}

	/**
	 * 用默认风格把时间格式化成<code>yyyy-MM-dd HH：mm:ss</code> 的时间字符串
	 * 
	 * @author zhaishixi 2013-09-18
	 * @param date
	 *            时间字符串
	 * @return date
	 */
	public static String format(Object date) {
		if (date != null) {
			return DateFormat.getDateTimeInstance().format(date);
		} else {
			return null;
		}
	}

	/**
	 * 用默认风格把时间格式化成制定格式如<code>（ yyyy-MM-dd HH：mm:ss）</code> 的时间字符串
	 * 
	 * @author zhaishixi 2013-09-26
	 * @param date
	 *            时间字符串
	 * @param pattern
	 *            the pattern describing the date and time format
	 * @return date
	 */
	public static String format(Object date, String pattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		if (date != null) {
			return sdf.format(date);
		} else {
			return null;
		}
	}

	/**
	 * 用默认风格把date(Object 类型)按指定格式<code>pattern</code>格式化Date类型
	 * 
	 * @author zhaishixi 2013-09-26
	 * @param date
	 *            时间字符串 * @param pattern the pattern describing the date and
	 *            time format
	 * @return date
	 */
	public static Date parse(Object date, String pattern) {
		Date fd = new Date();
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			fd = sdf.parse(date.toString());
		} catch (ParseException e) {
			LOG.error("parse date failed!", e);
		}
		return fd;
	}

	/**
	 * 用默认风格把date(Object 类型)按指定格式<code>pattern</code>格式化Date类型
	 * 
	 * @param date
	 *            要转换的日期
	 * @param pattern
	 *            格式
	 * @return 转换后的日期
	 */
	public static Date parseDate(Object date, String pattern) {
		Date d = new Date();
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String format = sdf.format(date);
		try {
			d = sdf.parse(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 计算出离<code>beginDate日期data</code>天的日期. <li>若datas小于0表示当前日期之前data天. <li>
	 * 若datas大于0表当前日期之后data天.
	 * 
	 * @author zhaishixi 2013-09-25
	 * @param beginDate
	 *            要计算的天数
	 * @param data
	 *            间隔
	 * @return 得到日期 格式：<code>yyyy-MM-dd HH:mm:ss</code>
	 */
	public static Date getDate(Date beginDate, int data) {
		Calendar beginCal = Calendar.getInstance();
		beginCal.setTimeInMillis(beginDate.getTime());
		GregorianCalendar calendar = new GregorianCalendar(beginCal
				.get(Calendar.YEAR), beginCal.get(Calendar.MONTH), beginCal
				.get(Calendar.DATE), beginCal.get(Calendar.HOUR_OF_DAY),
				beginCal.get(Calendar.MINUTE), beginCal.get(Calendar.SECOND));
		calendar.add(GregorianCalendar.DATE, data);
		return new Date(calendar.getTimeInMillis());
	}

	/**
	 * 将时间（单位为秒） 转化为 ：时 ：分 ： 秒格式
	 * 
	 * 该time 并非 {@link Date#getTime()} ,单位为秒
	 * 
	 * @param time
	 *            long
	 * @return String
	 */
	public static String valueOfSecond(long time) {
		long h = time / 3600;
		long m = (time % 3600) / 60;
		long s = (time % 3600) % 60;
		String value = h + "Basic_hour" + ":" + m + "Basic_min" + ":" + s
				+ "Basic_sec";
		return value;

	}

	/**
	 * 将时间（单位为分钟） 转化为 ：天：时 ：分格式
	 * 
	 * 该time 并非 {@link Date#getTime()} ,单位为分钟
	 * 
	 * @param time
	 *            long
	 * @return String
	 */
	public static String valueOfMinute(long time) {
		long d = time / (24 * 60);
		long h = (time % (24 * 60)) / 60;
		long m = (time % (24 * 60)) % 60;
		String value = d + "Basic_day" + ":" + h + "Basic_hour" + ":" + m
				+ "Basic_min";
		return value;

	}

	/**
	 * 返回当前月前n个月或者后n个月的第一天
	 * 
	 * @param num
	 *            n个月 isPositive 为true表示前，为false表示后
	 * @param isPositive
	 *            +/-
	 * @return n个月第一天
	 */
	@SuppressWarnings("deprecation")
	public static Date getFirstDayOfMonth(int num, boolean isPositive) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.getTime().getYear();
		int month = calendar.getTime().getMonth();
		if (isPositive) {
			month = month + num;
		} else {
			month = month - num;
		}
		int day = 1;
		if (month < 0) {
			year = year - 1;
			month = 11;
		} else if (month > 12) {
			year = year + 1;
			month = 0;
		}
		date = new Date(year, month, day);
		return date;
	}

	// ----------------非通用方法，谨慎使用----------------//
	/**
	 * 时间格式转换--cxf不识别java.sql.Timestamp
	 * 
	 * @param orgTime
	 *            java.sql.Timestamp
	 * @return XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar timeToXmlDate(java.sql.Timestamp orgTime) {
		if (orgTime != null) {
			return XMLGregorianCalendarConversionUtils
					.asXMLGregorianCalendar(new Date(orgTime.getTime()));
		}
		return null;
	}

	/**
	 * 将xmldate转为timestamp
	 * 
	 * @param cal
	 *            XMLGregorianCalendar
	 * @return java.sql.Timestamp
	 */
	public static java.sql.Timestamp xmlDate2Time(XMLGregorianCalendar cal) {
		if (cal != null) {
			return new java.sql.Timestamp(XMLGregorianCalendarConversionUtils
					.asDate(cal).getTime());
		}
		return null;
	}

	/**
	 * 将Date类转换为XMLGregorianCalendar
	 * 
	 * @param date
	 *            Date
	 * @return XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar dateToXmlDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DatatypeFactory dtf = null;
		try {
			dtf = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
		}
		XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
		dateType.setYear(cal.get(Calendar.YEAR));
		// 由于Calendar.MONTH取值范围为0~11,需要加1
		dateType.setMonth(cal.get(Calendar.MONTH) + 1);
		dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
		dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
		dateType.setMinute(cal.get(Calendar.MINUTE));
		dateType.setSecond(cal.get(Calendar.SECOND));
		return dateType;
	}

	/**
	 * 将XMLGregorianCalendar转换为Date
	 * 
	 * @param cal
	 *            XMLGregorianCalendar
	 * @return Date
	 */
	public static Date xmlDate2Date(XMLGregorianCalendar cal) {
		return cal.toGregorianCalendar().getTime();
	}

	/**
	 * 获取工作时间：8:30 - 17:30
	 * 
	 * @param date
	 *            String
	 * @return String[]
	 * @throws ParseException
	 *             ParseException
	 */
	public static String[] getWorkDate(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cd = Calendar.getInstance();
		cd.setTime(simpleDateFormat.parse(date));
		cd.add(Calendar.HOUR, 7);
		cd.add(Calendar.MINUTE, 30);

		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String[] workDate = new String[2];
		workDate[0] = simpleDateFormat.format(cd.getTime());

		cd.add(Calendar.HOUR, 10);
		workDate[1] = simpleDateFormat.format(cd.getTime());
		return workDate;
	}

	/**
	 * 获取当天的开始时间
	 * 
	 * @param date
	 *            指定日期
	 * @return 当前开始日期
	 */
	public static Date getCurDayStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取本周的开始时间
	 * 
	 * @param date
	 *            指定日期
	 * @return 本周开始日期
	 */
	public static Date getCurWeekStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}

	/**
	 * 获取当月的开始时间
	 * 
	 * @param date
	 *            指定日期
	 * @return 当月开始日期
	 */
	public static Date getCurMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当年的开始日期
	 * 
	 * @param date
	 *            指定日期
	 * @return 当年的开始日期
	 */
	public static Date getCurYearStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	// off checkstyle
	public static void main(String[] args) {
		// //
		// System.out.println(format(parse("20131111235959","yyyyMMddHHmmss")));
		//		
		// // LOG.info("DATA="+parse("TTT20131111235959","dsdsdsd"));
		// long var = 10201;
		// System.out.println(valueOfMinute(var));
		// System.out.println(valueOfSecond(var));
		//		
		//		
		//
		// Timestamp curTime = new Timestamp(System.currentTimeMillis());
		// XMLGregorianCalendar calendar = timeToXmlDate(curTime);
		//
		// System.out.println("calendar = " + calendar);
		//
		// Timestamp timestamp = xmlDate2Time(calendar);
		// System.out.println("timestamp = " +
		// DateTimeHelper.formatTimeGBK(timestamp));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String time = df.format(getCurWeekStart(new Date()));
		System.out.println(time);

	}

}
