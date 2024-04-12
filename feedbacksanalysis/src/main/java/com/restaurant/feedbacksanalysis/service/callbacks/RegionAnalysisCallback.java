package com.restaurant.feedbacksanalysis.service.callbacks;


import com.restaurant.feedbacksanalysis.dto.RegionAnalysisDto;

public interface RegionAnalysisCallback {
    void onResult(RegionAnalysisDto regionAnalysisDto);

}
