package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut decleration
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before (JoinPoint theJoinPoint) {
		// display the mothod we are calling
		
		String theMethod = theJoinPoint.getSignature().toShortString();
		
		myLogger.info("The method we are calling before is " + theMethod);
		
		// display the argument to the method
		
		Object[] args = theJoinPoint.getArgs();
		
		for (Object obj: args) {
			myLogger.info("my arguments are: " + obj);
		}
	}
	
	// add @AfterReturning advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		// display the method we are returning
		String theMethod = theJoinPoint.getSignature().toShortString();
		
		myLogger.info("The method we are calling after is " + theMethod);
		
		// display data returned
		myLogger.info("the Result is: " + theResult);
		
	}
	
}
