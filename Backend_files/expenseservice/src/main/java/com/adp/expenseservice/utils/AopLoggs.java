
package com.adp.expenseservice.utils;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopLoggs {
	public static final Log logger = LogFactory.getLog(AopLoggs.class);
	
	@Before("execution(* com.adp.expenseservice.*.*.*(..))")
	public void beforeRepo(JoinPoint joinpoint) {
		logger.info("Entering into "+joinpoint.getTarget().getClass() +" -> "+ joinpoint.getSignature().getName());
	}
	
	
	
	@AfterReturning("execution(* com.adp.expenseservice.*.*.*(..))")
	public void afterRepo(JoinPoint joinpoint) {
		logger.info("Exiting from "+joinpoint.getTarget().getClass() +" -> "+ joinpoint.getSignature().getName());
		
	}
	
	@AfterThrowing("execution(* com.adp.expenseservice.*.*.*(..))")
	public void afterexcepRepo(JoinPoint joinpoint) {
		logger.info("Exception from "+joinpoint.getTarget().getClass() +" -> "+ joinpoint.getSignature().getName());
	}
	
	
	
	
}
