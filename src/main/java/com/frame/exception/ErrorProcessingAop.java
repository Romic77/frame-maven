package com.frame.exception;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 前后台使用,异常统一处理
 * author yefeng
 */
@Aspect
@Order(0)//值越小优先级越高,优先级最低即最后执行
@Component
public class ErrorProcessingAop {

    private static Logger log = LoggerFactory.getLogger(ErrorProcessingAop.class);

    /*@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void pointcutAllController() {
    }*/
    
    
    @Pointcut("execution(* com.frame.controller.*.*(..))")  
    private void pointcutAllController() {  
    }

    /**
     * 异常统一处理
     *
     * @param joinPoint 请求目标对象
     * @return 请求结果
     */
    @Around("pointcutAllController()")
    public Object aroundController(ProceedingJoinPoint joinPoint) {
        Object obj;//执行controller返回的结果
        try {
            obj = joinPoint.proceed();//执行业务逻辑
        } catch (TipRuntimeException tre) {
            obj = commentError(tre.getVp(), tre.getMessage());
        } catch (TipException te) {
            obj = commentError(te.getVp(), te.getMessage());
        } catch (Throwable e) {
            obj = new Object();
            log.error("log-aop:", e);
        }
        return obj;
    }

    private JSONObject commentError(VP vp, String message) {
        JSONObject json = new JSONObject();
        if (vp == null)
            json.put("error",message);
        else
        	json.put("code",vp.getCode());
        	json.put("msg",vp.getMessage());
        	json.put("pattern",vp.getPattern());
        	json.put("status", vp.getStatus());
        return json;
    }
}
