package com.gbcom.common.im;

import com.gbcom.system.utils.TransformUtil;

/**
 * Created with IntelliJ IDEA.
 * User: fengerhu
 * Date: 14-7-31
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class IMUtil {
    /**
     * SN占位符及正则替换表达式
     */
    private static final String SN_PLACE_HOLDER = "{SN}";
    private static final String SN_PLACE_HOLDER_REGEX = "\\{SN\\}";

    /**
     * CRC32占位符及正则替换表达式
     */
    private static final String CRC32_PLACE_HOLDER = "{CRC32}";
    private static final String CRC32_PLACE_HOLDER_REGEX = "\\{CRC32\\}";


    /**
     * 将占位符，提供成crc32校验
     * @param atCmdHex atCmdHex
     * @param seqNum seqNum
     * @return String
     */
    public static String replaceSNCRC32(String atCmdHex, int seqNum) {
        //置入SN
        if (atCmdHex.contains(SN_PLACE_HOLDER)) {
            //生成序列号
            String seqNumHex = Integer.toHexString(seqNum).toUpperCase();
            seqNumHex = "0000".substring(seqNumHex.length()) + seqNumHex;
            atCmdHex = atCmdHex.replaceAll(SN_PLACE_HOLDER_REGEX, seqNumHex);
        }
        //置入CRC32
        if (atCmdHex.contains(CRC32_PLACE_HOLDER)) {
            //CRC处理
            //默认值替换
            String tempAtCmdHex = atCmdHex.replaceAll(CRC32_PLACE_HOLDER_REGEX, "00000000");

            //生成CRC32
            java.util.zip.CRC32 crc321 = new java.util.zip.CRC32();
            crc321.update(TransformUtil.hexStringToBytes(tempAtCmdHex));
            long crc32Value = crc321.getValue();
            byte[] bytes = TransformUtil.long2byteArray(crc32Value);
            String crc32Hex = Integer.toHexString(TransformUtil.byteArray2Int(new byte[]{bytes[4], bytes[5], bytes[6], bytes[7]}));
            crc32Hex = "00000000".substring(crc32Hex.length()) + crc32Hex.toUpperCase();

            //置入CRC32校验码
            atCmdHex = atCmdHex.replaceAll(CRC32_PLACE_HOLDER_REGEX, crc32Hex);
        }
        return atCmdHex;
    }
}
