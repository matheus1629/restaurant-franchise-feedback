package com.restaurant.feedbacksstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgeGroupAnalysisDto {
    private StatisticAgeGroupCategory ageGroup16To24;
    private StatisticAgeGroupCategory ageGroup25To35;
    private StatisticAgeGroupCategory ageGroup36To50;
    private StatisticAgeGroupCategory ageGroup51To70;
    private StatisticAgeGroupCategory ageGroup71To110;
}
