//off checkstyle
package com.gbcom.common.im.desc;

/**
 * 属性的数据类型枚举类
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public enum DataType {
	ONEBYTE {
		// 单字节
		public int intValue() {
			return ONEBYTE_DTYPE;
		}

		public char getDataType() {
			return UINT8;
		}
	},
	TWOBYTE {
		// 双字节
		public int intValue() {
			return TWOBYTE_DTYPE;
		}

		public char getDataType() {
			return UINT16;
		}
	},
	FOURBYTE {
		// 四字节
		public int intValue() {
			return FOURBYTE_DTYPE;
		}

		public char getDataType() {
			return UINT32;
		}
	},
	EIGHTBYTE {
		// 八字节
		public int intValue() {
			return EIGHTBYTE_DTYPE;
		}

		public char getDataType() {
			return UINT32;
		}
	},
	STRING {
		// 字符串
		public int intValue() {
			return STRING_DTYPE;
		}

		public char getDataType() {
			return CHAR;
		}
	},
	DATE {
		// 日期
		public int intValue() {
			return DATE_DTYPE;
		}

		public char getDataType() {
			return UINT32;
		}
	},
	ONEBYTEARRAY {
		// 单字节数组
		public int intValue() {
			return ONEBYTEARRAY_DTYPE;
		}

		public char getDataType() {
			return UINT8;
		}
	},
	TWOBYTEARRAY {
		// 双字节数组
		public int intValue() {
			return TWOBYTEARRAY_DTYPE;
		}

		public char getDataType() {
			return UINT16;
		}
	},
	FOURBYTEARRAY {
		// 四字节数组
		public int intValue() {
			return FOURBYTEARRAY_DTYPE;
		}

		public char getDataType() {
			return UINT32;
		}
	},
	SIGNED_ONEBYTE {
		// 有符号单字节
		public int intValue() {
			return SIGNED_ONEBYTE_DTYPE;
		}

		public char getDataType() {
			return INT8;
		}
	},
	SIGNED_TWOBYTE {
		// 有符号双字节
		public int intValue() {
			return SIGNED_TWOBYTE_DTYPE;
		}

		public char getDataType() {
			return INT16;
		}
	},

	SIGNED_FOUTBYTE {
		// 有符号四字节
		public int intValue() {
			return SIGNED_FOURBYTE_DTYPE;
		}

		public char getDataType() {
			return INT32;
		}
	},

	SIGNED_ONEBYTEARRAY {
		// 有符号单字节数组
		public int intValue() {
			return SIGNED_ONEBYTEARRAY_DTYPE;
		}

		public char getDataType() {
			return INT8;
		}
	},

	SIGNED_TWOBYTEARRAY {
		// 有符号双字节数组
		public int intValue() {
			return SIGNED_TWOBYTEARRAY_DTYPE;
		}

		public char getDataType() {
			return INT16;
		}
	},

	SIGNED_FOURBYTEARRAY {
		// 有符号四字节数组
		public int intValue() {
			return SIGNED_FOURBYTEARRAY_DTYPE;
		}

		public char getDataType() {
			return INT32;
		}
	},
	IPV4 {
		// IPV4，有符号单字节数组
		public int intValue() {
			return IPV4_DTYPE;
		}

		public char getDataType() {
			return INT8;
		}
	},

	IPV6 {
		// IPV6，有符号单字节数组
		public int intValue() {
			return IPV6_DTYPE;
		}

		public char getDataType() {
			return INT8;
		}
	},

	MAC {
		// MAC地址，有符号单字节数组
		public int intValue() {
			return MAC_DTYPE;
		}

		public char getDataType() {
			return INT8;
		}
	},

	STRINGARRAY {
		// 字符串数组
		public int intValue() {
			return STRINGARRAY_DTYPE;
		}

		public char getDataType() {
			return CHAR;
		}
	},

	TWOBYTEARRAYARRAY {
		// 双字节两维数组
		public int intValue() {
			return TWOBYTEARRAYARRAY_DTYPE;
		}

		public char getDataType() {
			return INT16;
		}
	},

	OXSTRING {
		// 字符串数组
		public int intValue() {
			return OXSTRING_DTYPE;
		}

		public char getDataType() {
			return INT8;
		}
	},

	OXSTRINGARRAY {
		// 16字符串数组
		public int intValue() {
			return OXSTRINGARRAY_DTYPE;
		}

		public char getDataType() {
			return INT8;
		}
	};

	/**
	 * 单字节对应的int值
	 */
	private final static int ONEBYTE_DTYPE = 1;

	/**
	 * 双字节对应的int值
	 */
	private final static int TWOBYTE_DTYPE = 2;

	/**
	 * 四字节对应的int值
	 */
	private final static int FOURBYTE_DTYPE = 3;

	/**
	 * 八字节对应的int值
	 */
	private final static int EIGHTBYTE_DTYPE = 4;

	/**
	 * 字符串对应的int值
	 */
	private final static int STRING_DTYPE = 5;

	/**
	 * 日期对应的int值
	 */
	private final static int DATE_DTYPE = 6;

	/**
	 * 枚举对应的int值
	 */
	private final static int ENUM_DTYPE = 7;

	/**
	 * 比特集对应的int值
	 */
	private final static int BITSET_DTYPE = 8;

	/**
	 * 坐标对应的int值
	 */
	private final static int COORDINATE_DTYPE = 9;

	/**
	 * 浮点型对应的int值
	 */
	private final static int FLOAT_DTYPE = 10;

	/**
	 * 单字节数组对应的int值
	 */
	private final static int ONEBYTEARRAY_DTYPE = 11;

	/**
	 * 双字节数组对应的int值
	 */
	private final static int TWOBYTEARRAY_DTYPE = 12;

	/**
	 * 四字节数组对应的int值
	 */
	private final static int FOURBYTEARRAY_DTYPE = 13;

	/**
	 * 单字节有符号数
	 */
	private final static int SIGNED_ONEBYTE_DTYPE = 21;

	/**
	 * 双字节有符号数
	 */
	private final static int SIGNED_TWOBYTE_DTYPE = 22;

	/**
	 * 四字节有符号数
	 */
	private final static int SIGNED_FOURBYTE_DTYPE = 23;

	/**
	 * 单字节有符号数组对应的int值
	 */
	private final static int SIGNED_ONEBYTEARRAY_DTYPE = 24;

	/**
	 * 双字节有符号数组对应的int值
	 */
	private final static int SIGNED_TWOBYTEARRAY_DTYPE = 25;

	/**
	 * 四字节有符号数组对应的int值
	 */
	private final static int SIGNED_FOURBYTEARRAY_DTYPE = 26;

	/** IPV4数组对应的int值 */
	private final static int IPV4_DTYPE = 27;
	/** IPV6数组对应的int值 */
	private final static int IPV6_DTYPE = 28;
	/** MAC数组对应的int值 */
	private final static int MAC_DTYPE = 29;

	/**
	 * 字符串数组
	 */
	private final static int STRINGARRAY_DTYPE = 30;

	/**
	 * 双字节两维数组
	 */
	private final static int TWOBYTEARRAYARRAY_DTYPE = 31;

	/**
	 * 16进制字符串
	 */
	private final static int OXSTRING_DTYPE = 32;

	/**
	 * 16进制字符串数组
	 */
	private final static int OXSTRINGARRAY_DTYPE = 33;

	/** 网元对应的UINT8,INT8数据类型,对于数据同样处理 **/
	private final static char UINT8 = 'N';
	private final static char INT8 = 'N';

	/** 网元对应的UNT16,INT16数据类型,对于数据同样处理 **/
	private final static char UINT16 = 'W';
	private final static char INT16 = 'W';

	/** 网元对应的UNT32,INT32数据类型,对于数据同样处理 **/
	private final static char UINT32 = 'D';
	private final static char INT32 = 'D';

	/** 2008/06/16 冯静 增加 */
	private final static char CHAR = 'C';

	/**
	 * 获取枚举值对应的int值
	 * 
	 * @return int值
	 */
	public abstract int intValue();

	/**
	 * 获取网元所需的数据类型<br>
	 * 在生成全表文件时填入
	 * 
	 * @return
	 */
	public abstract char getDataType();

	public static DataType getDT(int dt) {
		switch (dt) {
		case ONEBYTE_DTYPE:
			return ONEBYTE;
		case TWOBYTE_DTYPE:
			return TWOBYTE;
		case FOURBYTE_DTYPE:
			return FOURBYTE;
		case EIGHTBYTE_DTYPE:
			return EIGHTBYTE;
		case STRING_DTYPE:
			return STRING;
		case DATE_DTYPE:
			return DATE;
		case ONEBYTEARRAY_DTYPE:
			return ONEBYTEARRAY;
		case TWOBYTEARRAY_DTYPE:
			return TWOBYTEARRAY;
		case FOURBYTEARRAY_DTYPE:
			return FOURBYTEARRAY;
		case SIGNED_ONEBYTE_DTYPE:
			return SIGNED_ONEBYTE;
		case SIGNED_TWOBYTE_DTYPE:
			return SIGNED_TWOBYTE;
		case SIGNED_FOURBYTE_DTYPE:
			return SIGNED_FOUTBYTE;
		case SIGNED_ONEBYTEARRAY_DTYPE:
			return SIGNED_ONEBYTEARRAY;
		case SIGNED_TWOBYTEARRAY_DTYPE:
			return SIGNED_TWOBYTEARRAY;
		case TWOBYTEARRAYARRAY_DTYPE:
			return TWOBYTEARRAYARRAY;
		case SIGNED_FOURBYTEARRAY_DTYPE:
			return SIGNED_FOURBYTEARRAY;
		case IPV4_DTYPE:
			return IPV4;
		case IPV6_DTYPE:
			return IPV6;
		case MAC_DTYPE:
			return MAC;
		case STRINGARRAY_DTYPE:
			return STRINGARRAY;
		case OXSTRING_DTYPE:
			return OXSTRING;
		case OXSTRINGARRAY_DTYPE:
			return OXSTRINGARRAY;
		default:
			return null;
		}
	}
}
