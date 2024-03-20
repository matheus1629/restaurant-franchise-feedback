package com.restaurant.feedbacksanalysis.functions;

import com.restaurant.feedbacksanalysis.dto.FeedbacksAnalysisDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class FeedbacksAnalysisFunction {

    @Bean
    public Function<FeedbacksAnalysisDto, Boolean> feedbackReceiver() {
        return feedbackDto -> {
            System.out.println("DDDDDDDDDDDDDD" + feedbackDto);

            return true;
        };
    }
}
