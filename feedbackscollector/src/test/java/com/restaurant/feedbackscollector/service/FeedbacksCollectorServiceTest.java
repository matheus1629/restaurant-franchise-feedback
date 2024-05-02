package com.restaurant.feedbackscollector.service;

import com.restaurant.feedbackscollector.dto.FeedbackDto;
import com.restaurant.feedbackscollector.dto.FeedbackStorageDto;
import com.restaurant.feedbackscollector.exception.ResourceNotFoundException;
import com.restaurant.feedbackscollector.model.RestaurantEntity;
import com.restaurant.feedbackscollector.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

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

    @Test
    void testSendFeedback() throws ExecutionException, InterruptedException, TimeoutException {
        // SETUP
        Integer idRestaurant = 1;
        FeedbackDto feedbackDto = mock(FeedbackDto.class);
        when(feedbackDto.getIdRestaurant()).thenReturn(idRestaurant);
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
    void sendFeedback_ResourceNotFoundException() {
        // SETUP
        Integer idRestaurant = 1;
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setIdRestaurant(idRestaurant);
        when(restaurantRepository.findById(idRestaurant)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> feedbacksCollectorService.sendFeedback(feedbackDto));
    }


}