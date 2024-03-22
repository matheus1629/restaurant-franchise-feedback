package com.restaurant.feedbacksanalysis.dto;

public record FeedbackAnalysisDto(Integer age, String gender, Integer rating, String mealQuality,
                                  Boolean wrongOrder, String waitingTime, String service, String ambience,
                                  Integer idRestaurant, String region, String state, String city, String cep) {
}
