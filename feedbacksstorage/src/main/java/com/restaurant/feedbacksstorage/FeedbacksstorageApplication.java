package com.restaurant.feedbacksstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FeedbacksstorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedbacksstorageApplication.class, args);
    }

}
