package com.gbcom.system.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 加解密工具类
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-15,下午12:24:49
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.utils.SecurityUtil
 */
public class SecurityUtil {

    private static final byte[] k;
    private static final byte[] v;
    private static  final String c;

   static {
        char[] ac = new char[]{'A', 'E', 'S', '/', 'C', 'B', 'C', '/', 'N', 'o', 'P', 'a', 'd', 'd', 'i', 'n', 'g'};
        c = new String(ac);
        byte[] bytes = new byte[]{80, 112, 123, 123, 126, 118, 105, 104, 107, 104, 101, 100, 96, 27, 14, 2};
        byte byte0 = 24;
        int j = 0;
        while (j < bytes.length) {
            byte0 ^= 0xff & bytes[j];
            bytes[j] = byte0;
            j++;
        }
        v = bytes;
        bytes = new byte[]{112, 123, 123, 126, 118, 105, 104, 107, 104, 101, 100, 96, 27, 14, 2, 37};
        byte0 = 97;
        int i1 = -1 + bytes.length;
        while (i1 >= 0) {
            byte0 ^= 0xff & bytes[i1];
            bytes[i1] = byte0;
            i1--;
        }
        k = bytes;
    }

    /**
     * AES加密
     * @param bytes 源字节
     * @return byte[]
     */
    public static byte[] encrypt(byte[] bytes) {
        try {
            //第1个字节写数据开始位置
            int num = (bytes.length + 1) % 16;
            int newLength = bytes.length + 1 + 16 - num;
            byte[] newBytes = new byte[newLength];
            System.arraycopy(bytes, 0, newBytes, 17 - num, bytes.length);
            newBytes[0] = new Integer((17 - num) & 0xff).byteValue();

            Cipher cipher = Cipher.getInstance(c);
            SecretKeySpec secretkeyspec = new SecretKeySpec(k, c.substring(0, 3));
            IvParameterSpec parameterSpec = new IvParameterSpec(v);
            cipher.init(Cipher.ENCRYPT_MODE, secretkeyspec, parameterSpec);
            return cipher.doFinal(newBytes);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    /**
     * AES解密
     * @param bytes byte[]
     * @return byte[]
     */
    public byte[] decrypt(byte[] bytes) {
        try {
            Cipher cipher = Cipher.getInstance(c);
            SecretKeySpec secretkeyspec = new SecretKeySpec(k, c.substring(0, 3));
            IvParameterSpec parameterSpec = new IvParameterSpec(v);
            cipher.init(Cipher.DECRYPT_MODE, secretkeyspec, parameterSpec);
            byte[] newBytes = cipher.doFinal(bytes);
            int pos = newBytes[0]&0xff;
            byte[] res=new byte[newBytes.length-pos];
            System.arraycopy(newBytes,pos,res,0,newBytes.length-pos);
            return res;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    
    /**
     * 执行加密
     * @param s String
     * @return String
     */
	//off checkstyle
    public static final String MD5(String s) {
    	
        char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
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
            char[] str = new char[j * 2];
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
    
}
