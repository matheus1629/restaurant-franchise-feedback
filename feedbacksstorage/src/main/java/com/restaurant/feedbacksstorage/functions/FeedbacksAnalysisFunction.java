package com.restaurant.feedbacksstorage.functions;

import com.restaurant.feedbacksstorage.dto.AgeGroupAnalysisDto;
import com.restaurant.feedbacksstorage.dto.RegionAnalysisDto;
import com.restaurant.feedbacksstorage.dto.TimeFilterDto;
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
    public Function<TimeFilterDto, AgeGroupAnalysisDto> requestCustomAnalysis(FeedbacksStorageService feedbacksStorageService) {
        return filterDto -> feedbacksStorageService.getAnalysisByAgeGroup(filterDto);
    }
}
