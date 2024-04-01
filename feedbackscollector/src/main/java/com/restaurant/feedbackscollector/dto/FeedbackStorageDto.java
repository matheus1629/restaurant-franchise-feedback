package com.restaurant.feedbackscollector.dto;

import com.restaurant.feedbackscollector.enums.Gender;
import com.restaurant.feedbackscollector.enums.LevelSatisfaction;
import lombok.Data;


@Data
public class FeedbackStorageDto extends FeedbackDto {

    public FeedbackStorageDto(
            Integer age,
            Gender gender,
            Integer rating,
            LevelSatisfaction mealQuality,
            Boolean wrongOrder,
            LevelSatisfaction waitingTime,
            LevelSatisfaction service,
            LevelSatisfaction ambience,
            String date,
            Integer idRestaurant,
            String region,
            String state,
            String city,
            String cep) {
        super(age, gender, rating, mealQuality, wrongOrder, waitingTime, service, ambience);
        this.date = date;
        this.idRestaurant = idRestaurant;
        this.region = region;
        this.state = state;
        this.city = city;
        this.cep = cep;
    }

    private String date;
    private Integer idRestaurant;
    private String region;
    private String state;
    private String city;
    private String cep;
}
