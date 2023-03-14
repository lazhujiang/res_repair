package com.Cang.res.service.ucenter.timer;

import redis.clients.jedis.Jedis;
import java.util.*;
import java.text.SimpleDateFormat;

public class RedisTimer {
    private static final int MAX_SIZE = 5; // 随机生成的时间戳数量
    private static final SimpleDateFormat format = new SimpleDateFormat("HHmmss"); // 时间戳格式

    public static void main(String[] args) throws InterruptedException {
        // 创建Redis客户端
        Jedis jedis = new Jedis("localhost", 6379);

        // 每天生成50个随机时间戳
        while (true) {
            // 清空当天已有的时间戳
            jedis.del(getKey());

            // 生成随机时间戳并保存到Redis中
            List<String> timestamps = generateTimestamps();
            for (String timestamp : timestamps) {
                jedis.lpush(getKey(), timestamp);
            }

            // 打印当前保存的时间戳信息
            System.out.println(jedis.lrange(getKey(), 0, -1));

            // 等待1天
            Thread.sleep(24 * 60 * 60 * 1000L);
        }
    }

    // 获取当天的Key
    private static String getKey() {
        return "inveit_code";
    }

    // 生成50个随机时间戳
    private static List<String> generateTimestamps() {
        List<String> timestamps = new ArrayList<>();
        for (int i = 0; i < MAX_SIZE; i++) {
            long timestamp = (long)(Math.random() * 24 * 65 * 65 * 1000L);
            timestamps.add(format.format(new Date(timestamp)));
        }
        return timestamps;
    }
}
