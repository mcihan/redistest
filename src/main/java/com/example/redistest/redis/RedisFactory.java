package com.example.redistest.redis;

import redis.clients.jedis.Jedis;

public class RedisFactory {
    private static Jedis jedis =null ;


    public static Jedis getInstance(String host, int port){
        if(jedis == null )
            jedis = new Jedis(host, port);
        return jedis;
    }
}
