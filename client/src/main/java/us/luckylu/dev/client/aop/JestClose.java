package us.luckylu.dev.client.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author lu
 * @create 2019-03-26 9:12
 */
@Slf4j
@Aspect
@Component
public class JestClose {

    @After("(@within(org.springframework.web.bind.annotation.RequestMapping)) && (within(us.luckylu.dev.client.controller.SampleController.*)))")
    public void closeJest(JoinPoint joinPoint) throws Throwable{
//        String methodName = joinPoint.getSignature().getName();
//        if(methodName.contains("index")|| methodName.contains("document")){
//            joinPoint.getTarget()
//        }

    }
}
