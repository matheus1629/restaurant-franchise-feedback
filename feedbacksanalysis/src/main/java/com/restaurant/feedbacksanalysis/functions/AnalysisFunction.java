package com.restaurant.feedbacksanalysis.functions;

import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

public class AnalysisFunction {

    @Bean
    public Consumer<String> receiveAnalysis() {
        return o -> {

            System.out.println("GGGGGGGGGGG " + o);
        };
    }
}
