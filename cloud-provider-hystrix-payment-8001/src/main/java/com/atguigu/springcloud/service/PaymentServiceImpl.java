package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Value("${server.port}")
    private String port;

    @Override
    public String paymentOk(Long id) {
        System.out.println("我到OK啦*****************************************");
        return "线程名称: " +  Thread.currentThread().getName() + "当前是OK的" + port;
    }

    @Override
//    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "500")
//    })
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler")
    public String paymentTimeout(Long id) {
//        try {
//            TimeUnit.MILLISECONDS.sleep(600);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int age = 1/0;
        return "线程名称: " +  Thread.currentThread().getName() + "当前是超时的 , 问题大大的"+ port;
    }

    public String paymentTimeoutHandler(Long id) {
        System.out.println("我到处理器了**" + port);
        return "线程名称: " +  Thread.currentThread().getName() + "当前是Hystrix处理器 ***"+ port;
    }

    //=====服务熔断
    @HystrixCommand(fallbackMethod = "test",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String testHystrix(Long id) {
        System.out.println("我是正常的**" + port);
        long a = 10/id;
        return "线程名称: " +  Thread.currentThread().getName() + "当前是正常的 ***"+ port;
    }

    public String test(Long id) {
        System.out.println("我到出问题了**" + port);
        return "线程名称: " +  Thread.currentThread().getName() + "程序报错啦 , 快救我 ***"+ port;
    }
}
