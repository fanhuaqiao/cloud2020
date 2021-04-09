package com.atguigu.springcloud.controllers;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.services.PaymentServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {


    @Autowired
    private PaymentServiceImp paymentServiceImp;
    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment){
        int i = paymentServiceImp.create(payment);
        if (i>0) {
            return new CommonResult(200,"新增支付成功:" + port,i);
        }else {
            return new CommonResult(444,"失败了:" + port);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id){
        Payment paymentById = paymentServiceImp.getPaymentById(id);
        if (paymentById!=null) {
            return new CommonResult(200,"查询成功:" + port,paymentById);
        }else {
            return new CommonResult(404,"查询失败了:" + port);
        }
    }

    @GetMapping("/payment/discovery")
    public Object getDiscovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element: "+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String lb(){
        return port;
    }


}
