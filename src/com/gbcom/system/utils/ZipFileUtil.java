/*******************************************************************************
 * Copyright (c) GBCOM
 * All rights reserved. This program and the accompanying materials are belong 
 * to Shanghai GBCOM Communication Technology Co.Ltd
 * http://www.gbcom.com.cn
/*******************************************************************************/
package com.gbcom.system.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/**
 * @author xuyin
 * @version 1.0
 * @date 2013-5-23
 */
public class ZipFileUtil {

	private static final Logger LOG = Logger.getLogger(ZipFileUtil.class);

	/**
	 * 解压文件到指定目录
	 * 
	 * @param zipFile
	 *            zipFile
	 * @param descDir
	 *            descDir
	 * @author xuyin
	 * @throws IOException
	 *             io异常
	 */
	@SuppressWarnings("unchecked")
	public static void unZipFiles(File zipFile, String descDir)
			throws IOException {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replaceAll("\\\\", "/");
			// 判断路径是否存在,不存在则创建文件路径
			File file = null;
			if (outPath.contains("/")) {
				file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			} else {
				file = new File(outPath.substring(0, outPath.lastIndexOf('\\')));
			}
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
			LOG.info("printf-file path:" + outPath);
			OutputStream out = null;
			try {
				out = new FileOutputStream(outPath);
			} catch (FileNotFoundException e) {
				LOG.error("FileNotFoundException    file:" + outPath);
				continue;
			}
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		LOG.info("******************解压完毕********************");
	}

	/**
	 * 复制文件
	 * 
	 * @author zhaishixi 2013-11-19
	 * @param sourceFile
	 *            源文件
	 * @param targetFile
	 *            目的文件
	 * @throws IOException
	 *             io异常
	 */
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null) {
				inBuff.close();
			}
			if (outBuff != null) {
				outBuff.close();
			}
		}
	}

	/**
	 * 复制文件夹
	 * 
	 * @author zhaishixi 2013-11-19
	 * @param sourceDir
	 *            源文件夹
	 * @param targetDir
	 *            目的文件夹
	 * @throws IOException
	 *             io异常
	 */
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(new File(targetDir)
						.getAbsolutePath()
						+ File.separator + file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * 利用ant 压缩文件或文件夹
	 * 
	 * @param srcPathName
	 *            原路径
	 * @param targetDir
	 *            目的路径
	 */
	public static void zip(String srcPathName, String targetDir) {
		LOG.info("srcPathName=" + srcPathName + ";;;;targetDir" + targetDir);
		File file = new File(srcPathName);
		if (!file.exists()) {
			LOG.error("file not exist  sourcePath=" + srcPathName);
			throw new RuntimeException("source file or directory "
					+ srcPathName + " does not exist.");
		}
		Project proj = new Project();
		FileSet fileSet = new FileSet();
		fileSet.setProject(proj);
		// 判断是目录还是文件
		if (file.isDirectory()) {
			fileSet.setDir(file);
			fileSet.setExcludes("**/*.dll");// 排除所有dll文件
		} else {
			fileSet.setFile(file);
		}
		Zip zip = new Zip();
		zip.setProject(proj);
		zip.setDestFile(new File(targetDir));
		zip.addFileset(fileSet);
		zip.execute();
		LOG.info("zip SUCCESS!");
	}

	/**
	 * 实现将多个文件进行压缩，生成指定目录下的指定名字的压缩文件
	 * 
	 * @param zipName
	 *            ：指定生成的压缩文件的名称
	 * @param tempPath
	 *            ：指定生成的压缩文件所存放的目录
	 * @param fileNames
	 *            ：要压缩的多个文件名
	 * */
	public static void createZip(String zipName, String tempPath,
			String[] fileNames) {
		File file = new File(tempPath);
		LOG.info("ZipFileUtil temp_path:" + tempPath);
		File zipFile = new File(tempPath + File.separator + zipName);
		InputStream input = null;
		try {
			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(
					zipFile));
			zipOut.setComment(file.getName());
			if (file.isDirectory()) {
				for (int i = 0; i < fileNames.length; ++i) {
					input = new FileInputStream(new File(tempPath
							+ fileNames[i]));
					zipOut.putNextEntry(new ZipEntry( /*
													 * file.getName()+
													 * File.separator +
													 */fileNames[i]));
					int temp = 0;
					while ((temp = input.read()) != -1) {
						zipOut.write(temp);
					}
					input.close();
					LOG.info("[" + fileNames[i] + "] zip to File:[" + zipName
							+ "] success ");
				}
			}
			zipOut.close();
		} catch (Exception e) {
			LOG.error("zips:", e);
		}
	}

	/**
	 * 利用ant解压zip压缩文件
	 * 
	 * @param zipFilepath
	 *            压缩文件路径
	 * @param destDir
	 *            解压缩文件路径
	 * @throws BuildException
	 *             编译异常
	 * @throws RuntimeException
	 *             运行异常
	 */
	public static void unzip(String zipFilepath, String destDir)
			throws BuildException, RuntimeException {
		if (!new File(zipFilepath).exists()) {
			throw new RuntimeException("zip file " + zipFilepath
					+ " does not exist.");
		}
		Project proj = new Project();
		Expand expand = new Expand();
		expand.setProject(proj);
		expand.setTaskType("unzip");
		expand.setTaskName("unzip");

		expand.setSrc(new File(zipFilepath));
		expand.setDest(new File(destDir));
		expand.execute();
	}

	/**
	 * 递归查找文件 2013-09-11 zhaishixi
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 * @param targetFileName
	 *            需要查找的文件名
	 * @param fileList
	 *            查找到的文件集合
	 * @throws Exception
	 */
	// off checkstyle
	public static void findFiles(String baseDirName, String targetFileName,
			List<File> fileList) throws Exception {

		File baseDir = new File(baseDirName); // 创建一个File对象
		if (!baseDir.exists() || !baseDir.isDirectory()) { // 判断目录是否存在

			throw new Exception("文件查找失败：" + baseDirName + "不是一个目录！");
		}
		String tempName = null;
		File tempFile;
		File[] files = baseDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			tempFile = files[i];
			if (tempFile.isDirectory()) {
				findFiles(tempFile.getAbsolutePath(), targetFileName, fileList);
			} else if (tempFile.isFile()) {
				tempName = tempFile.getName();
				if (wildcardMatch(targetFileName, tempName)) {
					// 匹配成功，将文件名添加到结果集
					fileList.add(tempFile.getAbsoluteFile());
				}
			}
		}
	}

	/**
	 * 通配符匹配 2013-09-11 zhaishixi
	 * 
	 * @param pattern
	 *            通配符模式
	 * @param str
	 *            待匹配的字符串
	 * @return 匹配成功则返回true，否则返回false
	 */
	private static boolean wildcardMatch(String pattern, String str) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				// 通配符星号*表示可以匹配任意多个字符
				while (strIndex < strLength) {
					if (wildcardMatch(pattern.substring(patternIndex + 1), str
							.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else if (ch == '?') {
				// 通配符问号?表示匹配任意一个字符
				strIndex++;
				if (strIndex > strLength) {
					// 表示str中已经没有字符匹配?了。
					return false;
				}
			} else {
				if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
					return false;
				}
				strIndex++;
			}
		}
		return (strIndex == strLength);
	}

	/**
	 * 压缩文件
	 * 
	 * @param srcPathName
	 *            源文件
	 * @param targetDir
	 *            目的路径
	 * @param excludes
	 *            排除条件
	 * @param includes
	 *            包含条件
	 */
	public static void zip(String srcPathName, String targetDir,
			List<String> excludes, List<String> includes) {
		LOG.info("srcPathName=" + srcPathName + ";;;;targetDir" + targetDir);
		File file = new File(srcPathName);
		if (!file.exists()) {
			LOG.error("file not exist  sourcePath=" + srcPathName);
			throw new RuntimeException("source file or directory "
					+ srcPathName + " does not exist.");
		}
		Project proj = new Project();
		FileSet fileSet = new FileSet();
		fileSet.setProject(proj);
		// 判断是目录还是文件
		if (file.isDirectory()) {
			fileSet.setDir(file);
			if (excludes != null) {
				for (int i = 0; i < excludes.size(); i++) {
					fileSet.setExcludes(excludes.get(i));
				}
			}
			if (includes != null) {
				for (int j = 0; j < includes.size(); j++) {
					fileSet.setIncludes(includes.get(j));
				}
			}
		} else {
			fileSet.setFile(file);
		}
		Zip zip = new Zip();
		zip.setProject(proj);
		zip.setDestFile(new File(targetDir));
		zip.addFileset(fileSet);
		zip.execute();
		LOG.info("zip SUCCESS!");
	}

	/**
	 * 压缩多个文件
	 * 
	 * @param zipName
	 *            压缩后文件的名字
	 * @param dstDir
	 *            输出目录
	 * @param filePathes
	 *            多个文件
	 * @throws Exception
	 *             Exception
	 */
	public static void zip(String zipName, String dstDir,
			List<String> filePathes) throws Exception {
		ZipOutputStream zipOutputStream = null;
		try {
			if (filePathes == null) {
				throw new Exception("文件路径列表为空!");
			}
			File file = new File(dstDir);
			if (!file.exists()) {
				file.createNewFile();
			}
			LOG.info("ZipFile output path :" + dstDir + File.separator
					+ zipName);
			File zipFile = new File(dstDir + File.separator + zipName);
			zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
			zipOutputStream.setComment(file.getName());
			for (int i = 0; i < filePathes.size(); i++) {
				String filePath = filePathes.get(i);
				File srcFile = new File(filePath);
				if (srcFile.exists()) {
					InputStream in = new FileInputStream(srcFile);
					zipOutputStream
							.putNextEntry(new ZipEntry(srcFile.getName()));
					byte[] b = new byte[1024];
					int len = 0;
					while ((len = in.read()) > 0) {
						zipOutputStream.write(b, 0, len);
					}
					in.close();
					LOG.info("[" + srcFile.getName() + "] zip to File:["
							+ zipName + "] success ");
				}
			}
			LOG.info("zip SUCCESS!");
		} catch (Exception e) {
			LOG.info("zip:", e);
			throw e;
		} finally {
			zipOutputStream.close();
		}
	}

	/**
	 * 测试主类
	 * 
	 * @param args
	 *            agrg
	 */
	public static void main(String[] args) {
		// 在此目录中找文件
		String baseDIR = "D:\\zhaishixi\\workspace\\acs\\war\\datareport\\basic_alarm_files";
		// 找扩展名为xls的文件
		String fileName = "*.xls";
		List<File> resultList = new ArrayList<File>();
		try {
			findFiles(baseDIR, fileName, resultList);
		} catch (Exception e) {
			LOG.error(e);
		}
		if (resultList.size() == 0) {
			System.out.println("No File Fount.");
		} else {
			for (int i = 0; i < resultList.size(); i++) {
				System.out.println(resultList.get(i));// 显示查找结果。
			}
		}
		List<String> excludes = new ArrayList<String>();
		excludes.add("**/*.zip");
		zip(
				"E:\\java\\apache-tomcat-6.0.41\\webapps\\ccsv3\\upload\\target\\ccsv3",
				"E:\\java\\apache-tomcat-6.0.41\\webapps\\ccsv3\\upload\\target\\ccsv3.zip",
				excludes, null);
	}
}
