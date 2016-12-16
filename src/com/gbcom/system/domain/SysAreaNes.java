//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseSysAreaNes;

/**
 * 地区管理对象。
 * 
 * 对象是与地区关联，，未分组的设备显示在单独的树结构中。
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-5-14,下午06:11:37
 * <p>
 * @version v1.0.0
 * <p>
 * @see SysAreaNes
 */
public class SysAreaNes extends BaseSysAreaNes {
    private static final long serialVersionUID = 1L;

    public SysAreaNes() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public SysAreaNes(java.lang.Long id) {
        super(id);
    }



}