package com.restaurant.feedbacksanalysis.functions;

import com.restaurant.feedbacksanalysis.dto.AgeGroupAnalysisDto;
import com.restaurant.feedbacksanalysis.dto.RegionAnalysisDto;
import com.restaurant.feedbacksanalysis.service.FeedbacksAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class AnalysisFunction {


    private final FeedbacksAnalysisService feedbacksAnalysisService;

    @Bean
    public Consumer<RegionAnalysisDto> receiveAnalysisByRegion() {
        return regionAnalysisDto -> feedbacksAnalysisService.getRegionAnalysisCallback().onResult(regionAnalysisDto);
    }

    @Bean
    public Consumer<AgeGroupAnalysisDto> receiveAnalysisAgeGroup() {
        return ageGroupAnalysisDto -> feedbacksAnalysisService.getAgeGroupAnalysisCallback().onResult(ageGroupAnalysisDto);
    }
}
