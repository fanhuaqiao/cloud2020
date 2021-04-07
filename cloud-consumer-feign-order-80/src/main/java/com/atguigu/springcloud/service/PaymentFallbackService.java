package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentFeignService{
    @Override
    public String getPaymentById(Long id) {
        return "我是统一Fallback1111";
    }

    @Override
    public String getPaymentTimeout(Long id) {
        return "我是统一Fallback222";
    }

    @Override
    public String getPaymentOk(Long id) {
        return "我是统一Fallback333";
    }
}
