package com.Tokens;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.common.util.concurrent.RateLimiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Create by Joyyue sheting on 2020/6/25
 */
@Aspect
@Component
public class RateAspect {

    private ScheduledExecutorService scheduledExecutorService;

    private Map<String, RateLimiter> map = new ConcurrentHashMap<>();

    // 时间戳
    public long timeStamp = System.currentTimeMillis();
    // 桶的容量
    public int capacity = 10;
    // 令牌生成速度10/s
    public int rate = 100;
    // 当前令牌数量
    public int tokens;

//    @Pointcut("execution(public * com.Tokens.HunterTransportController.* (..))")
    @Pointcut("@annotation(com.Tokens.ExtRateLimiter)")
    public void rateLimitPointCut() {
    }


//    @Before("rateLimitPointCut())")
    public void acquireToken(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        ExtRateLimiter extRateLimiter = signature.getMethod().getDeclaredAnnotation(ExtRateLimiter.class);

        if(extRateLimiter == null) {
            return;
        }

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            long now = System.currentTimeMillis();
            // 当前令牌数
            tokens = Math.min(capacity, (int) (tokens + (now - timeStamp) * rate / 1000));
            //每隔0.5秒发送随机数量的请求
            int permits = (int) (Math.random() * 9) + 1;
            System.out.println("请求令牌数：" + permits + "，当前令牌数：" + tokens);
            timeStamp = now;
            if (tokens < permits) {
                // 若不到令牌,则拒绝
                System.out.println("限流了");
            } else {
                // 还有令牌，领取令牌
                tokens -= permits;
                System.out.println("剩余令牌=" + tokens);
            }
        }, 1000, 500, TimeUnit.MILLISECONDS);

    }


    @Around("rateLimitPointCut()")
    public Object tryAcquireToken(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 获取request,response
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        // 或者url(存在map集合的key)

        String url = request.getRequestURI();

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        ExtRateLimiter extRateLimiter = signature.getMethod().getDeclaredAnnotation(ExtRateLimiter.class);

        if(extRateLimiter == null) {
            Object proccess = proceedingJoinPoint.proceed();
            System.out.println("没有获得Token！");
            return proccess;
        }

        double value = extRateLimiter.value();
        long timeout = extRateLimiter.timeOut();

        RateLimiter rateLimiter = getRateLimiter(value, timeout);

        boolean tryAcquire = rateLimiter.tryAcquire();

        if(!tryAcquire) {
            serviceDowng();
            return null;
        }

        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("已获得Token！");
        return proceed;

    }


    // 获取RateLimiter对象，保证每个请求都是单例的，比如很多的同一个pay请求可以共用一个ratelimiter，
    // 但是如果是order请求，那么就必须再创建一个ratelimiter，根据URL进行判断
    private RateLimiter getRateLimiter(double value, long timeOut) {
        // 获取当前URI
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI();
        RateLimiter rateLimiter = null;
        if (!map.containsKey(requestURI)) {
            // 如果没有检测到URI，那么创建一个新的ratelimiter
            rateLimiter = RateLimiter.create(value); // 独立线程
            map.put(requestURI, rateLimiter);
        } else {
            // 能够在map中检测到URL就添加到map中，相同的请求在同一个ratemiliter中
            rateLimiter = map.get(requestURI);
        }
        return rateLimiter;
    }


    // 服务降级
    private void serviceDowng() throws IndexOutOfBoundsException, IOException {
        // 获取响应
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        try {
            writer.println("执行降级方法,亲,服务器忙！请稍后重试!");
        } catch (Exception e) {

        } finally {
            writer.close();
        }
    }

}
