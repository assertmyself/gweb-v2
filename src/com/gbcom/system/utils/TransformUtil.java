package com.gbcom.system.utils;

import java.io.UnsupportedEncodingException;

/**
 * 二进制数据转化，提供byte byte[] 等流转化工具类
 * 
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-15,上午11:48:19
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.utils.TransformUtil
 */
public class TransformUtil {

	/**
	 * int 转 byte数据
	 * @param num int
	 * @return  byte[]
	 */
    public static byte[] int2byteArray(int num) {
        byte[] result = new byte[4];
        result[0] = (byte) ((num >>> 24) & 0xFF);//取最高8位放到0下标
        result[1] = (byte) ((num >>> 16) & 0xFF);//取次高8为放到1下标
        result[2] = (byte) ((num >>> 8) & 0xFF); //取次低8位放到2下标
        result[3] = (byte) ((num) & 0xFF);      //取最低8位放到3下标
        return result;
    }

    /**
     *  将byte数组bRefArr转为一个整数,字节数组的低位是整型的高字节位
     * @param bRefArr byte[]
     * @return int
     */
    public static int byteArray2Int(byte[] bRefArr) {
        int iOutcome = 0;
        byte bLoop;

        for (int i = 0; i < bRefArr.length; i++) {
            bLoop = bRefArr[i];
            iOutcome += (bLoop & 0xFF) << (8 * (3 - i));
        }
        return iOutcome;
    }

    /**
     * long转byte[]
     * @param num long
     * @return  byte[] 
     */
    public static byte[] long2byteArray(long num) {
        byte[] result = new byte[8];
        result[0] = (byte) ((num >>> 56) & 0xFF);//取最高8位放到0下标
        result[1] = (byte) ((num >>> 48) & 0xFF);//取次高8为放到1下标
        result[2] = (byte) ((num >>> 40) & 0xFF); //取次低8位放到2下标
        result[3] = (byte) ((num >>> 32) & 0xFF); //取最低8位放到3下标
        result[4] = (byte) ((num >>> 24) & 0xFF);//取最高8位放到0下标
        result[5] = (byte) ((num >>> 16) & 0xFF);//取次高8为放到1下标
        result[6] = (byte) ((num >>> 8) & 0xFF); //取次低8位放到2下标
        result[7] = (byte) ((num) & 0xFF);      //取最低8位放到3下标
        return result;
    }

    /**
     *  将byte数组bRefArr转为一个long整数,字节数组的低位是整型的高字节位
     * @param bRefArr byte[] 
     * @return long
     */
    public static long byteArray2Long(byte[] bRefArr) {
        long iOutcome = 0;
        byte bLoop;

        for (int i = 0; i < bRefArr.length; i++) {
            bLoop = bRefArr[i];
            iOutcome += (bLoop & 0xFF) << (8 * (7 - i));
        }
        return iOutcome;
    }

    /**
     * byte数组转short
     * @param bRefArr byte[]
     * @return short
     */
    public static short byteArray2Short(byte[] bRefArr) {
        short iOutcome = 0;
        byte bLoop;

        for (int i = 0; i < bRefArr.length; i++) {
            bLoop = bRefArr[i];
            iOutcome += (bLoop & 0xFF) << (8 * (1 - i));
        }
        return iOutcome;
    }

    /**
     * 根据mac字符串获取字节
     *
     * @param mac mac字符串
     * @return byte[]
     */
    public static byte[] getMacBytes(String mac) {
        byte[] macBytes = new byte[6];
        String[] strArr = mac.split(":");

        for (int i = 0; i < strArr.length; i++) {
            int value = Integer.parseInt(strArr[i], 16);
            macBytes[i] = (byte) value;
        }
        return macBytes;
    }

    /**
     * 根据mac字节获取字符串
     *
     * @param mac mac字节
     * @return String
     */
    public static String getMacString(byte[] mac) {
        String value = "";
        for (int i = 0; i < mac.length; i++) {
            String sTemp = Integer.toHexString(0xFF & mac[i]);
            sTemp = "00".substring(sTemp.length()) + sTemp;
            value = value + sTemp + ":";
        }
        return value.substring(0, value.lastIndexOf(":")).toUpperCase();
    }

    /**
     * 根据mac字节获取字符串
     *
     * @param ip ip字节
     * @return String
     */
    public static String getIpString(byte[] ip) {
        String value = "";
        for (byte b : ip) {
            int b1 = 0xFF & b;
            value += b1 + ".";
        }
        return value.substring(0, value.lastIndexOf("."));
    }

    /**
     * 获取ip 字节数组
     * @param ip String
     * @return byte[]
     */
    public static byte[] getIpBytes(String ip) {

        byte[] ipBytes = new byte[4];
        String[] strArr = ip.split("[.]");

        for (int i = 0; i < strArr.length; i++) {
            int value = Integer.parseInt(strArr[i]);
            ipBytes[i] = (byte) value;
        }
        return ipBytes;
    }

    /**
     * 16进制支付成 转字节数组
     * @param hexString 16进制
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];
        int index = 0;
        while (index * 2 < hexString.length()) {
            String hex = hexString.substring(index * 2, index * 2 + 2);
            byte b = (byte) Integer.parseInt(hex, 16);
            bytes[index] = b;
            index++;
        }
        return bytes;
    }

    /**
     * 字节数组转16进制字符串
     * @param src byte[]
     * @return String
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    /**
     * 字符串转十六进制
     * @param str String
     * @return String
     */
    public static String str2HexString(String str){
        char[] chars = "0123456789ABCDEF".toCharArray(); 
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes(); 
        int bit; 
        for (int i = 0; i < bs.length; i++) { 
            bit = (bs[i] & 0x0f0) >> 4; 
            sb.append(chars[bit]); 
            bit = bs[i] & 0x0f; 
            sb.append(chars[bit]); 
        } 
        return sb.toString();  
    }

    /**
     * 16进制 转字符串
     * @param hexStr String
     * @return String
     */
    public static String hexStr2Str(String hexStr) { 

        String str = "0123456789ABCDEF"; 
        char[] hexs = hexStr.toCharArray(); 
        byte[] bytes = new byte[hexStr.length() / 2]; 
        int n; 
        for (int i = 0; i < bytes.length; i++) { 
            n = str.indexOf(hexs[2 * i]) * 16; 
            n += str.indexOf(hexs[2 * i + 1]); 
            bytes[i] = (byte) (n & 0xff); 
        } 
        return new String(bytes); 
    } 
    
    /**
     * 字节数组 转ascii码
     * @param src 字节数组
     * @return String
     */
    public static String bytes2Ascii(byte []src){
    	StringBuffer sb = new StringBuffer();
    	for(byte ea : src){
    		sb.append((char)ea);
    	}
    	
    	return sb.toString();
    }
    
    /**
     * ascii码 转字节数组
     * @param src String
     * @return byte[]
     */
    public static byte[] ascii2bytes(String src){
   	char[] carry = src.toCharArray();
    	byte [] bytes = new byte[carry.length];
    	for(int i=0;i<carry.length;i++){
    		bytes[i] = (byte) carry[i];
    	}
    	return bytes;
    }

    /**
     * test
     * @param args String
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException{
    	byte [] src = {0x28,0x51,0x32,0x08,(byte) 0xa5,0x2c};
    	String asc = bytes2Ascii(src);
    	byte[]aaa = ascii2bytes(asc);
    	String hexString  = bytesToHexString(src);
    	System.out.println(getMacString(src));
    	System.out.println(hexString);
    	
    	
    	System.out.println(str2HexString(asc));
    	System.out.println(hexStr2Str(str2HexString(asc)));
    }
}
