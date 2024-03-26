package com.restaurant.feedbacksstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurants")
@Getter
@AllArgsConstructor
public class RestaurantEntity {

    @Id
    private Integer idRestaurant;
    private String region;
    private String state;
    private String city;
    private String cep;
}
