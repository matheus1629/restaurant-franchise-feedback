package com.restaurant.feedbacksstorage.service;

import com.restaurant.feedbacksstorage.dto.*;
import com.restaurant.feedbacksstorage.enums.Gender;
import com.restaurant.feedbacksstorage.enums.LevelSatisfaction;
import com.restaurant.feedbacksstorage.enums.Region;
import com.restaurant.feedbacksstorage.model.FeedbackEntity;
import com.restaurant.feedbacksstorage.repository.FeedbacksStorageRepository;
import com.restaurant.feedbacksstorage.util.StatisticsCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    private final MongoTemplate mongoTemplate;

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
        List<FeedbackEntity> feedbacks;
        List<Integer> ageList = new ArrayList<>();
        List<String> genderList = new ArrayList<>();
        List<Integer> ratingList = new ArrayList<>();
        List<String> mealQualityList = new ArrayList<>();
        List<Boolean> wrongOrderList = new ArrayList<>();
        List<String> waitingTimeList = new ArrayList<>();
        List<String> serviceList = new ArrayList<>();
        List<String> ambienceList = new ArrayList<>();

        Map<String, List<FeedbackEntity>> feedbacksByRegion = feedbacksStorageRepository.findDocumentsByRegion(LocalDate.parse(filterDto.initDate()), LocalDate.parse(filterDto.finalDate()));
        RegionAnalysisDto regionAnalysisDto = new RegionAnalysisDto();

        if (feedbacksByRegion.isEmpty()) return regionAnalysisDto;

        for (Map.Entry<String, List<FeedbackEntity>> entry : feedbacksByRegion.entrySet()) {
            String region = entry.getKey();
            feedbacks = entry.getValue();


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

            StatisticRegionCategory statisticRegionCategory = StatisticRegionCategory.builder()
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
                case "SOUTH" -> regionAnalysisDto.setSouth(statisticRegionCategory);
                case "SOUTHEAST" -> regionAnalysisDto.setSoutheast(statisticRegionCategory);
                case "MIDWEST" -> regionAnalysisDto.setMidwest(statisticRegionCategory);
                case "NORTHEAST" -> regionAnalysisDto.setNortheast(statisticRegionCategory);
                case "NORTH" -> regionAnalysisDto.setNorth(statisticRegionCategory);
            }

        }

        return regionAnalysisDto;
    }

    public AgeGroupAnalysisDto getAnalysisByAgeGroup(TimeFilterDto filterDto) {
        List<FeedbackEntity> feedbacks;
        List<String> regionList = new ArrayList<>();
        List<String> genderList = new ArrayList<>();
        List<Integer> ratingList = new ArrayList<>();
        List<String> mealQualityList = new ArrayList<>();
        List<Boolean> wrongOrderList = new ArrayList<>();
        List<String> waitingTimeList = new ArrayList<>();
        List<String> serviceList = new ArrayList<>();
        List<String> ambienceList = new ArrayList<>();

        Map<String, List<FeedbackEntity>> feedbacksByAgeGroup = feedbacksStorageRepository.findDocumentsByAgeGroup(LocalDate.parse(filterDto.initDate()), LocalDate.parse(filterDto.finalDate()));
        AgeGroupAnalysisDto ageGroupAnalysisDto = new AgeGroupAnalysisDto();

        if (feedbacksByAgeGroup.isEmpty()) return ageGroupAnalysisDto;

        for (Map.Entry<String, List<FeedbackEntity>> entry : feedbacksByAgeGroup.entrySet()) {
            String ageGroup = entry.getKey();
            feedbacks = entry.getValue();


            for (FeedbackEntity feedback : feedbacks) {
                regionList.add(feedback.getRegion());
                genderList.add(feedback.getGender());
                ratingList.add(feedback.getRating());
                mealQualityList.add(feedback.getMealQuality());
                wrongOrderList.add(feedback.getWrongOrder());
                waitingTimeList.add(feedback.getWaitingTime());
                serviceList.add(feedback.getService());
                ambienceList.add(feedback.getAmbience());
            }

            StatisticAgeGroupCategory statisticAgeGroupCategory = StatisticAgeGroupCategory.builder()
                    .regionStatistic(StatisticsCalculator.statisticsDataEnum(regionList, Region.class))
                    .genderStatistic(StatisticsCalculator.statisticsDataEnum(genderList, Gender.class))
                    .ratingStatistic(StatisticsCalculator.statisticDataRating(ratingList))
                    .mealQualityStatistic(StatisticsCalculator.statisticsDataEnum(mealQualityList, LevelSatisfaction.class))
                    .wrongOrderStatistic(StatisticsCalculator.statisticDataBoolean(wrongOrderList))
                    .waitingTimeStatistic(StatisticsCalculator.statisticsDataEnum(waitingTimeList, LevelSatisfaction.class))
                    .serviceStatistic(StatisticsCalculator.statisticsDataEnum(serviceList, LevelSatisfaction.class))
                    .ambienceStatistic(StatisticsCalculator.statisticsDataEnum(ambienceList, LevelSatisfaction.class))
                    .build();


            switch (ageGroup) {
                case "ageGroup16To24" -> ageGroupAnalysisDto.setAgeGroup16To24(statisticAgeGroupCategory);
                case "ageGroup25To35" -> ageGroupAnalysisDto.setAgeGroup25To35(statisticAgeGroupCategory);
                case "ageGroup36To50" -> ageGroupAnalysisDto.setAgeGroup36To50(statisticAgeGroupCategory);
                case "ageGroup51To70" -> ageGroupAnalysisDto.setAgeGroup51To70(statisticAgeGroupCategory);
                case "ageGroup71To110" -> ageGroupAnalysisDto.setAgeGroup71To110(statisticAgeGroupCategory);
            }

        }

        return ageGroupAnalysisDto;
    }

    public CustomAnalysisDto getCustomAnalysis(CustomAnalysisFilterDto customAnalysisFilterDto) {
        List<FeedbackEntity> feedbacks;
        List<Integer> ageList = new ArrayList<>();
        List<String> regionList = new ArrayList<>();
        List<String> genderList = new ArrayList<>();
        List<Integer> ratingList = new ArrayList<>();
        List<String> mealQualityList = new ArrayList<>();
        List<Boolean> wrongOrderList = new ArrayList<>();
        List<String> waitingTimeList = new ArrayList<>();
        List<String> serviceList = new ArrayList<>();
        List<String> ambienceList = new ArrayList<>();

        CustomAnalysisDto customAnalysisDto = new CustomAnalysisDto();
        Query query = new Query();
        HashMap<String, String> mapFiltersAdded = new HashMap<>();

        query.addCriteria(Criteria.where("age").gte(customAnalysisFilterDto.minAge()).lte(customAnalysisFilterDto.maxAge()));

        mapFiltersAdded.put("MinAge", customAnalysisFilterDto.minAge().toString());
        mapFiltersAdded.put("MaxAge", customAnalysisFilterDto.maxRating().toString());

        query.addCriteria(Criteria.where("rating").gte(customAnalysisFilterDto.minRating()).lte(customAnalysisFilterDto.maxRating()));
        mapFiltersAdded.put("MinRating", customAnalysisFilterDto.minRating().toString());
        mapFiltersAdded.put("MaxRating", customAnalysisFilterDto.maxRating().toString());

        if (customAnalysisFilterDto.region() != null) {
            query.addCriteria(Criteria.where("region").is(customAnalysisFilterDto.region()));
            mapFiltersAdded.put("Region", customAnalysisFilterDto.region());
        }
        if (customAnalysisFilterDto.gender() != null) {
            query.addCriteria(Criteria.where("gender").is(customAnalysisFilterDto.gender()));
            mapFiltersAdded.put("Gender", customAnalysisFilterDto.gender());
        }
        if (customAnalysisFilterDto.mealQuality() != null) {
            query.addCriteria(Criteria.where("mealQuality").is(customAnalysisFilterDto.mealQuality()));
            mapFiltersAdded.put("MealQuality", customAnalysisFilterDto.mealQuality());
        }
        if (customAnalysisFilterDto.wrongOrder() != null) {
            query.addCriteria(Criteria.where("wrongOrder").is(customAnalysisFilterDto.wrongOrder()));
            mapFiltersAdded.put("wrongOrder", customAnalysisFilterDto.wrongOrder().toString());
        }
        if (customAnalysisFilterDto.waitingTime() != null) {
            query.addCriteria(Criteria.where("waitingTime").is(customAnalysisFilterDto.waitingTime()));
            mapFiltersAdded.put("WaitingTime", customAnalysisFilterDto.waitingTime());
        }
        if (customAnalysisFilterDto.ambience() != null) {
            query.addCriteria(Criteria.where("ambience").is(customAnalysisFilterDto.ambience()));
            mapFiltersAdded.put("Ambience", customAnalysisFilterDto.ambience());
        }
        if (customAnalysisFilterDto.service() != null) {
            query.addCriteria(Criteria.where("service").is(customAnalysisFilterDto.service()));
            mapFiltersAdded.put("Service", customAnalysisFilterDto.service());
        }

        feedbacks = mongoTemplate.find(query, FeedbackEntity.class);

        if (feedbacks.size() == 0) return customAnalysisDto;

        for (FeedbackEntity feedback : feedbacks) {
            regionList.add(feedback.getRegion());
            ageList.add(feedback.getAge());
            genderList.add(feedback.getGender());
            ratingList.add(feedback.getRating());
            mealQualityList.add(feedback.getMealQuality());
            wrongOrderList.add(feedback.getWrongOrder());
            waitingTimeList.add(feedback.getWaitingTime());
            serviceList.add(feedback.getService());
            ambienceList.add(feedback.getAmbience());
        }

        customAnalysisDto.setAgeStatistic((StatisticsCalculator.statisticDataAge(ageList)));
        customAnalysisDto.setRatingStatistic((StatisticsCalculator.statisticDataRating(ratingList)));

        if (!mapFiltersAdded.containsKey("Region")) {
            customAnalysisDto.setRegionStatistic(StatisticsCalculator.statisticsDataEnum(regionList, Region.class));
        }
        if (!mapFiltersAdded.containsKey("Gender")) {
            customAnalysisDto.setGenderStatistic(StatisticsCalculator.statisticsDataEnum(genderList, Gender.class));
        }
        if (!mapFiltersAdded.containsKey("MealQuality")) {
            customAnalysisDto.setMealQualityStatistic(StatisticsCalculator.statisticsDataEnum(mealQualityList, LevelSatisfaction.class));
        }
        if (!mapFiltersAdded.containsKey("wrongOrder")) {
            customAnalysisDto.setWrongOrderStatistic(StatisticsCalculator.statisticDataBoolean(wrongOrderList));
        }
        if (!mapFiltersAdded.containsKey("WaitingTime")) {
            customAnalysisDto.setWaitingTimeStatistic(StatisticsCalculator.statisticsDataEnum(waitingTimeList, LevelSatisfaction.class));
        }
        if (!mapFiltersAdded.containsKey("Ambience")) {
            customAnalysisDto.setAmbienceStatistic(StatisticsCalculator.statisticsDataEnum(ambienceList, LevelSatisfaction.class));
        }
        if (!mapFiltersAdded.containsKey("Service")) {
            customAnalysisDto.setServiceStatistic(StatisticsCalculator.statisticsDataEnum(serviceList, LevelSatisfaction.class));
        }

        customAnalysisDto.setFiltersAdded(mapFiltersAdded);

        return customAnalysisDto;
    }
}
