package com.restaurant.feedbacksanalysis.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Consumer;

@Configuration
public class AnalysisFunction {

    @Bean
    public Consumer<String> receiveAnalysis() {
        return o -> {
            System.out.println("GGGGGGGGGGG " + o);
        };
    }
}
