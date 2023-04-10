package com.Cang.res.service.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Date:2023/4/10 9:44
 * @Author:Cang
 */
@SpringBootApplication
@ComponentScan("com.Cang.res")
public class ServiceCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApplication.class);
    }

}
