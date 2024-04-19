package com.restaurant.feedbacksstorage.functions;

import com.restaurant.feedbacksstorage.dto.*;
import com.restaurant.feedbacksstorage.service.FeedbacksStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class FeedbacksAnalysisFunction {

    @Bean
    public Function<TimeFilterDto, RegionAnalysisDto> analysisByRegion(FeedbacksStorageService feedbacksStorageService) {
        return filterDto -> feedbacksStorageService.getAnalysisByRegion(filterDto);
    }

    @Bean
    public Function<TimeFilterDto, AgeGroupAnalysisDto> requestAnalysisByAgeGroup(FeedbacksStorageService feedbacksStorageService) {
        return filterDto -> feedbacksStorageService.getAnalysisByAgeGroup(filterDto);
    }

    @Bean
    public Function<CustomAnalysisFilterDto, CustomAnalysisDto> requestCustomAnalysis(FeedbacksStorageService feedbacksStorageService) {
        return customAnalysisFilterDto -> feedbacksStorageService.getCustomAnalysis(customAnalysisFilterDto);
    }
}
