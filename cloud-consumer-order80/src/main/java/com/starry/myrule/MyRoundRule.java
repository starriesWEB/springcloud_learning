package com.starry.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author starry
 * @version 1.0
 * @date 2022/1/20 11:43
 * @Description
 */
@Component
public class MyRoundRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    // 计数器
    private AtomicInteger number;
    // 是否只统计可用实例
    private boolean availableOnlyServers = true;

    public boolean isAvailableOnlyServers() {
        return availableOnlyServers;
    }

    public void setAvailableOnlyServers(boolean availableOnlyServers) {
        this.availableOnlyServers = availableOnlyServers;
    }


    public MyRoundRule() {
        number = new AtomicInteger(0);
    }

    public MyRoundRule(boolean availableOnlyServers) {
        this();
        this.availableOnlyServers = availableOnlyServers;
    }

    public MyRoundRule(ILoadBalancer lb) {
        this();
        setLoadBalancer(lb);
    }

    @Override
    public Server choose(Object key) {
        ILoadBalancer lb = getLoadBalancer();

        Server server = null;
        int cycleNumber = 0;
        // 最大循环次数 只显示可用就循环3次，否则就循环10次
        int cycleMaxNumber = availableOnlyServers ? 3 : 10;
        while (server == null && cycleNumber++ < cycleMaxNumber) {
            List<Server> serverList;
            if (availableOnlyServers) {
                serverList = lb.getReachableServers();
            } else {
                serverList = lb.getAllServers();
            }
            int serverCount = serverList.size();
            if (serverCount == 0) {
                return null;
            }

            int nextServerIndex = incrementAndGetModulo(serverCount);
            server = serverList.get(nextServerIndex);

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            server = null;

        }
        return server;
    }

    private int incrementAndGetModulo(int serverCount) {
        int current;
        int index;
        do {
            current = number.get();
            index = (number.get() + 1) % serverCount;
        } while (!number.compareAndSet(current, index));
        return index;
    }
}
