package com.zjl.comp.anno;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityKey {
    String value();
}
