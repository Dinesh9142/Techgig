package com.dinesh.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

import com.dinesh.annotation.LogMethodCall;



/**
 * @author Vinoth
 *
 */
@LogMethodCall
@Interceptor
public class LoggingInterceptor {

	
	@Inject
	private Logger logger;
	
	private static final String METHOD_ENTER = "{} - Enter Method: {}";
	
	private static final String METHOD_EXIT = "{} - Exit Method: {}";
	
	static {
		System.out.println("Hello World");
	}

	@AroundInvoke
	public Object logMethod(InvocationContext context) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug(METHOD_ENTER, context.getMethod().getDeclaringClass().getCanonicalName(),
					context.getMethod().getName());
		}
		if (logger.isInfoEnabled()) {
			logger.info(METHOD_ENTER, context.getMethod().getDeclaringClass().getCanonicalName(),
					context.getMethod().getName());
		}
		Object obj = context.proceed();
		if (logger.isDebugEnabled()) {
			logger.debug(METHOD_EXIT, context.getMethod().getDeclaringClass().getCanonicalName(),
					context.getMethod().getName());
		}
		if (logger.isInfoEnabled()) {
			logger.info(METHOD_EXIT, context.getMethod().getDeclaringClass().getCanonicalName(),
					context.getMethod().getName());
		}
		return obj;
	}
}
