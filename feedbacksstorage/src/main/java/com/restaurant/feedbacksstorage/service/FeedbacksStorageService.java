package com.restaurant.feedbacksstorage.service;

import com.restaurant.feedbacksstorage.dto.FeedbackStorageDto;
import com.restaurant.feedbacksstorage.dto.RegionAnalysisDto;
import com.restaurant.feedbacksstorage.dto.TimeFilterDto;
import com.restaurant.feedbacksstorage.model.FeedbackEntity;
import com.restaurant.feedbacksstorage.repository.FeedbacksStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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

    public String getAnalysisByRegion(TimeFilterDto filterDto) {
        Map<String, List<FeedbackEntity>> feedbacksByRegion = feedbacksStorageRepository.findDocumentsByRegion(LocalDate.parse(filterDto.initDate()), LocalDate.parse(filterDto.finalDate()));
        RegionAnalysisDto regionAnalysisDto = new RegionAnalysisDto();

        for (Map.Entry<String, List<FeedbackEntity>> entry : feedbacksByRegion.entrySet()) {
            String region = entry.getKey();
            List<FeedbackEntity> feedbacks = entry.getValue();

            System.out.println("Chave: " + region);
            System.out.println("Valores:");
            Map<String, String> analysisData = null;

            List<Integer> age = new ArrayList<>();
            List<String> gender = new ArrayList<>();
            List<Integer> rating = new ArrayList<>();
            List<String> mealQuality = new ArrayList<>();
            List<Boolean> wrongOrder = new ArrayList<>();
            List<String> waitingTime = new ArrayList<>();
            List<String> service = new ArrayList<>();
            List<String> ambience = new ArrayList<>();

            Map<String, List<?>> feedBacksData = new HashMap<>();

            for (FeedbackEntity feedback : feedbacks) {
                age.add(feedback.getAge());
                gender.add(feedback.getGender());
                rating.add(feedback.getRating());
                mealQuality.add(feedback.getMealQuality());
                wrongOrder.add(feedback.getWrongOrder());
                waitingTime.add(feedback.getWaitingTime());
                service.add(feedback.getService());
                ambience.add(feedback.getAmbience());
            }

            System.out.println("JJJJJ" + feedBacksData);

            switch (region) {
                case "south" -> regionAnalysisDto.setSouth(analysisData);
                case "southeast" -> regionAnalysisDto.setSoutheast(analysisData);
                case "midwest" -> regionAnalysisDto.setMidwest(analysisData);
                case "northeast" -> regionAnalysisDto.setNortheast(analysisData);
                case "north" -> regionAnalysisDto.setNorth(analysisData);
            }

        }

        return feedbacksByRegion.toString();
    }
}
