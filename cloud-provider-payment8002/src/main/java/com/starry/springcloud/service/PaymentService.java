package com.starry.springcloud.service;

import com.starry.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author starry
 * @version 1.0
 * @date 2022/1/16 18:58
 * @Description
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
