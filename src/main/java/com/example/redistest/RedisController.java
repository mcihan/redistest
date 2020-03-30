package com.example.redistest;

import com.example.redistest.dto.RequestDto;
import com.example.redistest.redis.RedisFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.Map;


@RestController
public class RedisController {

    @Value("${jedisHost}")
    private String host;

    @Value("${jedisPort}")
    private int port;

    boolean initial = true;


    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @ResponseBody
    public String startTransaction(@RequestBody RequestDto dto) {

        if(initial){
            System.out.println("Jedis host/port ----> " + host  + ":" + port + "\n\n");
            initial = false;
        }
        Jedis jedis = RedisFactory.getInstance(host,port);

        String value = dto.getValue();
        String hashCode = value.hashCode() + "";
        System.out.println("Jedis test --> " + value + "  hashcode: " + hashCode);

        jedis.set(hashCode, value);
        jedis.flushAll();
        return "Jedis test :" + value;
    }
}
