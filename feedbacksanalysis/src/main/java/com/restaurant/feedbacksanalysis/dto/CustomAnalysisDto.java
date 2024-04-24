package com.restaurant.feedbacksanalysis.dto;

import com.restaurant.feedbacksanalysis.enums.Gender;
import com.restaurant.feedbacksanalysis.enums.LevelSatisfaction;
import com.restaurant.feedbacksanalysis.enums.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomAnalysisDto {
    private Map<String, String> filtersAdded;
    private Map<Region, String> regionStatistic;
    private Map<String, String> ageStatistic;
    private Map<Gender, String> genderStatistic;
    private Map<String, String> ratingStatistic;
    private Map<LevelSatisfaction, String> mealQualityStatistic;
    private Map<Boolean, String> wrongOrderStatistic;
    private Map<LevelSatisfaction, String> waitingTimeStatistic;
    private Map<LevelSatisfaction, String> serviceStatistic;
    private Map<LevelSatisfaction, String> ambienceStatistic;
}
