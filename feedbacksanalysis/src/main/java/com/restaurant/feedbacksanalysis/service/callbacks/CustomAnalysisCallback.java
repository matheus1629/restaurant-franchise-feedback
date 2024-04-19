package com.restaurant.feedbacksanalysis.service.callbacks;


import com.restaurant.feedbacksanalysis.dto.CustomAnalysisDto;
import com.restaurant.feedbacksanalysis.dto.RegionAnalysisDto;

public interface CustomAnalysisCallback {
    void onResult(CustomAnalysisDto customAnalysisDto);

}
