//off checkstyle
package com.gbcom.system.domain.base;

import java.io.File;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-26
 * Time: 下午7:02
 * To change this template use File | Settings | File Templates.
 */
public class BaseAppVersion implements Serializable {

    // constructors
    public BaseAppVersion() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseAppVersion(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseAppVersion(
            java.lang.Long id,
            Integer appOsType,
            java.lang.String appVersionName,
            java.lang.Long appVersionCode) {

        this.setId(id);
        this.setAppOsType(appOsType);
        this.setAppVersionName(appVersionName);
        this.setAppVersionCode(appVersionCode);
        initialize();
    }

    protected void initialize() {
    }

    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private Long id;

    // 厂商code
    private String vendorCode;

    // app类型：android、ios
    private Integer appOsType;

    // app版本号
    private String appVersionName;

    // APP版本code
    private Long appVersionCode;

    // app版本文件名称
    private String appFileName;

    // app版本文件路径
    private String appFilePath;

    // app版本文件大小
    private String appFileSize;

    // MD5加密
    private String md5;

    // app plist文件路径：ios app
    private String appPlistFileUrl;

    /*创建时间戳*/
    private Long createTimestamp;

    /* 版本更新内容 */
    private String updateContent;

    /*是否可用*/
    private Boolean state;

    /* APP版本文件 */
    private File attachment;
    // 文件的内容类型
    private String attachmentContentType;
    // 上传文件名
    private String attachmentFileName;

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
    public String getVendorCode() {
        return vendorCode;
    }

    /**
     *
     * @param vendorCode
     */
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Integer getAppOsType() {
        return appOsType;
    }

    public void setAppOsType(Integer appOsType) {
        this.appOsType = appOsType;
    }

    /**
     *
     * @return
     */
    public String getAppVersionName() {
        return this.appVersionName;
    }

    /**
     *
     * @param appVersionName
     */
    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }


    /**
     *
     * @return
     */
    public Long getAppVersionCode() {
        return this.appVersionCode;
    }

    /**
     *
     * @param appVersionCode
     */
    public void setAppVersionCode(Long appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    /**
     *
     * @return
     */
    public String  getAppFileName() {
        return this.appFileName;
    }

    /**
     *
     * @param appFileName
     */
    public void setAppFileName(String appFileName) {
        this.appFileName = appFileName;
    }


    /**
     *
     * @return
     */
    public String  getAppFilePath() {
        return this.appFilePath;
    }

    /**
     *
     * @param appFilePath
     */
    public void setAppFilePath(String appFilePath) {
        this.appFilePath = appFilePath;
    }

    /**
     *
     * @return
     */
    public String getAppFileSize() {
        return this.appFileSize;
    }

    /**
     *
     * @param appFileSize
     */
    public void setAppFileSize(String appFileSize) {
        this.appFileSize = appFileSize;
    }

    /**
     *
     * @return
     */
    public String getMd5() {
        return this.md5;
    }

    /**
     *
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     *
     * @return
     */
    public String  getAppPlistFileUrl() {
        return this.appPlistFileUrl;
    }

    /**
     *
     * @param appPlistFileUrl
     */
    public void setAppPlistFileUrl(String appPlistFileUrl) {
        this.appPlistFileUrl = appPlistFileUrl;
    }

    /**
     *
     * @return
     */
    public Long getCreateTimestamp() {
        return this.createTimestamp;
    }

    /**
     *
     * @param createTimestamp
     */
    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**
     *
     * @return
     */
    public String getUpdateContent() {
        return this.updateContent;
    }

    /**
     *
     * @param updateContent
     */
    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    /**
     *
     * @return
     */
    public Boolean getState() {
        return this.state;
    }

    /**
     *
     * @param state
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public File getAttachment() {
        return this.attachment;
    }

    /**
     *
     * @param attachment
     */
    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    /**
     *
     * @return
     */
    public String getAttachmentContentType() {
        return this.attachmentContentType;
    }

    /**
     *
     * @param attachmentContentType
     */
    public void setAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }

    /**
     *
     * @return
     */
    public String getAttachmentFileName() {
        return this.attachmentFileName;
    }

     /**
     *
     * @param attachmentFileName
     */
    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }
}
