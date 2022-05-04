package com.starry.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author starry
 * @version 1.0
 * @date 2022/1/23 19:41
 * @Description
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "faild paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "faild paymentInfo_TimeOut";
    }
}
