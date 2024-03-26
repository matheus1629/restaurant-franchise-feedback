package com.restaurant.feedbacksanalysis.functions;

import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

public class AnalysisFunction {

    @Bean
    public Consumer<Object> requestAnalysisByRegion() {
        return o -> {

            System.out.println(o);
        };
    }
}
