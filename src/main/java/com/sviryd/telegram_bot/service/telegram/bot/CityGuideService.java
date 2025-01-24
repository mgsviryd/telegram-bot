package com.sviryd.telegram_bot.service.telegram.bot;

import com.sviryd.telegram_bot.entity.City;
import com.sviryd.telegram_bot.service.CityService;
import com.sviryd.telegram_bot.service.MessageI18nService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Optional;

@Service
public class CityGuideService {
    private final CityService cityService;
    private final MessageI18nService messageI18nService;

    public CityGuideService(CityService cityService, MessageI18nService messageI18nService) {
        this.cityService = cityService;
        this.messageI18nService = messageI18nService;
    }

    public String getCityInfo(String name, Locale locale) {
        final Optional<City> city = cityService.findByName(name);
        String info = city
                .map(City::getInfo)
                .orElseGet(() ->
                        MessageFormat.format(
                                messageI18nService.getMessage(
                                        "bot.city-guide.city-not-found",
                                        new Object[]{},
                                        locale
                                ),
                                name
                        ));
        return info;
    }
}
