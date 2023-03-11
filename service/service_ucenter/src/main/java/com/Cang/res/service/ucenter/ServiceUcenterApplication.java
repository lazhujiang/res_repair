package com.Cang.res.service.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Date:2023/3/10 20:48
 * @Author:Cang
 */
@SpringBootApplication
@ComponentScan({"com.Cang.res"})
public class ServiceUcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcenterApplication.class, args);
    }
}
