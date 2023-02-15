package com.oppo.annotation;

import java.lang.annotation.*;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface CustomizedHandleException {
    String description() default "CustomizedHandleException";
}
