package com.Cang.res.service.oss.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Date:2023/6/4 16:31
 * @Author:Cang
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {

    private String endpoint;
    private String keyid;
    private String keysecret;
    private String bucketname;

}