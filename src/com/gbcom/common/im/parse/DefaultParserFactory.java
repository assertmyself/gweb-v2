package com.gbcom.common.im.parse;

import com.gbcom.common.im.parse.alarm.IAlarmParser;
import com.gbcom.system.utils.SpringContextUtil;

/**
 * Created with IntelliJ IDEA. User: fengerhu Date: 14-7-30 Time: 上午10:45 To
 * change this template use File | Settings | File Templates.
 */
public class DefaultParserFactory extends ParserFactory {

	private static DefaultParserFactory instance = new DefaultParserFactory();

	/**
	 * DefaultParserFactory
	 * @return DefaultParserFactory
	 */
	public static DefaultParserFactory getInstance() {
		return instance;
	}

	@Override
	public IAlarmParser getAlarmParser(String className) {
		return (IAlarmParser) SpringContextUtil.getBean(className);
	}

}
