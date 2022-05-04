package com.starry.springcloud.controller;

import com.starry.springcloud.entity.CommonResult;
import com.starry.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author starry
 * @version 1.0
 * @date 2022/1/16 22:54
 * @Description
 */
@RestController
public class OrderController {

//    private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create") //客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
    public CommonResult create(@RequestBody Payment payment)
    {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,CommonResult.class);
    }


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable Long id)
    {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/"+id, CommonResult.class, id);
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB()
    {
        return restTemplate.getForObject(PAYMENT_URL+"/payment/lb",String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
//        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        // 使用服务名 远程调用
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }

}
