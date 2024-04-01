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
        if (initDate == null) initDate = LocalDate.of(1970, 1, 1);
        if (finalDate == null) finalDate = LocalDate.now();


        TimeFilterDto timeFilterDto = new TimeFilterDto(initDate.toString(), finalDate.toString());
        boolean result = streamBridge.send("requestAnalysisByRegion-out-0", timeFilterDto);
        System.out.println("RESULT" + result);

    }
}
