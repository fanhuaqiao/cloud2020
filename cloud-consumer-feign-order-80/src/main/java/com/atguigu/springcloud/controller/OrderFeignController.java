package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "getPaymentTimeoutGlobalHandler")
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public String getPaymentById(@PathVariable Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/hystrix/timeout/payment/get/{id}")
//    @HystrixCommand
    public String getPaymentTimeout(@PathVariable Long id){
        return paymentFeignService.getPaymentTimeout(id);
    }

    @GetMapping("/consumer/hystrix/ok/payment/get/{id}")
    public String getPaymentOk(@PathVariable Long id){
        return paymentFeignService.getPaymentOk(id);
    }

    public String getPaymentTimeoutHandler(@PathVariable Long id){

        return "我是80 , 对方服务器加载缓慢 , 请稍后再试";
    }
    public String getPaymentTimeoutGlobalHandler(){

        return "我是Global , 对方服务器加载缓慢 , 请稍后再试";
    }
}
