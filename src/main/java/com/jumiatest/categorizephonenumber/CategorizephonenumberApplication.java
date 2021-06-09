package com.jumiatest.categorizephonenumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CategorizephonenumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategorizephonenumberApplication.class, args);
    }

}
