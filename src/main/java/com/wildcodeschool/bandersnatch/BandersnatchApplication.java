package com.wildcodeschool.bandersnatch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
public class BandersnatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BandersnatchApplication.class, args);
    }

    
}