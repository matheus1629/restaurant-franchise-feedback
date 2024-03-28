package com.restaurant.feedbacksstorage.service;

import com.restaurant.feedbacksstorage.dto.FeedbackStorageDto;
import com.restaurant.feedbacksstorage.dto.TimeFilterDto;
import com.restaurant.feedbacksstorage.model.FeedbackEntity;
import com.restaurant.feedbacksstorage.repository.FeedbacksStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FeedbacksStorageService {

    private final FeedbacksStorageRepository feedbacksStorageRepository;

    public void saveFeedback(FeedbackStorageDto feedbackStorageDto) {

        FeedbackEntity feedbackEntity = FeedbackEntity.builder()
                .age(feedbackStorageDto.age())
                .gender(feedbackStorageDto.gender())
                .rating(feedbackStorageDto.rating())
                .mealQuality(feedbackStorageDto.mealQuality())
                .wrongOrder(feedbackStorageDto.wrongOrder())
                .waitingTime(feedbackStorageDto.waitingTime())
                .service(feedbackStorageDto.service())
                .ambience(feedbackStorageDto.ambience())
                .date(LocalDate.parse(feedbackStorageDto.date()))
                .idRestaurant(feedbackStorageDto.idRestaurant())
                .region(feedbackStorageDto.region())
                .state(feedbackStorageDto.state())
                .city(feedbackStorageDto.city())
                .cep(feedbackStorageDto.cep())
                .build();


        feedbacksStorageRepository.save(feedbackEntity);


    }

    public String getAnalysisByRegion(String filterDto) {
        System.out.println("QQQQQQQQQQQQQ" + filterDto);

        Map<String, List<FeedbackEntity>> feedbacksByRegion = feedbacksStorageRepository.findDocumentsByRegion(filterDto.getInitDate(), filterDto.getFinalDate());
        System.out.println("GGGGGGGGGG"+feedbacksByRegion);
        return feedbacksByRegion.toString();
    }
}
