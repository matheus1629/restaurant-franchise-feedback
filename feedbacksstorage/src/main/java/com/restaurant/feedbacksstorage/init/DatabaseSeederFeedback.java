package com.restaurant.feedbacksstorage.init;

import com.restaurant.feedbacksstorage.model.FeedbackEntity;
import com.restaurant.feedbacksstorage.model.RestaurantEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.restaurant.feedbacksstorage.init.DatabaseSeederRestaurant.restaurantEntities;

@Component
public class DatabaseSeederFeedback {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeederRestaurant.class);

    public static int generateRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String selectRandomString(List<String> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    public static Date generateRandomDate() {
        long startMillis = 1609459200000L; // 01/01/2021 00:00:00 GMT
        long endMillis = 1714675199000L; // 31/03/2024 23:59:59 GMT
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

    @Bean
    @DependsOn("initRestaurantsDocuments")
    CommandLineRunner initFeedbacksDocuments(MongoTemplate mongoTemplate) {
        return args -> {


            logger.info("Checking if seeders of the collection feedbacks have already been inserted");
            if (mongoTemplate.getCollection("feedbacks").countDocuments() == 0) {

                List<FeedbackEntity> feedbackEntities = new ArrayList<>();

                for (int i = 0; i < 500; i++) {
                    RestaurantEntity restaurantEntity = restaurantEntities.get(generateRandomNumber(0, 49));

                    FeedbackEntity feedbackEntity = FeedbackEntity.builder()
                            .id(String.valueOf(i))
                            .age(generateRandomNumber(16, 110))
                            .gender(selectRandomString(List.of("MALE", "FEMALE", "OTHER")))
                            .rating(generateRandomNumber(1, 10))
                            .mealQuality(selectRandomString(List.of("SATISFIED", "NEUTRAL", "UNSATISFIED")))
                            .wrongOrder(new Random().nextBoolean())
                            .waitingTime(selectRandomString(List.of("SATISFIED", "NEUTRAL", "UNSATISFIED")))
                            .service(selectRandomString(List.of("SATISFIED", "NEUTRAL", "UNSATISFIED")))
                            .ambience(selectRandomString(List.of("SATISFIED", "NEUTRAL", "UNSATISFIED")))
                            .date(generateRandomDate())
                            .idRestaurant(restaurantEntity.getIdRestaurant())
                            .region(restaurantEntity.getRegion())
                            .state(restaurantEntity.getState())
                            .city(restaurantEntity.getCity())
                            .cep(restaurantEntity.getCep())
                            .build();

                    feedbackEntities.add(feedbackEntity);
                }

                logger.info("Inserting seeders into feedbacks collection");
                mongoTemplate.insertAll(feedbackEntities);
                logger.info("Insertion of seeders in feedbacks collection completed");

            } else {
                logger.info("Seeders from the feedbacks collection already inserted. No action required");
            }

        };
    }
}
