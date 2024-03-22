package com.restaurant.feedbackscollector.repository;

import com.restaurant.feedbackscollector.model.RestaurantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<RestaurantEntity, Integer> {

}
