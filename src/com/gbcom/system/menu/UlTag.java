//off checkstyle
package com.gbcom.system.menu;

import java.util.ArrayList;
import java.util.List;
/**
 * UI标签
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午03:27:33
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.menu.UlTag
 */
@SuppressWarnings("unchecked")
public class UlTag {

    private String id;
    private String text;
    private List<LiTag> liList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public List<LiTag> getLiList() {
        if (liList == null) {
            liList = new ArrayList();
        }
        return liList;
    }

    public void setLiList(List<LiTag> liList) {
        this.liList = liList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
