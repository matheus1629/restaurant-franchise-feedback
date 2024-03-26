package com.restaurant.feedbacksstorage.functions;

import com.restaurant.feedbacksstorage.service.FeedbacksStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class FeedbacksAnalysisFunction {

    private final FeedbacksStorageService feedbacksStorageService;

    @Bean
    public Consumer<Object> analysisByRegion() {
        return o -> feedbacksStorageService.getAnalysisByRegion(o);
    }
}
