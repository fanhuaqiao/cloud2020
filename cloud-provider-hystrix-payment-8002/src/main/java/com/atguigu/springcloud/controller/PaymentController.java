package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @GetMapping("/hystrix/payment/getOk/{id}")
    public String getPaymentOk(@PathVariable("id") Long id){
        System.out.println("ok");
        return paymentServiceImpl.paymentOk(id);
    }

    @GetMapping("/hystrix/payment/getTimeout/{id}")
    public String getPaymentTimeout(@PathVariable("id") Long id){
        System.out.println("我到8002controller啦");
        return paymentServiceImpl.paymentTimeout(id);
    }
}
