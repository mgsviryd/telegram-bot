package com.sviryd.telegram_bot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sviryd.telegram_bot.config.telegram.CityGuideBotTelegramConfig;
import com.sviryd.telegram_bot.entity.City;
import com.sviryd.telegram_bot.service.telegram.bot.CityGuideTelegramBotService;
import com.sviryd.telegram_bot.vo.TelegramUpdateVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Тестовый класс для проверки функциональности REST контроллера `CityRestController`.
 *
 * Конфигурация:
 * - Используется `SpringBootTest` с случайным портом.
 * - `@AutoConfigureMockMvc` для тестирования с помощью MockMvc.
 * - База данных инициализируется с помощью SQL-скриптов до и после каждого теста.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TelegramBotRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityGuideTelegramBotService cityGuideTelegramBotService;

    @Autowired
    private CityGuideBotTelegramConfig botConfig;

    @BeforeEach
    public void beforeEach() {
    }


    @Test
    void testMgsvirydBot() throws Exception {
        // Prepare test data
        TelegramUpdateVO mockUpdate = new TelegramUpdateVO();
        ObjectMapper om = new ObjectMapper();
        String requestJson = om.writeValueAsString(mockUpdate);

        // Perform the request and assert the result
        mockMvc.perform(post("/webhook/mgsviryd_bot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Update received"));

        // Verify service interaction
        Mockito.verify(cityGuideTelegramBotService).processUpdate(botConfig.getToken(), mockUpdate);
    }
}