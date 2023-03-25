package com.Cang.res.service.base.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BloomFilterConfig {
    @Autowired
    public RedissonClient redissonClient;

    @Bean
    public RBloomFilter memberBloomFilter() {
        //指定布隆过滤器的名称
        RBloomFilter<Long> bloomFilter = redissonClient.getBloomFilter("bloom_user_id");
        //初始化布隆过滤器的大小与容错率
        bloomFilter.tryInit(10000, 0.001);
        return bloomFilter;
    }
}
