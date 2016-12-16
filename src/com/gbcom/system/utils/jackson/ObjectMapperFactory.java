package com.gbcom.system.utils.jackson;


import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created with IntelliJ IDEA.
 * <p/>
 * User: fengerhu
 * Date: 13-1-22
 * Time: 上午10:49
 * To change this template use File | Settings | File Templates.
 */
public class ObjectMapperFactory {
    private static ObjectMapper objectMapper;

    /**
     * json getObjectMapper
     * @param classesPostProcessor ClassesPostProcessor
     * @param jsonSerializer JsonSerializer
     * @return ObjectMapper
     */
    public static ObjectMapper getObjectMapper(ClassesPostProcessor classesPostProcessor, JsonSerializer jsonSerializer) {
        if (objectMapper == null) {
            objectMapper = new HibernateAwareObjectMapper();
//            objectMapper=new ObjectMapper();
//            SimpleModule testModule = new SimpleModule("MyModule", new Version(1, 0, 0, null));
//            Set<Class<?>> achiveClasses = classesPostProcessor.getAchiveClasses();
//            for (Class<?> achiveClass : achiveClasses) {
//                testModule=testModule.addSerializer(achiveClass,jsonSerializer);
//            }
//            objectMapper.registerModule(testModule);
        }
        return objectMapper;
    }


}
