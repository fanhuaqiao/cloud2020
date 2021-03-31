package com.atguigu.springcloud.controllers;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.services.PaymentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {


    @Autowired
    private PaymentServiceImp paymentServiceImp;
    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment){
        int i = paymentServiceImp.create(payment);
        if (i>0) {
            return new CommonResult(200,"8002新增支付成功:" + port,i);
        }else {
            return new CommonResult(444,"8002失败了:" + port);
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


}
