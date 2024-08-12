package ru.gb.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.gb.timesheet.model.Timesheet;

import java.util.Optional;

@Aspect
@Component
@Slf4j // - Simple logging facade for java
public class LoggingAspect {

    //before
    //After
    //AfterThrowing
    //AfterReturning
    //Around

    @Pointcut("execution( * ru.gb.timesheet.service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointcut(){

    }

    //Pointcut - точка входа в аспект
    @Before(value = "timesheetServiceMethodsPointcut()")
    public void beforeTimesheetsServiceFindById(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        StringBuilder argsInfo = new StringBuilder();

        for (Object arg : args) {
            if (argsInfo.length() > 0) {
                argsInfo.append(", ");
            }
            argsInfo.append(arg.getClass().getSimpleName()).append(" = ").append(arg);
        }

        log.info("Before -> {}#{}({})", jp.getTarget().getClass().getSimpleName(), methodName, argsInfo);

    }
    //@After(value = "timesheetServiceMethodsPointcut()")
    //public void afterTimesheetsServiceFindById(JoinPoint jp){
    //    String methodName = jp.getSignature().getName();
    //    //Long id  = (Long) jp.getArgs()[0];
    //    log.info("After -> TimesheetService#{}",  methodName);
//
    //}





}
