package com.restaurant.feedbacksanalysis.service;

import com.restaurant.feedbacksanalysis.dto.RegionAnalysisDto;

public interface RegionAnalysisCallback {
    void onResult(RegionAnalysisDto regionAnalysisDto);
}
