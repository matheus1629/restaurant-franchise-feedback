package com.restaurant.feedbacksstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegionAnalysisDto {
    private StatisticRegionCategory south;
    private StatisticRegionCategory southeast;
    private StatisticRegionCategory midwest;
    private StatisticRegionCategory northeast;
    private StatisticRegionCategory north;
}

