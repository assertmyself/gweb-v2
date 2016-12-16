//off checkstyle
package com.gbcom.common.im.desc;

/**
 * 界面控件类型枚举类，用于界面显示
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public enum ControlType {
	INTTEXT {
		// 十进制整型数字
		public int intValue() {
			return INTTEXT_CTYPE;
		}
	},
	OXTEXT {
		// 十六进制整型数字
		public int intValue() {
			return OXTEXT_CTYPE;
		}
	},
	FLOATTEXT {
		// 浮点型数字
		public int intValue() {
			return FLOATTEXT_CTYPE;
		}
	},
	IPTEXT {
		// IP地址
		public int intValue() {
			return IPTEXT_CTYPE;
		}
	},
	LETTERTEXT {
		// 字母,只能输入字母，不能输入中文，标点，数字
		public int intValue() {
			return LETTERTEXT_CTYPE;
		}
	},
	COMBOBOX {
		// 下拉框
		public int intValue() {
			return COMBOBOX_CTYPE;
		}
	},
	SPINNER {
		// 微调控制
		public int intValue() {
			return SPINNER_CTYPE;
		}
	},
	COORDINATE {
		// 坐标
		public int intValue() {
			return COORDINATE_CTYPE;
		}
	},
	FILESELECT {
		// 文件选择对话框
		public int intValue() {
			return FILESELECT_CTYPE;
		}
	},
	MACTEXT {
		// MAC地址
		public int intValue() {
			return MACTEXT_CTYPE;
		}
	},
	NEWMACTEXT {
		public int intValue() {
			return NEWMACTEXT_CTYPE;
		}
	},
	ARRAY {
		// 数组
		public int intValue() {
			return ARRAY_CTYPE;
		}
	},
	IPV6TEXT {
		// IPV6地址
		public int intValue() {
			return IPV6TEXT_CTYPE;
		}
	},
	ARRAY_GROUP {
		// 数组，通过属性描述中的数组及结构项知道数组结构大小
		public int intValue() {
			return ARRAY_GROUP_CTYPE;
		}
	},
	TEXT {
		// 字符串输入控件，可以输入所有字符
		public int intValue() {
			return TEXT_CTYPE;
		}
	},
	COMBO_GROUP {
		// 枚举型数组，用于标识有五个属性项结构的数组
		public int intValue() {
			return COMBO_GROUP_CTYPE;
		}
	},
	ARRAY_RELATION {
		// 数组关系控件
		public int intValue() {
			return ARRAY_RELATION_CTYPE;
		}
	},
	PASSWORD {
		// 密码控件
		public int intValue() {
			return PASSWORD_CTYPE;
		}
	},
	OXSTRING {
		// 16进制字符串
		public int intValue() {
			return OXSTRING_CTYPE;
		}
	},
	NEWOXSTRING {
		public int intValue() {
			return NEWOXSTRING_CTYPE;
		}
	},
	TEXT_GROUP {
		// 文本数组
		public int intValue() {
			return TEXT_GROUP_CTYPE;
		}
	},
	TEXT2 {
		// 固定长度的文本串
		public int intValue() {
			return TEXT2_CTYPE;
		}
	},
	TIME_DATEONLY {
		public int intValue() {
			return TIME_DATEONLY_TYPE;
		}
	},
	PASSWORD2 {
		public int intValue() {
			return PASSWORD2_CTYPE;
		}
	},
	OXSTRING2 {
		public int intValue() {
			return PASSWORD_OXSTRING_CTYPE;
		}
	}

	;

	/**
	 * 十进制整型数字对应的int值
	 */
	private final static int INTTEXT_CTYPE = 1;

	/**
	 * 十六进制整型数字对应的int值
	 */
	private final static int OXTEXT_CTYPE = 2;

	/**
	 * 浮点型数字对应的int值
	 */
	private final static int FLOATTEXT_CTYPE = 3;

	/**
	 * IP地址对应的int值
	 */
	private final static int IPTEXT_CTYPE = 4;

	/**
	 * 字母对应的int值
	 */
	private final static int LETTERTEXT_CTYPE = 5;

	/**
	 * 下拉框对应的int值
	 */
	private final static int COMBOBOX_CTYPE = 6;
	/**
	 * 单选对应的int值
	 */
	private final static int RADIOBUTTON_CTYPE = 7;

	/**
	 * 复选对应的int值
	 */
	private final static int CHECKBOX_CTYPE = 8;

	/**
	 * 对应的int值
	 */
	private final static int SPINNER_CTYPE = 9;

	/**
	 * 坐标对应的int值
	 */
	private final static int COORDINATE_CTYPE = 10;

	/**
	 * 文件选择对话框对应的int值
	 */
	private final static int FILESELECT_CTYPE = 11;

	/**
	 * MAC地址对应的int值
	 */
	private final static int MACTEXT_CTYPE = 12;

	/**
	 * 数组对应的int值
	 */
	private final static int ARRAY_CTYPE = 13;

	/**
	 * 数组对应的int值，用于标识只有一个属性项结构的数组
	 */
	private final static int ARRAY_GROUP_CTYPE = 14;

	/**
	 * IPv6地址对应的int值
	 */
	private final static int IPV6TEXT_CTYPE = 18;

	/**
	 * 文本
	 */
	private final static int TEXT_CTYPE = 20;
	/**
	 * Combo数组
	 */
	private final static int COMBO_GROUP_CTYPE = 21;
	/**
	 * 数组关系控件，有控制该数组显示个数
	 */
	private final static int ARRAY_RELATION_CTYPE = 22;
	/**
	 * 密码控件
	 */
	private final static int PASSWORD_CTYPE = 23;

	/**
	 * 增加16进制字符串
	 */
	private final static int OXSTRING_CTYPE = 24;

	/**
	 * 增加 文本数组 类型
	 */
	private final static int TEXT_GROUP_CTYPE = 25;

	/**
     *
     */
	private final static int TEXT2_CTYPE = 26;

	/**
     *
     */
	private final static int TIME_DATEONLY_TYPE = 27;

	/**
	 * 最大长度为max-1的密码控件
	 */
	private final static int PASSWORD2_CTYPE = 28;
	private final static int PASSWORD_OXSTRING_CTYPE = 29;

	/**
     *
     */
	private final static int NEWMACTEXT_CTYPE = 30;
	private final static int NEWOXSTRING_CTYPE = 31;

	/**
	 * 根据int值获得对应的控件类型
	 * 
	 * @param ct
	 *            控件类型对应的int值
	 * 
	 * @return 控件类型
	 */
	public static ControlType getCT(int ct) {
		switch (ct) {
		case INTTEXT_CTYPE:
			return INTTEXT;
		case OXTEXT_CTYPE:
			return OXTEXT;
		case FLOATTEXT_CTYPE:
			return FLOATTEXT;
		case IPTEXT_CTYPE:
			return IPTEXT;
		case IPV6TEXT_CTYPE:
			return IPV6TEXT;
		case LETTERTEXT_CTYPE:
			return LETTERTEXT;
		case COMBOBOX_CTYPE:
			return COMBOBOX;
		case SPINNER_CTYPE:
			return SPINNER;
		case COORDINATE_CTYPE:
			return COORDINATE;
		case FILESELECT_CTYPE:
			return FILESELECT;
		case MACTEXT_CTYPE:
			return MACTEXT;
		case NEWMACTEXT_CTYPE:
			return NEWMACTEXT;
		case ARRAY_GROUP_CTYPE:
			return ARRAY_GROUP;
		case ARRAY_CTYPE:
			return ARRAY;
		case TEXT_CTYPE:
			return TEXT;
		case COMBO_GROUP_CTYPE:
			return COMBO_GROUP;
		case ARRAY_RELATION_CTYPE:
			return ARRAY_RELATION;
		case PASSWORD_CTYPE:
			return PASSWORD;
		case OXSTRING_CTYPE:
			return OXSTRING;
		case NEWOXSTRING_CTYPE:
			return NEWOXSTRING;
		case TEXT_GROUP_CTYPE:
			return TEXT_GROUP;
		case TEXT2_CTYPE:
			return TEXT2;
		case TIME_DATEONLY_TYPE:
			return TIME_DATEONLY;
		case PASSWORD2_CTYPE:
			return PASSWORD2;
		case PASSWORD_OXSTRING_CTYPE:
			return OXSTRING2;

		default:
			return null;
		}
	}

	/**
	 * 获取枚举值对应的int值
	 * 
	 * @return int值
	 */
	public abstract int intValue();
}
