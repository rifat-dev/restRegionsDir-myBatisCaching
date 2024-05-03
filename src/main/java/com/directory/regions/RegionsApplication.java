package com.directory.regions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

@SpringBootApplication(exclude = RedisAutoConfiguration.class)
@EnableCaching
public class RegionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegionsApplication.class, args);
    }

}
