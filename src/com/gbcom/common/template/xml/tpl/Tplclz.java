package com.gbcom.common.template.xml.tpl;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Tplclz
 * 
 * <p>
 * 
 * @author syz
 *         <p>
 * @date 2015-6-18,下午01:35:48
 *       <p>
 * @version v1.0.0
 *          <p>
 * @see com.gbcom.ccsv3.template.xml.tpl.Tplclz
 */
@XStreamAlias("Tplclz")
public class Tplclz {
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String tab1;
	//相同设备型号的 得到不同的展现，，根据OEMpline信息。
	@XStreamAsAttribute
	private String pline;
	//相同设备型号的 得到不同的展现，，根据OEMpline信息。
	@XStreamAsAttribute
	private boolean inner = false;//jvm
	@XStreamAsAttribute
	private String alias ;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return tab1
	 */
	public String getTab1() {
		return tab1;
	}

	/**
	 * @param tab1
	 *            String
	 */
	public void setTab1(String tab1) {
		this.tab1 = tab1;
	}

	/**
	 * : (Tplclz.setPline)
	 * @param pline String
	 */
	public void setPline(String pline) {
		this.pline = pline;
	}

	/**
	 * : (Tplclz.getPline)
	 * @return String
	 */
	public String getPline() {
		return pline;
	}

	/**
	 * : (Tplclz.setInner)
	 * @param inner String
	 */
	public void setInner(boolean inner) {
		this.inner = inner;
	}

	/**
	 * : (Tplclz.isInner)
	 * @return String
	 */
	public boolean isInner() {
		return inner;
	}

	/**
	 * : (Tplclz.setAlias)
	 * @param alias String
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * : (Tplclz.getAlias)
	 * @return String
	 */
	public String getAlias() {
		return alias;
	}

}
