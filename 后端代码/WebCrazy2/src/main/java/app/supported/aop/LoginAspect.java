package app.supported.aop;

import app.supported.Holder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author Fizz Pu
 * @Date 2020/12/1 上午10:38
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Aspect
@Component
public class LoginAspect {

    @Autowired
    Holder holder;

    Logger logger = LoggerFactory.getLogger(LoginAspect.class);

    @Pointcut("execution (* app.controller.*.*(..))")
    public void all(){}

    @Before(value = "all()")
    public void beforeAd(JoinPoint joinPoint){
        logger.info("");
    }

    @AfterThrowing(value = "all()")
    public void error(){}

    @AfterReturning(value = "all()")
    public void afterReturn(){}

    @Around(value = "all()")
    public void around(ProceedingJoinPoint proceedingJoinPoint){}

    @After(value = "all()")
    public void after(){ }

}
