package com.gbcom.system.mysql;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;
/**
 * db dialect
 * @author SYZ
 * @date 2016-1-8 下午03:24:11
 * @version 1.0.0
 * @see com.gbcom.system.mysql.MySQLTextDialect
 */
public class MySQLTextDialect extends MySQLDialect {

	/**
	 * 
	 * Constructors
	 */
	public MySQLTextDialect() {
		super();
		registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
	}
}