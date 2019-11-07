package com.hxd.security.demo.aspect;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;





@Aspect
@Component
public class ControllerLogAspect {

	private static Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

	/**
	 *  设置监视点方法
	 */
	@Pointcut("execution(public * com.hxd.security.demo.controller..*.*(..))")
	public void webLog() {
	}
	
	/**
	 * 方法执行前
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("#####################Request Start####################");
		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			logger.info("name:{" + name + "},value:{" + request.getParameter(name) + "}");
		}
	}
	
	/**
	 *  方法执行时候
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("webLog()")
	public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
//	     获取参数列表
		Object[] args = joinPoint.getArgs();
//		打印参数
		for(Object obj : args) {
			System.out.println(obj.toString());
		}
		
		return result;
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("RESPONSE : " + ret);
		logger.info("#####################Request End####################");
	}

}
