package com.restaurant.feedbacksanalysis.service;

import com.restaurant.feedbacksanalysis.dto.TimeFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbacksAnalysisService {

    private final StreamBridge streamBridge;

    public void getAnalysisByRegion(TimeFilterDto timeFilterDto) {
        boolean result = streamBridge.send("requestAnalysisByRegion-out-0", timeFilterDto);
    }
}
