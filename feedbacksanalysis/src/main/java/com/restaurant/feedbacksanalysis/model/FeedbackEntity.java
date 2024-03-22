package com.restaurant.feedbacksanalysis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedbacks")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class FeedbackEntity {
    @Id
    private String id;
    private Integer age;
    private String gender;
    private Integer rating;
    private String mealQuality;
    private Boolean wrongOrder;
    private String waitingTime;
    private String service;
    private String ambience;
    private Integer idRestaurant;
    private String region;
    private String state;
    private String city;
    private String cep;
}