package com.restaurant.feedbacksanalysis.service;

import com.restaurant.feedbacksanalysis.dto.CustomAnalysisFilterDto;
import com.restaurant.feedbacksanalysis.dto.TimeFilterDto;
import com.restaurant.feedbacksanalysis.service.callbacks.AgeGroupAnalysisCallback;
import com.restaurant.feedbacksanalysis.service.callbacks.CustomAnalysisCallback;
import com.restaurant.feedbacksanalysis.service.callbacks.RegionAnalysisCallback;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FeedbacksAnalysisService {

    private final StreamBridge streamBridge;
    @Getter
    @Setter
    private RegionAnalysisCallback regionAnalysisCallback;
    @Getter
    @Setter
    private AgeGroupAnalysisCallback ageGroupAnalysisCallback;
    @Getter
    @Setter
    private CustomAnalysisCallback customAnalysisCallback;

    public void getAnalysisByRegion(LocalDate initDate, LocalDate finalDate) {
        if (initDate == null) initDate = LocalDate.of(1970, 1, 1);
        if (finalDate == null) finalDate = LocalDate.now();


        TimeFilterDto timeFilterDto = new TimeFilterDto(initDate.toString(), finalDate.toString());
        streamBridge.send("requestAnalysisByRegion-out-0", timeFilterDto);
    }

    public void getAnalysisByAgeGroup(LocalDate initDate, LocalDate finalDate) {
        if (initDate == null) initDate = LocalDate.of(1970, 1, 1);
        if (finalDate == null) finalDate = LocalDate.now();


        TimeFilterDto timeFilterDto = new TimeFilterDto(initDate.toString(), finalDate.toString());
        streamBridge.send("requestAnalysisByAgeGroup-out-0", timeFilterDto);
    }

    public void getCustomAnalysis(CustomAnalysisFilterDto customAnalysisFilterDto) {
        if (customAnalysisFilterDto.getInitDate() == null) customAnalysisFilterDto.setInitDate("1970-01-01");
        if (customAnalysisFilterDto.getFinalDate() == null)
            customAnalysisFilterDto.setFinalDate(LocalDate.now().toString());

        streamBridge.send("requestCustomAnalysis-out-0", customAnalysisFilterDto);
    }
}
