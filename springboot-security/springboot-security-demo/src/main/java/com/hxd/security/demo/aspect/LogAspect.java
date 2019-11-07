package com.hxd.security.demo.aspect;

//import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {
	
    @Pointcut("@annotation(com.hxd.security.demo.annotation.LogUtil)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        // 执行方法
        result = point.proceed();
        return result;
    }
}
