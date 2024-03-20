package com.restaurant.feedbacksanalysis.dto;

public record FeedbacksAnalysisDto(Integer idRestaurant, String region, String state, String city, String address,
                                   Integer age, String gender, Integer rating, String mealQuality, Boolean wrongOrder,
                                   String waittingTime, String service, String ambience) {
}
