package com.sviryd.telegram_bot.config.telegram;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegram.city-guide-bot")
public class CityGuideBotTelegramConfig {
    private String token;
    private String username;
}
