package com.starry.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author starry
 * @version 1.0
 * @date 2022/1/20 10:11
 * @Description
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule() {
//        return new RandomRule();
        return new MyRoundRule(true);
    }
}
