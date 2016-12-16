package com.gbcom.system.utils.jackson;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Created with IntelliJ IDEA.
 * <p/>
 * User: fengerhu
 * Date: 13-2-26
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class BaseDomain {
}
