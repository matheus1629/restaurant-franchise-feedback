package com.restaurant.feedbackscollector.service;

import com.restaurant.feedbackscollector.dto.FeedbackDto;
import com.restaurant.feedbackscollector.dto.FeedbackStorageDto;
import com.restaurant.feedbackscollector.exception.MessageSendFailedException;
import com.restaurant.feedbackscollector.exception.ResourceNotFoundException;
import com.restaurant.feedbackscollector.model.RestaurantEntity;
import com.restaurant.feedbackscollector.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class FeedbacksCollectorService {

    private final StreamBridge streamBridge;
    private final RestaurantRepository restaurantRepository;
    private String channel = "sendFeedback-out-0";

    public void sendFeedback(FeedbackDto feedbackDto) {

        RestaurantEntity restaurantData = getRestaurantInfo(feedbackDto.getIdRestaurant());

        FeedbackStorageDto feedbackStorageDto = new FeedbackStorageDto(
                feedbackDto.getAge(), feedbackDto.getGender(), feedbackDto.getRating(),
                feedbackDto.getMealQuality(), feedbackDto.getWrongOrder(), feedbackDto.getWaitingTime(),
                feedbackDto.getService(), feedbackDto.getAmbience(), new Date(), restaurantData.getIdRestaurant(),
                restaurantData.getRegion(), restaurantData.getState(), restaurantData.getCity(), restaurantData.getCep()
        );

        boolean result = streamBridge.send(channel, feedbackStorageDto);

        if (!result) throw new MessageSendFailedException("Fail to send message to channel: " + channel);
    }

    private RestaurantEntity getRestaurantInfo(Integer idRestaurant) {
        return restaurantRepository.findById(idRestaurant)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant of id " + idRestaurant + " was not found."));
    }
}

