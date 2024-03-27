package com.restaurant.feedbacksanalysis.service;

import com.restaurant.feedbacksanalysis.dto.TimeFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FeedbacksAnalysisService {

    private final StreamBridge streamBridge;

    public void getAnalysisByRegion(LocalDate initDate, LocalDate finalDate) {
        TimeFilterDto timeFilterDto = new TimeFilterDto(initDate, finalDate);
        boolean result = streamBridge.send("requestAnalysisByRegion-out-0", timeFilterDto);

    }
}
