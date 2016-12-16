//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseDataFile;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-18
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 */
public class DataFile extends BaseDataFile {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public DataFile() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public DataFile(java.lang.Long id) {
        super(id);
    }

    /**
     * Constructor for required fields
     */
    public DataFile(
            java.lang.Long id,
            java.lang.String fileName) {

        super(
                id,
                fileName);
    }

/*[CONSTRUCTOR MARKER END]*/
}
