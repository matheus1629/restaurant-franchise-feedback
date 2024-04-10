package com.restaurant.feedbacksanalysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegionAnalysisDto {
    private StatisticCategory south;
    private StatisticCategory southeast;
    private StatisticCategory midwest;
    private StatisticCategory northeast;
    private StatisticCategory north;
}

