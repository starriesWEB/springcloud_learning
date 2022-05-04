package com.starry.springcloud.service.fallback;

import com.starry.springcloud.entity.CommonResult;
import com.starry.springcloud.entity.Payment;
import com.starry.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

/**
 * @author starry
 * @version 1.0
 * @date 2022/3/11 17:43
 * @Description
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回,没有该流水信息");
    }
}
