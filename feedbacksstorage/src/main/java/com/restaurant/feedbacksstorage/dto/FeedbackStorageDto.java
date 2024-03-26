package com.restaurant.feedbacksstorage.dto;

import java.util.Date;

public record FeedbackStorageDto(Integer age, String gender, Integer rating, String mealQuality,
                                 Boolean wrongOrder, String waitingTime, String service, String ambience,
                                 Date date, Integer idRestaurant, String region, String state,
                                 String city, String cep) {
}
