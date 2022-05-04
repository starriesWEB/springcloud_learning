package com.starry.springcloud.controller;

import com.starry.springcloud.entity.CommonResult;
import com.starry.springcloud.entity.Payment;
import com.starry.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author starry
 * @version 1.0
 * @date 2022/3/11 17:47
 * @Description
 */
@RestController
public class CircleBreakerController {

    @Resource
    PaymentService paymentService;

    @GetMapping("/consumer/openfeign/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        if (id == 4) {
            throw new RuntimeException("没有该id");
        }
        return paymentService.paymentSQL(id);
    }
}
