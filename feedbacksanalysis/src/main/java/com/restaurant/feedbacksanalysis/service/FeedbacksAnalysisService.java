package com.restaurant.feedbacksanalysis.service;

import com.restaurant.feedbacksanalysis.dto.TimeFilterDto;
import com.restaurant.feedbacksanalysis.service.callbacks.AgeGroupAnalysisCallback;
import com.restaurant.feedbacksanalysis.service.callbacks.RegionAnalysisCallback;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FeedbacksAnalysisService {

    private final StreamBridge streamBridge;
    private RegionAnalysisCallback regionAnalysisCallback;
    private AgeGroupAnalysisCallback ageGroupAnalysisCallback;

    public void setRegionAnalysisCallback(RegionAnalysisCallback regionAnalysisCallback) {
        this.regionAnalysisCallback = regionAnalysisCallback;
    }

    public RegionAnalysisCallback getRegionAnalysisCallback() {
        return this.regionAnalysisCallback;
    }

    public void setAgeGroupAnalysisCallback(AgeGroupAnalysisCallback ageGroupAnalysisCallback) {
        this.ageGroupAnalysisCallback = ageGroupAnalysisCallback;
    }

    public AgeGroupAnalysisCallback getAgeGroupAnalysisCallback() {
        return this.ageGroupAnalysisCallback;
    }

    public void getAnalysisByRegion(LocalDate initDate, LocalDate finalDate) {
        if (initDate == null) initDate = LocalDate.of(1970, 1, 1);
        if (finalDate == null) finalDate = LocalDate.now();


        TimeFilterDto timeFilterDto = new TimeFilterDto(initDate.toString(), finalDate.toString());
        boolean result = streamBridge.send("requestAnalysisByRegion-out-0", timeFilterDto);
        System.out.println("RESULT" + result);

    }

    public void getAnalysisByAgeGroup(LocalDate initDate, LocalDate finalDate) {
        if (initDate == null) initDate = LocalDate.of(1970, 1, 1);
        if (finalDate == null) finalDate = LocalDate.now();


        TimeFilterDto timeFilterDto = new TimeFilterDto(initDate.toString(), finalDate.toString());
        boolean result = streamBridge.send("requestAnalysisByAgeGroup-out-0", timeFilterDto);
        System.out.println("RESULT" + result);

    }
}
