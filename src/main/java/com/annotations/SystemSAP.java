package com.annotations;

import com.constants.SAPSystems;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SystemSAP {
    SAPSystems sys() default SAPSystems.SYSTEM;
}
