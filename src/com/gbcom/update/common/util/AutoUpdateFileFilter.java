package com.gbcom.update.common.util;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import org.apache.log4j.Logger;

public class AutoUpdateFileFilter implements FileFilter {
	private static final Logger LOG = Logger
			.getLogger(AutoUpdateFileFilter.class);
	private List<File> includeFiles;
	private List<File> excludeFiles;

	/**
	 * @return the includeFiles
	 */
	public List<File> getIncludeFiles() {
		return includeFiles;
	}

	/**
	 * @param includeFiles
	 *            the includeFiles to set
	 */
	public void setIncludeFiles(List<File> includeFiles) {
		this.includeFiles = includeFiles;
	}

	/**
	 * @return the excludeFiles
	 */
	public List<File> getExcludeFiles() {
		return excludeFiles;
	}

	/**
	 * @param excludeFiles
	 *            the excludeFiles to set
	 */
	public void setExcludeFiles(List<File> excludeFiles) {
		this.excludeFiles = excludeFiles;
	}

	/**
	 * 过滤
	 * 
	 * @param pathname
	 *            文件
	 * @return boolean true表示放过，false表示过滤掉
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		if (includeFiles == null) {
			if (excludeFiles == null) {
				return false;
			} else {
				if (excludeFiles != null) {
					for (File exf : excludeFiles) {
						if (exf.getName().equals(pathname.getName())) {
							return false;
						}
					}
				}
			}
		} else {
			for (File inf : includeFiles) {
				if (pathname.isDirectory()) {
					return true;
				}
				if (inf.getName().equals(pathname.getName())) {
					LOG.info("no filter file :" + pathname.getAbsolutePath());
					return true;
				}
			}
			if (excludeFiles != null) {
				for (File exf : excludeFiles) {
					if (exf.getName().equals(pathname.getName())) {
						return false;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 过滤一个文件夹及其子文件夹下所有的文件
	 * 
	 * @param file 要过滤的文件
	 * @param filter 过滤规则
	 * @param list 过滤后的文件集合
	 * @return 过滤后的文件集合
	 */
	public static List<File> filterFile(File file, FileFilter filter,
			List<File> list) {
		if (file.isDirectory()) {
			File[] fileArray = file.listFiles(filter);
			for (int i = 0; i < fileArray.length; i++) {
				file = fileArray[i];
				if (file.isDirectory()) {
					filterFile(file, filter, list);
				} else {
					list.add(file);
				}
			}
		} else {
			list.add(file);
		}
		return list;
	}
}
