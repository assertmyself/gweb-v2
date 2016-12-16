package com.gbcom.system.manager;

import com.gbcom.system.menu.AttributeValueConveter;
import com.gbcom.system.menu.LiTag;
import com.gbcom.system.menu.Menu;
import com.gbcom.system.menu.UlTag;
import com.thoughtworks.xstream.XStream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tcg
 * Date: 2011-11-6
 * Time: 13:21:14
 * To change this template use File | Settings | File Templates.
 */
//off checkstyle
public class MenuManager {
    /**
     * MENUTAG
     */
    public static String MENUTAG = "menu";
    /**
     * ULLIST
     */
    public static String ULLIST = "ulList";
    /**
     * LITAG
     */
    public static String LITAG = "li";
    /**
     * ULTAG
     */
    public static String ULTAG = "ul";
    /**
     * LILIST
     */
    public static String LILIST = "liList";

    private XStream xStream;
    private List<UlTag> ulTagList;
    private UlTag selectUlTag;
    private String ulStr;
    private String filePath;

    /**
     * 默认构造方法
     */
    public MenuManager() {
        xStream = new XStream();
        xStream.addImplicitCollection(UlTag.class, MenuManager.LILIST);
        xStream.addImplicitCollection(Menu.class, MenuManager.ULLIST);
        xStream.alias(MenuManager.ULTAG, UlTag.class);
        xStream.alias(MenuManager.LITAG, LiTag.class);
        xStream.alias(MenuManager.MENUTAG, Menu.class);
        xStream.aliasAttribute(LiTag.class, "id", "id");
        xStream.aliasAttribute(UlTag.class, "id", "id");
        xStream.aliasAttribute(UlTag.class, "text", "text");
        xStream.registerConverter(new AttributeValueConveter(xStream.getMapper()));
    }

    /**
     * @param xml String
     */
    public void parse(String xml) {
        Menu menu = (Menu) xStream.fromXML(xml);
        ulTagList = menu.getUlList();
    }

    /**
     * @throws IOException Exception
     */
    public void parse() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource rs = resolver.getResource(getFilePath());
        Menu menu = (Menu) xStream.fromXML(new FileInputStream(rs.getFile()));
        ulTagList = menu.getUlList();
    }

    /**
     * 根据指定的id,生成子菜单
     *
     * @param id String
     */
    public void generateChildMenu(String id) {
        for (UlTag ulTag : ulTagList) {
            if (id.equals(ulTag.getId())) {
                selectUlTag = ulTag;
                ulStr = xStream.toXML(selectUlTag);
                break;
            }
        }
    }

    /**
     * @param ulTag UlTag
     * @return String
     */
    public String tag2String(UlTag ulTag) {
        return xStream.toXML(ulTag);
    }

    /**
     * @return List<UlTag>
     */
    public List<UlTag> getUlTagList() {
        return ulTagList;
    }

    /**
     * @param ulTagList List<UlTag>
     */
    public void setUlTagList(List<UlTag> ulTagList) {
        this.ulTagList = ulTagList;
    }

    /**
     * @param id String
     * @return selectUlTag
     */
    public UlTag getSelectUlTag(String id) {
        generateChildMenu(id);
        return selectUlTag;
    }

    /**
     * @param selectUlTag UlTag
     */
    public void setSelectUlTag(UlTag selectUlTag) {
        this.selectUlTag = selectUlTag;
    }

    /**
     * @param id String
     * @return String
     */
    public String getUlStr(String id) {
        generateChildMenu(id);
        return ulStr;
    }

    /**
     * @param ulStr String
     */
    public void setUlStr(String ulStr) {
        this.ulStr = ulStr;
    }

    /**
     * @param filePath String
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return filePath
     */
    public String getFilePath() {
        return filePath;
    }
}
