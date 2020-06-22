package com.zjl.comp.anno;

import java.lang.annotation.*;


/**
 * bean 对应mubatis 的xml文件
 * namespace xml的namespace
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MapperXml {

    String namespace() default "";

    String xmlFile() default "";

    String defaultQuery() default "queryList";

}
