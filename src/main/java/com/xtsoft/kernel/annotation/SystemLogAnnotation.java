package com.xtsoft.kernel.annotation;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLogAnnotation {

	String module() default "";

	String methods() default "";

	String description() default "";

	String encrypted() default "0";

}
