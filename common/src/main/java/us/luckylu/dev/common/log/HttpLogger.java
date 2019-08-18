package us.luckylu.dev.common.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class HttpLogger {

    @Around("(@within(org.springframework.web.bind.annotation.RequestMapping)) && (within(us.luckylu.dev.*.controller..*)))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        Object[] args = joinPoint.getArgs();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("controller {}方法{}报错,方法参数是{}, 错误原因是", className, methodName, args, throwable);
            throw throwable;
        }
        log.info("controller {}方法{}访问成功, 方法参数是{}", className, methodName, args);
        return result;
    }
}
