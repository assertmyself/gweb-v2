//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseSysLog;


public class SysLog extends BaseSysLog {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public SysLog() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public SysLog(java.lang.Long id) {
        super(id);
    }

/*[CONSTRUCTOR MARKER END]*/
/*
    private String logTypeCode;

    public String getLogTypeCode() {
        return logTypeCode;
    }

    public void setLogTypeCode(String logTypeCode) {
        this.logTypeCode = logTypeCode;
    }*/
}