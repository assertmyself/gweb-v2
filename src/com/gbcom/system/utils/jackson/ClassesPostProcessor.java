package com.gbcom.system.utils.jackson;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 后处理器ClassesPostProcessor
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午03:06:15
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.utils.jackson.ClassesPostProcessor
 */
public class ClassesPostProcessor implements InitializingBean, ResourceLoaderAware {
    /**
     * List of packages to scan.
     */
    private List<String> packages;

    /**
     * setPackages
     * @param packages List<String>
     */
    public void setPackages(List<String> packages) {
        this.packages = packages;
    }

    /**
     * Persistent classes.
     */
    private Set<Class<?>> achiveClasses;

    /**
     * getAchiveClasses
     * @return Set<Class<?>>
     */
    public Set<Class<?>> getAchiveClasses() {
        return achiveClasses;
    }

    /**
     * setAchiveClasses
     * @param achiveClasses Set<Class<?>> 
     */
    public void setAchiveClasses(Set<Class<?>> achiveClasses) {
        this.achiveClasses = achiveClasses;
    }

    private static final String CLASS_RESOURCE_PATTERN = "*.class";

    private ResourcePatternResolver resourcePatternResolver;
    private MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();

    /**
     * Looks for persistent classes in the classpath under the specified packages.
     *
     * @throws Exception exception
     */
    public void afterPropertiesSet() throws Exception {
        if (packages != null) {

            achiveClasses = new HashSet<Class<?>>();

            try {
                for (String p : packages) {
                    String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                            ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(p)) +
                            "/" +
                            CLASS_RESOURCE_PATTERN;

                    Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);

                    for (Resource resource : resources) {
                        if (resource.isReadable()) {
                            //将注解过滤功能注释
                            MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                            achiveClasses.add(Class.forName(metadataReader.getAnnotationMetadata().getClassName()));

                        }
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException("I/O failure during classpath scanning", ex);
            }
        }
    }

    /**
     * Set the ResourceLoader that this object runs in.
     *
     * @param resourceLoader resource loader
     */
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

}
