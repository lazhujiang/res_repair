package com.Cang.res.service.ucenter;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * @Date:2023/3/14 13:59
 * @Author:Cang
 */
public class RedisTest {

    @Test
    public void JedisTest() {
        // 连接Redis
        Jedis jedis = new Jedis("localhost", 6379);

        // 待存储的数组
        int[] myArray = {1, 2, 3, 4, 5};

        // 遍历数组并存储到Redis列表
        for (int i = 0; i < myArray.length; i++) {
            jedis.lpush("my_list", Integer.toString(myArray[i]));
        }

        // 关闭Redis连接
        jedis.close();
    }
}
