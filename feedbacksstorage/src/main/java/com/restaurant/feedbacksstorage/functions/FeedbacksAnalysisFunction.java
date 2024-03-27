package com.restaurant.feedbacksstorage.functions;

import com.restaurant.feedbacksstorage.dto.TimeFilterDto;
import com.restaurant.feedbacksstorage.service.FeedbacksStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class FeedbacksAnalysisFunction {

    @Bean
    public Function<TimeFilterDto, String> analysisByRegion(FeedbacksStorageService feedbacksStorageService) {
        return o -> {
            String analysisByRegion = feedbacksStorageService.getAnalysisByRegion(o);
            return analysisByRegion;

        };
    }
}
