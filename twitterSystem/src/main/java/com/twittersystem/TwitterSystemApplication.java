package com.twittersystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@MapperScan(basePackages = "com.twittersystem.mapper")
@ServletComponentScan
public class TwitterSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterSystemApplication.class, args);
    }

}
