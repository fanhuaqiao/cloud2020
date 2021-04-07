package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public String getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/hystrix/payment/getTimeout/{id}")
    public String getPaymentTimeout(@PathVariable("id") Long id);

    @GetMapping(value = "/hystrix/payment/getOk/{id}")
    public String getPaymentOk(@PathVariable("id") Long id);
}
