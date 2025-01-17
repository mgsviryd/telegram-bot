package com.sviryd.telegram_bot;

import com.sviryd.telegram_bot.service.telegram.bot.CityGuideTelegramBotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class Application implements CommandLineRunner {
    static {
        ApiContextInitializer.init();
    }

    private final CityGuideTelegramBotServiceImpl service;

    @Autowired
    public Application(CityGuideTelegramBotServiceImpl service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(service);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}