//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseAppVersion;

/**
 * Created with IntelliJ IDEA.
 * User: fengjing
 * Date: 14-6-26
 * Time: 下午7:02
 * To change this template use File | Settings | File Templates.
 */
public class AppVersion extends BaseAppVersion {
    private static final long serialVersionUID = 1L;

    // 终端OS类型
    public static final int APP_OS_TYPE_ANDROID = 1;
    public static final int APP_OS_TYPE_IOS = 2;


    /*[CONSTRUCTOR MARKER BEGIN]*/
    public AppVersion() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public AppVersion(Long id) {
        super(id);
    }

    /**
     * Constructor for required fields
     */
    public AppVersion(
            Long id,
            Integer appOsType,
            String appVersionName,
            Long appVersionCode) {

        super(
                id,
                appOsType,
                appVersionName,
                appVersionCode);
    }

/*[CONSTRUCTOR MARKER END]*/
}
