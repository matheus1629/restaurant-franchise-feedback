package com.restaurant.feedbacksstorage.dto;

import com.restaurant.feedbacksstorage.enums.Gender;
import com.restaurant.feedbacksstorage.enums.LevelSatisfaction;
import com.restaurant.feedbacksstorage.enums.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomAnalysisDto implements Serializable {
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
