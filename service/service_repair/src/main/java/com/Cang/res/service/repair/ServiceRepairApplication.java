package com.Cang.res.service.repair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Date:2023/3/10 9:46
 * @Author:Cang
 */

@SpringBootApplication
@ComponentScan({"com.Cang.res"})
public class ServiceRepairApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRepairApplication.class, args);
    }

}
