//off checkstyle
package com.gbcom.system.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 通知告警
 * @author huanghaiyun
 * @createTime 2016-12-9上午11:09:49
 */
public class SysMessage implements Serializable{
	
	private Long id;
	//类型 1通知 2告警３邮件
	private Integer type;
	//状态 1已读 2未读
	private Integer status;
	//标题
	private String title;
	//内容
	private String content;
	//创建者 sysuserId 
	private Long creatorId;
	//接收者 sysuserId
	private Long recipientId;
	//创建时间
	private Date createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Long getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}