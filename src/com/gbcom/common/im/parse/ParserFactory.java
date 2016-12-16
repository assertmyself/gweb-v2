package com.gbcom.common.im.parse;

import com.gbcom.common.im.parse.alarm.IAlarmParser;


/**
 * Created with IntelliJ IDEA. User: fengerhu Date: 14-7-29 Time: 下午8:53 To
 * change this template use File | Settings | File Templates.
 */
public abstract class ParserFactory {

	/**
	 * getAlarmParser
	 * @param className String
	 * @return IAlarmParser
	 */
	public abstract IAlarmParser getAlarmParser(String className);
}
