package com.restaurant.feedbacksanalysis.service;

import com.restaurant.feedbacksanalysis.dto.CustomAnalysisFilterDto;
import com.restaurant.feedbacksanalysis.dto.TimeFilterDto;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FeedbacksAnalysisServiceTest {
    @InjectMocks
    private FeedbacksAnalysisService feedbacksAnalysisService;

    @Mock
    private StreamBridge streamBridge;

    private static Stream<Arguments> testValuesLocalDate() {
        return Stream.of(
                Arguments.of(LocalDate.of(2021, 5, 5), LocalDate.of(2023, 5, 5)),
                Arguments.of(null, LocalDate.of(2023, 5, 5)),
                Arguments.of(LocalDate.of(2021, 5, 5), null),
                Arguments.of(null, null)
        );
    }


    @ParameterizedTest
    @MethodSource("testValuesLocalDate")
    void shouldGetAnalysisByRegion(LocalDate initDate, LocalDate finalDate) {
        //SETUP
        TimeFilterDto expectedDto = new TimeFilterDto(
                initDate != null ? initDate.toString() : "1970-01-01",
                finalDate != null ? finalDate.toString() : LocalDate.now().toString()
        );

        //ACT
        feedbacksAnalysisService.getAnalysisByRegion(initDate, finalDate);

        //ASSERT
        verify(streamBridge).send(eq("requestAnalysisByRegion-out-0"), eq(expectedDto));
    }

    @ParameterizedTest
    @MethodSource("testValuesLocalDate")
    void shouldGetAnalysisByAgeGroup(LocalDate initDate, LocalDate finalDate) {
        //SETUP
        TimeFilterDto expectedDto = new TimeFilterDto(
                initDate != null ? initDate.toString() : "1970-01-01",
                finalDate != null ? finalDate.toString() : LocalDate.now().toString()
        );

        //ACT
        feedbacksAnalysisService.getAnalysisByAgeGroup(initDate, finalDate);

        //ASSERT
        verify(streamBridge).send(eq("requestAnalysisByAgeGroup-out-0"), eq(expectedDto));
    }

    private static Stream<Arguments> testValuesCustomAnalysisFilterDto() {
        return Stream.of(
                Arguments.of(createDto("2021-05-0e5", "2023-05-05")),
                Arguments.of(createDto("2021-05-05", null)),
                Arguments.of(createDto(null, "2023-05-05")),
                Arguments.of(createDto(null, null))
        );
    }

    private static CustomAnalysisFilterDto createDto(String initDate, String finalDate) {
        CustomAnalysisFilterDto dto = new CustomAnalysisFilterDto();
        dto.setInitDate(initDate);
        dto.setFinalDate(finalDate);
        return dto;
    }

    @ParameterizedTest
    @MethodSource("testValuesCustomAnalysisFilterDto")
    void shouldGetCustomAnalysis(CustomAnalysisFilterDto customAnalysisFilterDto) {
        //ACT
        feedbacksAnalysisService.getCustomAnalysis(customAnalysisFilterDto);

        //ASSERT
        verify(streamBridge).send(eq("requestCustomAnalysis-out-0"), any(CustomAnalysisFilterDto.class));

    }
}