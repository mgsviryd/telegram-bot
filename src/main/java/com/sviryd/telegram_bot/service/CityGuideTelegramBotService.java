package com.sviryd.telegram_bot.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CityGuideTelegramBotService {
    void onUpdateReceived(Update update);
    String getBotUsername();
    String getBotToken();
}
