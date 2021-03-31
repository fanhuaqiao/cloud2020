package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    public static final String url = "http://CLOUD-PAYMENT-SERVICE";

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> createPayment(Payment payment){

        return restTemplate.postForObject(url+ "/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){

        return restTemplate.getForObject(url+ "/payment/get/"+id,CommonResult.class);
    }
}
