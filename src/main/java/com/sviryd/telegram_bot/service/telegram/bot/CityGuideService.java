package com.sviryd.telegram_bot.service.telegram.bot;

import com.sviryd.telegram_bot.entity.City;
import com.sviryd.telegram_bot.service.CityService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class CityGuideService {
    private static final String CITY_NOT_FOUND_MSG = "The city {0} is not found.";
    private final CityService cityService;

    public CityGuideService(CityService cityService) {
        this.cityService = cityService;
    }

    public String getCityInformation(String messageText) {
        final Optional<City> city = cityService.findByName(messageText);
        return city
                .map(City::getInformation)
                .orElseGet(() -> MessageFormat.format(CITY_NOT_FOUND_MSG, messageText));
    }
}
