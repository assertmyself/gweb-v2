package com.gbcom.system.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
/**
 * 压缩文件解析文件工具，方法非最佳，谨慎使用
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午02:49:41
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.utils.ParFileUtils
 */
public class ParFileUtils {

	/**
	 * 读取zip文件内容
	 * @param parFile File
	 * @param fileName String
	 * @return byte[] byte[]
	 * @throws IOException IOException
	 */
	public static byte[] readFileContentFromZipfile(File parFile,
			String fileName) throws IOException {
		org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(
				parFile);
		try {
			java.util.Enumeration e = zipFile.getEntries();
			org.apache.tools.zip.ZipEntry zipEntry = null;
			while (e.hasMoreElements()) {
				zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
				if (!zipEntry.isDirectory()) {
					if (zipEntry.getName().equals(fileName)) {
						InputStream in = zipFile.getInputStream(zipEntry);
						return IOUtils.toByteArray(in);
					}
				}
			}
		} finally {
			zipFile.close();
		}
		return null;
	}

	/**
	 * readFileContentFromByteArray
	 * @param bytes byte[]
	 * @param fileName String
	 * @return byte[]
	 * @throws IOException IOException
	 */
	public static byte[] readFileContentFromByteArray(byte[] bytes,
			String fileName) throws IOException {
		File tempFile = File.createTempFile("parFile", ".tmp");

		log.debug("temp file name is " + tempFile.getPath());

		FileUtils.writeByteArrayToFile(tempFile, bytes);
		try {
			byte[] ret = readFileContentFromZipfile(tempFile, fileName);
			tempFile.delete();
			return ret;
		} finally {
			tempFile.deleteOnExit();
		}
	}

	/**
	 * readEntriesFromByteArray
	 * @param bytes byte[]
	 * @return ZipEntry[]
	 * @throws IOException IOException
	 */
	public static ZipEntry[] readEntriesFromByteArray(byte[] bytes)
			throws IOException {
		File tempFile = File.createTempFile("parFile", ".tmp");

		log.debug("temp file name is " + tempFile.getPath());

		FileUtils.writeByteArrayToFile(tempFile, bytes);
		try {
			ZipEntry[] zipEntries = readEntriesFromZipfile(tempFile);
			tempFile.delete();
			return zipEntries;
		} finally {
			tempFile.deleteOnExit();
		}
	}

	/**
	 * readEntriesFromZipfile
	 * @param parFile File
	 * @return ZipEntry[]
	 * @throws IOException IOException
	 */
	public static ZipEntry[] readEntriesFromZipfile(File parFile)
			throws IOException {
		org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(
				parFile);
		java.util.Enumeration e = zipFile.getEntries();
		org.apache.tools.zip.ZipEntry zipEntry = null;
		List<org.apache.tools.zip.ZipEntry> zipEntries = new ArrayList<org.apache.tools.zip.ZipEntry>();
		while (e.hasMoreElements()) {
			zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
			zipEntries.add(zipEntry);
		}
		return zipEntries.toArray(new ZipEntry[zipEntries.size()]);
	}

	// attentiion
	// FileUtils.readFileToString(File,String);
	// FileUtils.writeStringToFile(File,String,String);

	/**
	 * 从压缩文件中读取名称为fileName.expandName文件的内容
	 * 
	 * @param parFile
	 *            压缩文件的路径
	 * @param fileName
	 *            文件名称,含扩展名
	 * @return .
	 * @throws java.io.IOException .
	 */

	public static String getContentFromPar(File parFile, String fileName)
			throws IOException {

		byte[] bytes = readFileContentFromZipfile(parFile, fileName);
		return new String(bytes);
	}

	/**
	 * getContentFromPar
	 * 
	 * @param parFile File
	 * @param fileName String
	 * @param encoding String
	 * @return String
	 * @throws IOException IOException
	 */
	public static String getContentFromPar(File parFile, String fileName,
			String encoding) throws IOException {

		byte[] bytes = readFileContentFromZipfile(parFile, fileName);
		return new String(bytes, encoding);
	}

	/**
	 * 从压缩文件中读取xml,the xml file name is match with the parfile name
	 * 
	 * @param parFile
	 *            压缩文件
	 * @return .
	 * @throws java.io.IOException .
	 */
	public static String getXmlFromParFile(File parFile) throws IOException {
		String baseName = FilenameUtils.getBaseName(parFile.getName());
		return getContentFromPar(parFile, baseName + ".xml");
	}

	/**
	 * 从压缩文件中读取hml,the htm file name is match with the parfile name
	 * 
	 * @param parFile
	 *            .
	 * @return .
	 * @throws java.io.IOException .
	 */
	public static String getHtmFromParFile(File parFile) throws IOException {
		String baseName = FilenameUtils.getBaseName(parFile.getName());
		return getContentFromPar(parFile, baseName + ".htm");
	}

	private static final Log log = LogFactory.getLog(ParFileUtils.class);
}