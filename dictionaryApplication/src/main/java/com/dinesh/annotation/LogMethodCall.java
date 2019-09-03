package com.dinesh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;
import javax.interceptor.Interceptors;

import com.dinesh.Logger.LoggingInterceptor;

/**
 * @author vinoth
 *
 */
@Inherited
@InterceptorBinding
@Interceptors({ LoggingInterceptor.class })
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface LogMethodCall {}
