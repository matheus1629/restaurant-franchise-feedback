package com.restaurant.feedbacksstorage.dto;


public record CustomAnalysisFilterDto(String initDate, String finalDate, String region, Integer minAge,
                                      Integer maxAge, String gender, Integer minRating, Integer maxRating,
                                      String mealQuality, Boolean wrongOrder, String waitingTime, String service,
                                      String ambience
) {
}