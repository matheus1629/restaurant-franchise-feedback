package com.restaurant.feedbacksstorage.functions;

import com.restaurant.feedbacksstorage.dto.FeedbackStorageDto;
import com.restaurant.feedbacksstorage.service.FeedbacksStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class FeedbacksStorageFunction {

    private final FeedbacksStorageService feedbacksStorageService;

    @Bean
    public Consumer<FeedbackStorageDto> feedbacksReceiver() {
        return feedbackDto -> feedbacksStorageService.saveFeedback(feedbackDto);
    }
}
