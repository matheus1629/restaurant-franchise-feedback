package com.restaurant.feedbacksstorage.dto;

import com.restaurant.feedbacksstorage.enums.Gender;
import com.restaurant.feedbacksstorage.enums.LevelSatisfaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatisticCategory {
    private Map<Enum, String> ageStatistic;
    private Map<Gender, String> genderStatistic;
    private Map<Enum, String> rating;
    private Map<LevelSatisfaction, String> mealQuality;
    private Map<Boolean, String> wrongOrder;
    private Map<LevelSatisfaction, String> waitingTime;
    private Map<LevelSatisfaction, String> service;
    private Map<LevelSatisfaction, String> ambience;

}
