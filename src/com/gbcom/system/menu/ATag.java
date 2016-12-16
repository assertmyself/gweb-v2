//off checkstyle
package com.gbcom.system.menu;

/**
 * menu>ul>li>A标签描述
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午03:26:50
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.menu.ATag
 */
public class ATag {
    private String href;
    private String func;
    private String text;

    public ATag() {

    }

    public ATag(String href, String func, String text) {
        this.href = href;
        this.func = func;
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
