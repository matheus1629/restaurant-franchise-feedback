package com.restaurant.feedbackscollector.service;

import com.restaurant.feedbackscollector.dto.FeedbackDto;
import com.restaurant.feedbackscollector.dto.FeedbackStorageDto;
import com.restaurant.feedbackscollector.enums.Gender;
import com.restaurant.feedbackscollector.enums.LevelSatisfaction;
import com.restaurant.feedbackscollector.exception.ResourceNotFoundException;
import com.restaurant.feedbackscollector.model.RestaurantEntity;
import com.restaurant.feedbackscollector.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbacksCollectorServiceTest {

    @InjectMocks
    private FeedbacksCollectorService feedbacksCollectorService;

    @Mock
    private StreamBridge streamBridge;

    @Mock
    private RestaurantRepository restaurantRepository;

    private FeedbackDto feedbackDto;
    private Integer idRestaurant;

    @BeforeEach
    public void setup() {
        idRestaurant = 1;
        feedbackDto = new FeedbackDto(1,
                25,
                Gender.MALE,
                8,
                LevelSatisfaction.SATISFIED,
                true,
                LevelSatisfaction.SATISFIED,
                LevelSatisfaction.SATISFIED,
                LevelSatisfaction.SATISFIED
        );


    }

    @Test
    void shouldSendFeedback() {
        // SETUP
        RestaurantEntity restaurantEntity = new RestaurantEntity(1, "SOUTHEAST", "São Paulo", "Campinas", "35235642");

        when(restaurantRepository.findById(idRestaurant)).thenReturn(Optional.of(restaurantEntity));
        when(streamBridge.send(any(String.class), any(FeedbackStorageDto.class))).thenReturn(true);

        // ACT
        feedbacksCollectorService.sendFeedback(feedbackDto);

        // ASSERT
        verify(restaurantRepository).findById(idRestaurant);
        verify(streamBridge, timeout(5000)).send(any(String.class), any(FeedbackStorageDto.class));
    }


    @Test
    void shouldThrownResourceNotFoundException_whenNoRestaurantIsFound() {
        // SETUP
        feedbackDto.setIdRestaurant(idRestaurant);
        when(restaurantRepository.findById(idRestaurant)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> feedbacksCollectorService.sendFeedback(feedbackDto));
    }

}