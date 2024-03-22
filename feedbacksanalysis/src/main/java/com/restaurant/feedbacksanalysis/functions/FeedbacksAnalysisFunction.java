package com.restaurant.feedbacksanalysis.functions;

import com.restaurant.feedbacksanalysis.dto.FeedbackAnalysisDto;
import com.restaurant.feedbacksanalysis.service.FeedbacksAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class FeedbacksAnalysisFunction {

    private final FeedbacksAnalysisService feedbacksAnalysisService;

    @Bean
    public Consumer<FeedbackAnalysisDto> feedbacksReceiver() {
        return feedbackDto -> feedbacksAnalysisService.saveFeedback(feedbackDto);
    }
}
