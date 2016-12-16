//off checkstyle
package com.gbcom.common.template.xml.sys;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ClearRule {
    @XStreamAlias("Id")
    public String id;
    
    @XStreamAlias("Name")
    public String name;
    
    @XStreamAlias("Type")
    public String type;
    /**
     * 清除策略：待详细
     */
    @XStreamAlias("Policy")
    public String policy;
    
    @XStreamAlias("Desc")
    public String desc;
    
    public ClearRule(String id, String name, String value,String desc,String policy)
    {
        super();
        this.id = id;
        this.name = name;
        this.type = value;
        this.desc = desc;
        this.policy = policy;
    }

}
