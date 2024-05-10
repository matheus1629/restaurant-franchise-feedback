package com.restaurant.feedbackscollector.service;

import com.restaurant.feedbackscollector.dto.FeedbackDto;
import com.restaurant.feedbackscollector.dto.FeedbackStorageDto;
import com.restaurant.feedbackscollector.exception.ResourceNotFoundException;
import com.restaurant.feedbackscollector.model.RestaurantEntity;
import com.restaurant.feedbackscollector.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class FeedbacksCollectorService {

    private final StreamBridge streamBridge;
    private final RestaurantRepository restaurantRepository;
    private final String channel = "sendFeedback-out-0";

    public void sendFeedback(FeedbackDto feedbackDto) {

        RestaurantEntity restaurantData = getRestaurantInfo(feedbackDto.getIdRestaurant());

        FeedbackStorageDto feedbackStorageDto = new FeedbackStorageDto(
                feedbackDto.getAge(), feedbackDto.getGender(), feedbackDto.getRating(),
                feedbackDto.getMealQuality(), feedbackDto.getWrongOrder(), feedbackDto.getWaitingTime(),
                feedbackDto.getService(), feedbackDto.getAmbience(), LocalDate.now().toString(), restaurantData.getIdRestaurant(),
                restaurantData.getRegion(), restaurantData.getState(), restaurantData.getCity(), restaurantData.getCep()
        );

        streamBridge.send(channel, feedbackStorageDto);
    }

    private RestaurantEntity getRestaurantInfo(Integer idRestaurant) {
        return restaurantRepository.findById(idRestaurant)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant of id " + idRestaurant + " was not found."));
    }
}

