package com.Tokens;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Create by Joyyue sheting on 2020/6/25
 */
public class RateLimiter {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    // 时间戳
    public long timeStamp = System.currentTimeMillis();
    // 桶的容量
    public int capacity = 10;
    // 令牌生成速度10/s
    public int rate = 100;
    // 当前令牌数量
    public int tokens;

    public void acquire() {

//        ProceedingJoinPoint proceedingJoinPoint = ;

//        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

//        ExtRateLimiter extRateLimiter = signature.getMethod().getDeclaredAnnotation(ExtRateLimiter.class);

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

    public static void main(String[] args) {
        RateLimiter tokensLimiter = new RateLimiter();
        tokensLimiter.acquire();
    }

}
