package com.restaurant.feedbackscollector.service;

import com.restaurant.feedbackscollector.dto.FeedbackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbacksCollectorService {

    private final StreamBridge streamBridge;

    public void sendFeedback(FeedbackDto feedbackDto) {
        System.out.println("JJJJJJJJJJJJJJJ" + feedbackDto);
        boolean result = streamBridge.send("sendFeedback-out-0", feedbackDto);
        System.out.println("WWWWWWWWWWWWW" + result);
    }
}
