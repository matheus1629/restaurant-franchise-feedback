package com.restaurant.feedbackscollector.dto;

import com.restaurant.feedbackscollector.enums.Gender;
import com.restaurant.feedbackscollector.enums.LevelSatisfaction;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class FeedbackAnalysisDto extends FeedbackDto {

    public FeedbackAnalysisDto(
            Integer age,
            Gender gender,
            Integer rating,
            LevelSatisfaction mealQuality,
            Boolean wrongOrder,
            LevelSatisfaction waitingTime,
            LevelSatisfaction service,
            LevelSatisfaction ambience,
            Integer idRestaurant,
            String region,
            String state,
            String city,
            String cep) {
        super(age, gender, rating, mealQuality, wrongOrder, waitingTime, service, ambience);
        this.idRestaurant = idRestaurant;
        this.region = region;
        this.state = state;
        this.city = city;
        this.cep = cep;
    }


    private Integer idRestaurant;
    private String region;
    private String state;
    private String city;
    private String cep;
}
