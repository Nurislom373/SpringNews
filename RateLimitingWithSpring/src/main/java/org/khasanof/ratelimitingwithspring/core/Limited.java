package org.khasanof.ratelimitingwithspring.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/9/2023
 * <br/>
 * Time: 11:10 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.rateLimiting
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Limited {

    long limit() default 100L;

    int time() default 5;

    Time timeType() default Time.MINUTE;

    RefillType refillType() default RefillType.INTERVAL;
}
