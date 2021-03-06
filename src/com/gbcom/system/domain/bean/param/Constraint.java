//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.04.02 at 12:21:22 ���� CST 
//

//off checkstyle
package com.gbcom.system.domain.bean.param;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="String"/>
 *             &lt;enumeration value="Text"/>
 *             &lt;enumeration value="Integer"/>
 *             &lt;enumeration value="Long"/>
 *             &lt;enumeration value="Double"/>
 *             &lt;enumeration value="Date"/>
 *             &lt;enumeration value="Timestamp"/>
 *             &lt;enumeration value="Boolean"/>
 *             &lt;enumeration value="Enum"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="precesion" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="readonly" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="expression" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="visible" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="default" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Constraint")
public class Constraint {

    @XmlAttribute(required = true)
    protected String type;
    @XmlAttribute
    protected Integer length;
    @XmlAttribute
    protected Integer precesion;
    @XmlAttribute(required = true)
    protected boolean readonly;
    @XmlAttribute
    protected String expression;
    @XmlAttribute(required = true)
    protected boolean visible;
    @XmlAttribute
    protected String definition;
    @XmlAttribute(name = "default")
    protected String _default;

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the length property.
     *
     * @return possible object is
     *         {@link Integer }
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setLength(Integer value) {
        this.length = value;
    }

    /**
     * Gets the value of the precesion property.
     *
     * @return possible object is
     *         {@link Integer }
     */
    public Integer getPrecesion() {
        return precesion;
    }

    /**
     * Sets the value of the precesion property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setPrecesion(Integer value) {
        this.precesion = value;
    }

    /**
     * Gets the value of the readonly property.
     */
    public boolean isReadonly() {
        return readonly;
    }

    /**
     * Sets the value of the readonly property.
     */
    public void setReadonly(boolean value) {
        this.readonly = value;
    }

    /**
     * Gets the value of the expression property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Sets the value of the expression property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setExpression(String value) {
        this.expression = value;
    }

    /**
     * Gets the value of the visible property.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     */
    public void setVisible(boolean value) {
        this.visible = value;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * Gets the value of the default property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDefault(String value) {
        this._default = value;
    }

}
