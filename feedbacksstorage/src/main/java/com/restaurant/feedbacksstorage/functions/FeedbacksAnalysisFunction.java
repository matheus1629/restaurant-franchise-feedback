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
        return filterDto -> {
            String analysisByRegion = feedbacksStorageService.getAnalysisByRegion(filterDto);
            return analysisByRegion;

        };
    }
}