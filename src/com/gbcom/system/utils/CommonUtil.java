package com.gbcom.system.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import sun.misc.BASE64Encoder;

/**
 * 文件操作；
 * common-io组件
 * 
 * @author SunYanzheng
 * @date 2014-8-14
 * @version v1.0.0
 */
public class CommonUtil {
	@SuppressWarnings("unused")
	private static List<String> readLines(File file) throws IOException {
		return readLines(file, "UTF-8");
	}

	private static List<String> readLines(File file, String encoding)
			throws IOException {
		return FileUtils.readLines(file, encoding);
	}

	/**
	 * @param input
	 *            InputStream
	 * @return List<String>
	 * @throws IOException
	 *             Exception
	 */
	public static List<String> readLines(InputStream input) throws IOException {
		return readLines(input, "UTF-8");
	}

	private static List<String> readLines(InputStream input, String encoding)
			throws IOException {
		return IOUtils.readLines(input, encoding);
	}

	@SuppressWarnings("unused")
	private static List<String> readLines(Reader read) throws IOException {
		return IOUtils.readLines(read);
	}

	/**
	 * 读取文件，保存到byte数组中：不需要指定具体编码格式
	 * 
	 * @param srcFile
	 *            String
	 * @return byte[]
	 */
	public static byte[] read(String srcFile) {

		File file = new File(srcFile);
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			return IOUtils.toByteArray(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(input);
		}
		return new byte[0];

	}

	/**
	 * 读取文件：保存到String对象中，默认使用utf-8作为编码格式
	 * 
	 * @param srcFile
	 *            String
	 * @return String
	 */
	public static String readAll(String srcFile) {
		File file = new File(srcFile);
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			return IOUtils.toString(input, "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(input);
		}
		return "";
	}

	/**
	 * 读取文件，按行分割保存到list中，默认编码格式为utf-8
	 * 
	 * @param srcFile
	 *            String
	 * @return List<String>
	 */
	public static List<String> readLines(String srcFile) {
		File file = new File(srcFile);
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			return readLines(input, "utf-8");
			// IOUtils.readLines(input, encoding);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(input);
		}
		return new ArrayList<String>(0);
	}

	/**
	 * 写文件。最原始的方式，支持 byte数组，无序提供编码格式
	 * 
	 * @param srcFile
	 *            String
	 * @param data
	 *            byte[]
	 */
	public static void wirte(String srcFile, byte[] data) {
		File file = new File(srcFile);
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			IOUtils.write(data, out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				IOUtils.closeQuietly(out);
			}
		}
	}

	/**
	 * 一次性写入文件，如果需要分行，需要再buffer中使用\n 实现
	 * 
	 * @param srcFile
	 *            String
	 * @param buffer
	 *            StringBuffer
	 */
	@SuppressWarnings("deprecation")
	public static void writeAll(String srcFile, StringBuffer buffer) {
		File file = new File(srcFile);
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			IOUtils.write(buffer, out, "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				IOUtils.closeQuietly(out);
			}
		}

	}

	/**
	 * 写文件：支持多行写入，list提供
	 * 
	 * @param srcFile
	 *            String
	 * @param lines
	 *            List<String>
	 */
	public static void writeLines(String srcFile, List<String> lines) {
		File file = new File(srcFile);
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			IOUtils.writeLines(lines, null, out, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				IOUtils.closeQuietly(out);
			}
		}
	}

	/**
	 * 在文件末尾添加：仅支持
	 * 
	 * @param srcFile
	 *            String
	 * @param lines
	 *            List<String>
	 */
	public static void appendWrite(String srcFile, List<String> lines) {
		File file = new File(srcFile);
		OutputStream out = null;
		try {
			out = new FileOutputStream(file, true);// append
			IOUtils.writeLines(lines, null, out, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				IOUtils.closeQuietly(out);
			}
		}
	}

	/**
	 * 在文件末尾添加：仅支持 List 和 StringBuffer 其他省略
	 * 
	 * @param srcFile
	 *            String
	 * @param buffer
	 *            StringBuffer
	 */
	@SuppressWarnings("deprecation")
	public static void appendWrite(String srcFile, StringBuffer buffer) {
		File file = new File(srcFile);
		OutputStream out = null;
		try {
			out = new FileOutputStream(file, true);// append
			IOUtils.write(buffer, out, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				IOUtils.closeQuietly(out);
			}
		}
	}

	/**
	 * 拷贝文件，，return count failed return -1
	 * 
	 * @param src
	 *            String
	 * @param dest
	 *            String
	 * @return int
	 */
	public static int copy(String src, String dest) {
		File srcFile = new File(src);
		File destFile = new File(dest);

		InputStream input = null;
		OutputStream output = null;
		try {
			input = FileUtils.openInputStream(srcFile);
			output = new FileOutputStream(destFile);
			return IOUtils.copy(input, output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (output != null) {
				IOUtils.closeQuietly(output);
			}
			if (input != null) {
				IOUtils.closeQuietly(input);
			}
		}
		return -1;
	}

	/**
	 * 支持文件夹操作
	 * 
	 * @param dests
	 *            String
	 */
	public static void delete(String... dests) {
		for (String dest : dests) {
			File file = new File(dest);
			try {
				FileUtils.forceDeleteOnExit(file);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			} finally {
				file = null;
			}
		}
	}

	/**
	 * 添加到末尾
	 * 
	 * @param fileName
	 *            String
	 * @param content
	 *            String
	 */
	public static void appendFile(String fileName, String content) {
		FileWriter writer = null;
		try {
			// append mode
			writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				writer = null;
			}
		}
	}

	/**
	 * 重写
	 * 
	 * @param fileName
	 *            String
	 * @param content
	 *            String
	 */
	public static void rewriteFile(String fileName, String content) {
		OutputStreamWriter writer = null;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(fileName),
					"utf-8");
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				writer = null;
			}
		}
	}

	/**
	 * base64 编码  
	 * @param bstr byte[]
	 * @return String  
	 */    
	public static String encode64(byte[] bstr){    
		return new BASE64Encoder().encode(bstr);    
	}    
	
	
	/**
	 * @param args
	 *            String[]
	 * @throws IOException
	 *             Exception
	 */
	public static void main(String[] args) throws IOException {
		String dir = "d:\\";
		String srcName = "xxx.jpg";
		String destName = "test.xds";

		// FileUtils.forceMkdir(new File(dir));
		//		
		// copy(dir+File.separator+srcName,dir+File.separator+destName);

		delete(dir + File.separator + srcName, dir + File.separator + destName);
	}

}
