package com.xtsoft.kernel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLogAnnotation {

	String module() default "";

	String methods() default "";

	String description() default "";

	String encrypted() default "0";

	String[] parameters() default "";

}
