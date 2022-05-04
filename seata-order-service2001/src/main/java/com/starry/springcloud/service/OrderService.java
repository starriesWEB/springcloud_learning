package com.starry.springcloud.service;

import com.starry.springcloud.pojo.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}