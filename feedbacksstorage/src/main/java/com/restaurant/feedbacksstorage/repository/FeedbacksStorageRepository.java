package com.restaurant.feedbacksstorage.repository;

import com.restaurant.feedbacksstorage.model.FeedbackEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbacksStorageRepository extends MongoRepository<FeedbackEntity, String> {
}
