package com.gbcom.system.utils;

/**
 * KMP实现类:用于字符串的模糊查找 可用于cpe列表的查找
 * 
 * @author SunYanzheng
 * @date 上午11:17:00
 * @version v1.0.0
 * @see KMP
 */
public class KMP {
	/**
	 * getNext方法
	 * 
	 * @param b
	 *            char[]
	 * @return char[]
	 */
	private static int[] getNext(char[] b) {
		int i, j;
		int len = b.length;
		int[] next = new int[len];
		next[0] = 0;

		for (j = 1; j < len; j++) {

			i = next[j - 1];

			while ((i > 0) && (b[i - 1] != b[j - 1])) {

				i = next[i - 1];
			}
			next[j] = i + 1;
		}
		return next;
	}

	/**
	 * KMP算法
	 * 
	 * @param msg
	 *            String
	 * @param pattern
	 *            String
	 * @return boolean
	 */
	public static boolean kmp(String msg, String pattern) {
		if (msg == null || pattern == null) {
			return false;
		}
		int plen = pattern.length();
		int tlen = msg.length();
		char[] p = pattern.toCharArray();
		char[] t = msg.toCharArray();
		int[] n = getNext(t);

		int i = 1, j = 1, sum = 0;
		while (i <= plen && j <= tlen) {
			if (j == 0 || p[i - 1] == t[j - 1]) {
				if (j != 0) {
					sum++;
				}
				i++;
				j++;

			} else {
				sum++;
				j = n[j - 1];
			}
			if (j > tlen) {
				break;
			}
		}

		return j > tlen;

	}

	/**
	 * test methodl
	 * 
	 * @param args
	 *            String【】
	 */
	public static void main(String[] args) {
		boolean result = kmp("孙", "孙悟空");
		System.out.println("result : " + result);

	}

}
