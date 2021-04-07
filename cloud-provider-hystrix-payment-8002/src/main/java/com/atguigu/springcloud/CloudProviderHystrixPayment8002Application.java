package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
//@EnableHystrix
public class CloudProviderHystrixPayment8002Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProviderHystrixPayment8002Application.class, args);
    }

}
