package com.oppo.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 设置注解的生命周期 英文单词是保持、维持的意思
@Target(ElementType.METHOD)
@Documented // 方便生成文档使用 Tools-->Generate JavaDoc
public @interface InterfaceLog {
    String version() default "V1.0";

    String type();

    String interfaceName();

    String sourceID();

    String destinationID();

    /**
     * fuzzy 模糊的意思
     */
    boolean isFuzzy() default true;
}
