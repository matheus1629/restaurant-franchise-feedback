package com.restaurant.feedbacksanalysis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.feedbacksanalysis.dto.FeedbackAnalysisDto;
import com.restaurant.feedbacksanalysis.model.FeedbackEntity;
import com.restaurant.feedbacksanalysis.repository.FeedbacksAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbacksAnalysisService {

    private final FeedbacksAnalysisRepository feedbacksAnalysisRepository;

    public void saveFeedback(FeedbackAnalysisDto feedbackAnalysisDto) {

        FeedbackEntity feedbackEntity = FeedbackEntity.builder()
                .age(feedbackAnalysisDto.age())
                .gender(feedbackAnalysisDto.gender())
                .rating(feedbackAnalysisDto.rating())
                .mealQuality(feedbackAnalysisDto.mealQuality())
                .wrongOrder(feedbackAnalysisDto.wrongOrder())
                .waitingTime(feedbackAnalysisDto.waitingTime())
                .service(feedbackAnalysisDto.service())
                .ambience(feedbackAnalysisDto.ambience())
                .idRestaurant(feedbackAnalysisDto.idRestaurant())
                .region(feedbackAnalysisDto.region())
                .state(feedbackAnalysisDto.state())
                .city(feedbackAnalysisDto.city())
                .cep(feedbackAnalysisDto.cep())
                .build();


        feedbacksAnalysisRepository.save(feedbackEntity);


    }
}
