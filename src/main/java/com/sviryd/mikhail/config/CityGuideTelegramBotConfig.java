package com.sviryd.mikhail.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:telegram.properties")
@ConfigurationProperties(prefix = "bot.city-guide")
public class CityGuideTelegramBotConfig {
    private String username;
    private String token;
}
