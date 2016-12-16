package com.gbcom.common.im.parse.alarm;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-28
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
public interface IAlarmParser {
    /**
     * 与MCU交互
     * 1、解析MCU数据
     * 2、返回MCU对应的响应
     * @param device Device
     * @param bytes byte
     * @return 响应信息 byte
     */
    public byte[] parse(Object device, byte[] bytes);
}
