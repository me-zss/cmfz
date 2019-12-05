package com.shun.aspect;

import com.shun.annotation.LogAnnotation;
import com.shun.entity.Log;
import com.shun.entity.Manager;
import com.shun.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Configuration
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Around("@annotation(com.shun.annotation.LogAnnotation)")
    public Object addLog(ProceedingJoinPoint proceed) throws FileNotFoundException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        String username = null;
        if(manager!=null) {
            username = manager.getUsername();
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String datetime = simpleDateFormat.format(date);
        MethodSignature signature = (MethodSignature) proceed.getSignature();
        LogAnnotation annotation = signature.getMethod().getAnnotation(LogAnnotation.class);
        String action = annotation.value();

        Object[] args = proceed.getArgs();

        String status = null;
        try {
            Object result = proceed.proceed();
            status = "成功";
            if(username==null&&(args[0] instanceof Manager)){
                username = ((Manager)args[0]).getUsername();
            }
            return result;
        } catch (Throwable throwable) {
            status = "失败";
            throwable.printStackTrace();
            return null;
        }finally {
            Log log = new Log(username, datetime, action, status,args);
            String path = request.getSession().getServletContext().getRealPath("/logs");
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            String logStr = "["+log.getDatetime()+"]--"+log.getName()+":"+log.getAction()+"-->"+log.getStatus()+"\t"+log.argsToString();
            LogUtil.addToText(new File(path,"log.txt"),logStr);
            addLogToRedis(logStr);
        }
    }

    public void addLogToRedis(String logStr) {
        ListOperations<String, String> listRedis = stringRedisTemplate.opsForList();
        listRedis.leftPush("log",logStr);
    }
}
