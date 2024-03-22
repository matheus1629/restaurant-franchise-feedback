package com.restaurant.feedbacksanalysis.repository;

import com.restaurant.feedbacksanalysis.model.FeedbackEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbacksAnalysisRepository extends MongoRepository<FeedbackEntity, String> {
}
