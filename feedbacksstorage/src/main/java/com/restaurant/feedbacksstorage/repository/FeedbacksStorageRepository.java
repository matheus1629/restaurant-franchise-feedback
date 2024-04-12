package com.restaurant.feedbacksstorage.repository;

import com.restaurant.feedbacksstorage.model.FeedbackEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface FeedbacksStorageRepository extends MongoRepository<FeedbackEntity, String> {

    @Query("{ 'date' : { $gte: ?0, $lte: ?1 } }")
    List<FeedbackEntity> findByDateBetween(LocalDate initDate, LocalDate finalDate);

    default Map<String, List<FeedbackEntity>> findDocumentsByRegion(LocalDate initDate, LocalDate finalDate) {
        List<FeedbackEntity> documents = findByDateBetween(initDate, finalDate);
        return documents.stream().collect(Collectors.groupingBy(FeedbackEntity::getRegion));
    }

    default Map<String, List<FeedbackEntity>> findDocumentsByAgeGroup(LocalDate initDate, LocalDate finalDate) {
        List<FeedbackEntity> documents = findByDateBetween(initDate, finalDate);
        return documents.stream().collect(Collectors.groupingBy(feedback -> {
            int age = feedback.getAge();
            if (age <= 24) return "ageGroup16To24";
            else if (age <= 35) return "ageGroup25To35";
            else if (age <= 50) return "ageGroup36To50";
            else if (age <= 70) return "ageGroup51To70";
            else return "ageGroup71To110";
        }));
    }
}
