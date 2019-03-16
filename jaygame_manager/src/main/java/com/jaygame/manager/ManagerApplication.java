/**
 * Create Manager GateWay
 * Add Spring Cloud Zuul and Eureka Client
 * when http://ip:port/game/all  also equal to  http://ip:port/base/game/all
 */

package com.jaygame.manager;

import JwtUtil.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }


    @Bean
    public  JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
