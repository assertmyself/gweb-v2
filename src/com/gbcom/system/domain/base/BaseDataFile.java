//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-20
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseDataFile implements Serializable {

    public static String REF = "DataFile";
    public static String PROP_FILE_NAME = "fileName";
    public static String PROP_FILE_DATE = "fileDate";
    public static String PROP_FILE_PATH = "filePath";
    public static String PROP_FILE_SIZE = "fileSize";

    // constructors
    public BaseDataFile() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseDataFile(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseDataFile(
            java.lang.Long id,
            java.lang.String fileName) {

        this.setId(id);
        this.setFileName(fileName);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private Long id;
    /**
     * 备份文件的名称
     */
    private String fileName;

    /**
     * 备份文件的日期
     */
    private String fileDate;

    /**
     *  备份文件的地址
     */
    private String filePath;
    /**
     * 备份文件的大小
     */
    private String fileSize;


    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="com.hc.core.orm.hibernate.LongIdGenerator"
     * column="ID"
     */
    public java.lang.Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param id the new ID
     * @deprecated
     */
    public void setId(java.lang.Long id) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }

    /**
     *
     * @return
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     *
     * @param fileSize
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     *
     * @return
     */
    public String getFileDate() {
        return fileDate;
    }

    /**
     *
     * @param fileDate
     */
    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    /**
     *
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     *
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.DataFile)) return false;
        else {
            com.gbcom.system.domain.DataFile dataFile = (com.gbcom.system.domain.DataFile) obj;
            if (null == this.getId() || null == dataFile.getId()) return false;
            else return (this.getId().equals(dataFile.getId()));
        }
    }

    /**
     *
     * @return
     */
    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getId()) return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }

    /**
     *
     * @return
     */
    public String toString() {
        org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
        builder.append(id);
        builder.append(fileName);
        builder.append(fileDate);
        builder.append(filePath);
        builder.append(fileSize);
        return builder.toString();
    }


}
