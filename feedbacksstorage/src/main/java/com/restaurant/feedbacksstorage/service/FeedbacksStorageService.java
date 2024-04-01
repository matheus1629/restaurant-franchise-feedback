package com.restaurant.feedbacksstorage.service;

import com.restaurant.feedbacksstorage.dto.FeedbackStorageDto;
import com.restaurant.feedbacksstorage.dto.RegionAnalysisDto;
import com.restaurant.feedbacksstorage.dto.StatisticCategory;
import com.restaurant.feedbacksstorage.dto.TimeFilterDto;
import com.restaurant.feedbacksstorage.enums.Gender;
import com.restaurant.feedbacksstorage.enums.LevelSatisfaction;
import com.restaurant.feedbacksstorage.model.FeedbackEntity;
import com.restaurant.feedbacksstorage.repository.FeedbacksStorageRepository;
import com.restaurant.feedbacksstorage.util.StatisticsCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public RegionAnalysisDto getAnalysisByRegion(TimeFilterDto filterDto) {
        Map<String, List<FeedbackEntity>> feedbacksByRegion = feedbacksStorageRepository.findDocumentsByRegion(LocalDate.parse(filterDto.initDate()), LocalDate.parse(filterDto.finalDate()));
        RegionAnalysisDto regionAnalysisDto = new RegionAnalysisDto();

        for (Map.Entry<String, List<FeedbackEntity>> entry : feedbacksByRegion.entrySet()) {
            String region = entry.getKey();
            List<FeedbackEntity> feedbacks = entry.getValue();

            List<Integer> ageList = new ArrayList<>();
            List<String> genderList = new ArrayList<>();
            List<Integer> ratingList = new ArrayList<>();
            List<String> mealQualityList = new ArrayList<>();
            List<Boolean> wrongOrderList = new ArrayList<>();
            List<String> waitingTimeList = new ArrayList<>();
            List<String> serviceList = new ArrayList<>();
            List<String> ambienceList = new ArrayList<>();

            for (FeedbackEntity feedback : feedbacks) {
                ageList.add(feedback.getAge());
                genderList.add(feedback.getGender());
                ratingList.add(feedback.getRating());
                mealQualityList.add(feedback.getMealQuality());
                wrongOrderList.add(feedback.getWrongOrder());
                waitingTimeList.add(feedback.getWaitingTime());
                serviceList.add(feedback.getService());
                ambienceList.add(feedback.getAmbience());
            }

            StatisticCategory statisticCategory = StatisticCategory.builder()
                    .ageStatistic(StatisticsCalculator.statisticDataAge(ageList))
                    .genderStatistic(StatisticsCalculator.statisticsDataEnum(genderList, Gender.class))
                    .ratingStatistic(StatisticsCalculator.statisticDataRating(ratingList))
                    .mealQualityStatistic(StatisticsCalculator.statisticsDataEnum(mealQualityList, LevelSatisfaction.class))
                    .wrongOrderStatistic(StatisticsCalculator.statisticDataBoolean(wrongOrderList))
                    .waitingTimeStatistic(StatisticsCalculator.statisticsDataEnum(waitingTimeList, LevelSatisfaction.class))
                    .serviceStatistic(StatisticsCalculator.statisticsDataEnum(serviceList, LevelSatisfaction.class))
                    .ambienceStatistic(StatisticsCalculator.statisticsDataEnum(ambienceList, LevelSatisfaction.class))
                    .build();


            switch (region) {
                case "south" -> regionAnalysisDto.setSouth(statisticCategory);
                case "southeast" -> regionAnalysisDto.setSoutheast(statisticCategory);
                case "midwest" -> regionAnalysisDto.setMidwest(statisticCategory);
                case "northeast" -> regionAnalysisDto.setNortheast(statisticCategory);
                case "north" -> regionAnalysisDto.setNorth(statisticCategory);
            }

        }

        System.out.println("DDDD " + regionAnalysisDto);

        return regionAnalysisDto;
    }
}
