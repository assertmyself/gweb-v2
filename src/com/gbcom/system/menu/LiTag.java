//off checkstyle
package com.gbcom.system.menu;

import java.util.List;

/**
 * menu>ul>li标签
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午03:28:02
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.menu.LiTag
 */
public class LiTag {
    private String id;
    private ATag a;
    private List<UlTag> ulList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ATag getA() {
        return a;
    }

    public void setA(ATag a) {
        this.a = a;
    }

    public List<UlTag> getUlList() {
        return ulList;
    }

    public void setUlList(List<UlTag> ulList) {
        this.ulList = ulList;
    }
}
