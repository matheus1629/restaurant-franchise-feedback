package com.restaurant.feedbacksanalysis.service;

import com.restaurant.feedbacksanalysis.dto.TimeFilterDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FeedbacksAnalysisServiceTest {
    @InjectMocks
    private FeedbacksAnalysisService feedbacksAnalysisService;

    @Mock
    private StreamBridge streamBridge;

    private static Stream<Arguments> testValues() {
        return Stream.of(
                Arguments.of(LocalDate.of(2021, 5, 5), LocalDate.of(2023, 5, 5)),
                Arguments.of(null, LocalDate.of(2023, 5, 5)),
                Arguments.of(LocalDate.of(2021, 5, 5), null),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("testValues")
    void testGetAnalysisByRegion(LocalDate initDate, LocalDate finalDate) {
        //SETUP
        TimeFilterDto expectedDto = new TimeFilterDto(
                initDate != null ? initDate.toString() : "1970-01-01",
                finalDate != null ? finalDate.toString() : LocalDate.now().toString()
        );

        // Act
        feedbacksAnalysisService.getAnalysisByRegion(initDate, finalDate);

        // Assert
        verify(streamBridge).send(eq("requestAnalysisByRegion-out-0"), eq(expectedDto));
    }

    @Test
    void getAnalysisByAgeGroup() {
    }

    @Test
    void getCustomAnalysis() {
    }
}