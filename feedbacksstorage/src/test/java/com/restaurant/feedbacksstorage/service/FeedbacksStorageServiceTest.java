package com.restaurant.feedbacksstorage.service;

import com.restaurant.feedbacksstorage.dto.*;
import com.restaurant.feedbacksstorage.enums.Gender;
import com.restaurant.feedbacksstorage.enums.LevelSatisfaction;
import com.restaurant.feedbacksstorage.enums.Region;
import com.restaurant.feedbacksstorage.model.FeedbackEntity;
import com.restaurant.feedbacksstorage.repository.FeedbacksStorageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbacksStorageServiceTest {

    @InjectMocks
    private FeedbacksStorageService feedbacksStorageService;

    @Mock
    private FeedbacksStorageRepository feedbacksStorageRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    private TimeFilterDto timeFilterDto;
    private List<FeedbackEntity> feedbacksMock;

    @BeforeEach
    public void setup() {
        timeFilterDto = new TimeFilterDto("2023-01-01", "2024-01-31");
        feedbacksMock = Arrays.asList(
                new FeedbackEntity("1", 25, "FEMALE", 5, "SATISFIED", false, "SATISFIED", "SATISFIED", "SATISFIED", LocalDate.of(2023, 1, 15), 1, "SOUTHEAST", "São Paulo", "Campinas", "35235642"),
                new FeedbackEntity("2", 25, "FEMALE", 5, "SATISFIED", false, "SATISFIED", "SATISFIED", "SATISFIED", LocalDate.of(2023, 1, 15), 1, "SOUTHEAST", "São Paulo", "Campinas", "35235642")
        );

    }

    @Test
    void shouldSaveFeedback() {
        //SETUP
        FeedbackStorageDto feedbackStorageDto = new FeedbackStorageDto(25,
                "MALE",
                7,
                "SATISFIED",
                false,
                "SATISFIED",
                "SATISFIED",
                "SATISFIED",
                "2021-08-05",
                1,
                "SOUTHEAST",
                "São Paulo",
                "Campinas",
                "35235642");


        //ACT
        feedbacksStorageService.saveFeedback(feedbackStorageDto);

        //ASSERT
        verify(feedbacksStorageRepository).save(any(FeedbackEntity.class));
    }

    @Test
    void shouldGetAnalysisByRegionNonEmptyReturn() {
        //SETUP
        Map<String, List<FeedbackEntity>> feedbacksByRegionMock = new HashMap<>();
        feedbacksByRegionMock.put("SOUTHEAST", feedbacksMock);

        when(feedbacksStorageRepository.findDocumentsByRegion(any(LocalDate.class), any(LocalDate.class))).thenReturn(feedbacksByRegionMock);

        //ACT
        RegionAnalysisDto result = feedbacksStorageService.getAnalysisByRegion(timeFilterDto);

        //ASSERT
        assertEquals("100%", result.getSoutheast().getAgeStatistic().get("25-35"));
        assertEquals("100%", result.getSoutheast().getGenderStatistic().get(Gender.FEMALE));
        assertEquals("5", result.getSoutheast().getRatingStatistic().get("averageRating"));
        assertEquals("100%", result.getSoutheast().getMealQualityStatistic().get(LevelSatisfaction.SATISFIED));
        assertEquals("100%", result.getSoutheast().getWrongOrderStatistic().get(false));
        assertEquals("100%", result.getSoutheast().getServiceStatistic().get(LevelSatisfaction.SATISFIED));
        assertEquals("100%", result.getSoutheast().getAmbienceStatistic().get(LevelSatisfaction.SATISFIED));
        assertEquals("100%", result.getSoutheast().getWaitingTimeStatistic().get(LevelSatisfaction.SATISFIED));
        assertNull(result.getNorth());

        verify(feedbacksStorageRepository).findDocumentsByRegion(LocalDate.parse(timeFilterDto.initDate()), LocalDate.parse(timeFilterDto.finalDate()));
    }

    @Test
    void shouldGetAnalysisByRegionEmptyReturn() {
        //SETUP
        Map<String, List<FeedbackEntity>> feedbacksByRegion = new HashMap<>();
        when(feedbacksStorageRepository.findDocumentsByRegion(any(LocalDate.class), any(LocalDate.class))).thenReturn(feedbacksByRegion);

        //ACT
        RegionAnalysisDto result = feedbacksStorageService.getAnalysisByRegion(timeFilterDto);

        //ASSERT
        assertNull(result.getSouth());
        assertNull(result.getSoutheast());
        assertNull(result.getMidwest());
        assertNull(result.getNortheast());
        assertNull(result.getNorth());

        verify(feedbacksStorageRepository).findDocumentsByRegion(LocalDate.parse(timeFilterDto.initDate()), LocalDate.parse(timeFilterDto.finalDate()));
    }


    @Test
    void shouldGetAnalysisByAgeGroupNonEmptyReturn() {
        //SETUP
        Map<String, List<FeedbackEntity>> feedbacksByAgeGroupMock = new HashMap<>();
        feedbacksByAgeGroupMock.put("ageGroup25To35", feedbacksMock);

        when(feedbacksStorageRepository.findDocumentsByAgeGroup(any(LocalDate.class), any(LocalDate.class))).thenReturn(feedbacksByAgeGroupMock);

        //ACT
        AgeGroupAnalysisDto result = feedbacksStorageService.getAnalysisByAgeGroup(timeFilterDto);

        //ASSERT
        assertEquals("100%", result.getAgeGroup25To35().getRegionStatistic().get(Region.SOUTHEAST));
        assertEquals("100%", result.getAgeGroup25To35().getGenderStatistic().get(Gender.FEMALE));
        assertEquals("5", result.getAgeGroup25To35().getRatingStatistic().get("averageRating"));
        assertEquals("100%", result.getAgeGroup25To35().getMealQualityStatistic().get(LevelSatisfaction.SATISFIED));
        assertEquals("100%", result.getAgeGroup25To35().getWrongOrderStatistic().get(false));
        assertEquals("100%", result.getAgeGroup25To35().getServiceStatistic().get(LevelSatisfaction.SATISFIED));
        assertEquals("100%", result.getAgeGroup25To35().getAmbienceStatistic().get(LevelSatisfaction.SATISFIED));
        assertEquals("100%", result.getAgeGroup25To35().getWaitingTimeStatistic().get(LevelSatisfaction.SATISFIED));
        assertNull(result.getAgeGroup16To24());

        verify(feedbacksStorageRepository).findDocumentsByAgeGroup(LocalDate.parse(timeFilterDto.initDate()), LocalDate.parse(timeFilterDto.finalDate()));
    }

    @Test
    void shouldGetAnalysisByAgeGroupEmptyReturn() {
        //SETUP
        Map<String, List<FeedbackEntity>> feedbacksByAgeGroupMock = new HashMap<>();
        when(feedbacksStorageRepository.findDocumentsByAgeGroup(any(LocalDate.class), any(LocalDate.class))).thenReturn(feedbacksByAgeGroupMock);

        //ACT
        AgeGroupAnalysisDto result = feedbacksStorageService.getAnalysisByAgeGroup(timeFilterDto);

        //ASSERT
        assertNull(result.getAgeGroup16To24());
        assertNull(result.getAgeGroup25To35());
        assertNull(result.getAgeGroup36To50());
        assertNull(result.getAgeGroup51To70());
        assertNull(result.getAgeGroup71To110());

        verify(feedbacksStorageRepository).findDocumentsByAgeGroup(LocalDate.parse(timeFilterDto.initDate()), LocalDate.parse(timeFilterDto.finalDate()));
    }

    @Test
    void shouldGetCustomAnalysisNonEmptyReturn() {
        //SETUP
        CustomAnalysisFilterDto customAnalysisFilterDto = new CustomAnalysisFilterDto("2021-01-01", "2024-01-01", "SOUTHEAST", 18, 65,
                "FEMALE", 4, 6, "SATISFIED", false, "SATISFIED", null, null);

        HashMap<String, String> filtersAddedMock = new HashMap<>();
        filtersAddedMock.put("finalDate", "2024-01-01");
        filtersAddedMock.put("MinAge", "18");
        filtersAddedMock.put("MealQuality", "SATISFIED");
        filtersAddedMock.put("initDate", "2021-01-01");
        filtersAddedMock.put("MinRating", "4");
        filtersAddedMock.put("WaitingTime", "SATISFIED");
        filtersAddedMock.put("MaxAge", "65");
        filtersAddedMock.put("Region", "SOUTHEAST");
        filtersAddedMock.put("Gender", "FEMALE");
        filtersAddedMock.put("MaxRating", "6");
        filtersAddedMock.put("wrongOrder", "false");

        when(mongoTemplate.find(any(Query.class), eq(FeedbackEntity.class))).thenReturn(feedbacksMock);

        //ACT
        CustomAnalysisDto result = feedbacksStorageService.getCustomAnalysis(customAnalysisFilterDto);

        //ASSERT
        assertEquals("100%", result.getServiceStatistic().get(LevelSatisfaction.SATISFIED));
        assertEquals("100%", result.getAmbienceStatistic().get(LevelSatisfaction.SATISFIED));
        assertEquals("5", result.getRatingStatistic().get("averageRating"));
        assertEquals("25", result.getAgeStatistic().get("averageAge"));
        assertEquals(null, result.getWaitingTimeStatistic());
        assertEquals(null, result.getWrongOrderStatistic());
        assertEquals(null, result.getMealQualityStatistic());
        assertEquals(null, result.getGenderStatistic());
        assertEquals(null, result.getRegionStatistic());
        assertEquals(filtersAddedMock, result.getFiltersAdded());

        verify(mongoTemplate).find(any(Query.class), eq(FeedbackEntity.class));
    }

    @Test
    void shouldGetCustomAnalysisEmptyReturn() {
        //SETUP
        CustomAnalysisFilterDto customAnalysisFilterDto = new CustomAnalysisFilterDto("2021-01-01", "2024-01-01", "SOUTHEAST", 18, 65,
                "FEMALE", 4, 6, "SATISFIED", false, "SATISFIED", null, null);
        List<FeedbackEntity> feedbacksMock = new ArrayList<>();

        when(mongoTemplate.find(any(Query.class), eq(FeedbackEntity.class))).thenReturn(feedbacksMock);

        //ACT
        CustomAnalysisDto result = feedbacksStorageService.getCustomAnalysis(customAnalysisFilterDto);

        //ASSERT
        assertNull(result.getFiltersAdded());
        assertNull(result.getRegionStatistic());
        assertNull(result.getRatingStatistic());
        assertNull(result.getAgeStatistic());
        assertNull(result.getGenderStatistic());
        assertNull(result.getMealQualityStatistic());
        assertNull(result.getWrongOrderStatistic());
        assertNull(result.getWaitingTimeStatistic());
        assertNull(result.getServiceStatistic());
        assertNull(result.getAmbienceStatistic());

        verify(mongoTemplate).find(any(Query.class), eq(FeedbackEntity.class));
    }
}
