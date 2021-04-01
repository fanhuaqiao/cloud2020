package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Override
    public String paymentOk(Long id) {
        return "线程名称: " +  Thread.currentThread().getName() + "当前是OK的";
    }

    @Override
    public String paymentTimeout(Long id) {
        try {
//            TimeUnit.MICROSECONDS.sleep(3);
//            TimeUnit.MILLISECONDS.sleep(3);
            TimeUnit.SECONDS.sleep(3);
//            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程名称: " +  Thread.currentThread().getName() + "当前是超时的 , 问题大大的";
    }
}
