//off checkstyle
package com.gbcom.demo.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 实体类
 * <p>
 * @author syz
 * <p>
 * @date 2015-12-28,下午02:55:53
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.demo.domain.Hello
 */
public class Hello implements Serializable {
	private static final long serialVersionUID = 1L;
	 // primary key
    private java.lang.Long id;

    // fields
    private java.lang.String name;
    // fields
    private java.lang.Integer age;
    
    //fields
    private java.lang.String address;
    
    // fields
    private java.lang.String description;

    /*进入时间*/
    private java.sql.Timestamp createTime;

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Integer getAge() {
		return age;
	}

	public void setAge(java.lang.Integer age) {
		this.age = age;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getAddress() {
		return address;
	}

}
