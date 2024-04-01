package com.restaurant.feedbacksstorage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "feedbacks")
@Data
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
    private LocalDate date;
    private Integer idRestaurant;
    private String region;
    private String state;
    private String city;
    private String cep;
}