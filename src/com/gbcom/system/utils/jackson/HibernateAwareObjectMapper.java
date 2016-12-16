package com.gbcom.system.utils.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate3.Hibernate3Module;

/**
 * ObjectMapper
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午03:02:29
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.utils.jackson.HibernateAwareObjectMapper
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
	/**
	 * HibernateAwareObjectMapper
	 */
    public HibernateAwareObjectMapper() {
        Hibernate3Module hm = new Hibernate3Module();
        registerModule(hm);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    /**
     * 
     * @param prettyPrint boolean
     */
    public void setPrettyPrint(boolean prettyPrint) {
        configure(SerializationFeature.INDENT_OUTPUT, prettyPrint);
    }
}
