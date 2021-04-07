package com.atguigu.springcloud.service;

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
    public String paymentTimeout(Long id) {
            System.out.println("我到不延迟啦*****************************************" + port);
        return "线程名称: " +  Thread.currentThread().getName() + "当前是超时的 , 问题大大的"+ port;
    }
}
