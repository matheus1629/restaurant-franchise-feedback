package com.restaurant.feedbackscollector.dto;

import lombok.Data;

@Data
public class FeedbackAnalysisDto extends FeedbackDto {
    private Integer idRestaurant;
    private String region;
    private String state;
    private String city;
    private String address;
}
