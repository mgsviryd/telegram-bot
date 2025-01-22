package com.sviryd.telegram_bot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sviryd.telegram_bot.controller.rest.CityRestController;
import com.sviryd.telegram_bot.entity.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
@ActiveProfiles("it")
@Sql(value = {"/init-db-city-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/cleanup-db-city-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CityRestControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CityRestController cityRestController;
    private City city;

    @BeforeEach
    public void initCity() {
        Long id = 1L;
        String name = "Moscow";
        String information = "Moscow in the capital of Russia.";
        this.city = new City(id, name, information);
    }

    @Test
    public void autoriwedMockMvc() {
        assertThat(mvc).isNotNull();
    }

    @Test
    public void autoriwedCityController() {
        assertThat(cityRestController).isNotNull();
    }

    // Тест для проверки сохранения города через REST API.
    // Ожидается, что при успешном сохранении статус ответа будет 201 (Created)
    // и в ответе будет существовать поле "id".
    @Test
    public void testSave() throws Exception {
        mvc.perform(post("/cities")
                        .content(new ObjectMapper().writeValueAsString(city))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());
    }

    /**
     * Тестовый метод, который проверяет, что попытка сохранения города
     * с нулевым содержимым возвращает статус 400 Bad Request.
     */
    @Test
    public void testSaveFailNull() throws Exception {
        mvc.perform(post("/cities")
                        .content(new ObjectMapper().writeValueAsString(null))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Тест проверяет, что при попытке сохранить город с недопустимой информацией (null)
     * возвращается статус ошибки 400 Bad Request.
     */
    @Test
    public void testSaveFailValidation() throws Exception {
        city.setInformation(null);
        mvc.perform(post("/cities")
                        .content(new ObjectMapper().writeValueAsString(city))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Тестовый метод для проверки обновления информации о городе.
     * Он выполняет два запроса PUT для обновления города и проверяет,
     * что статус ответа - 200 OK, а также что возвращаемые данные содержат
     * правильные значения идентификатора, имени и информации о городе.
     * Второй запрос также проверяет, что информация о городе изменилась.
     */
    @Test
    public void testUpdate() throws Exception {
        City oldCity = new City();
        oldCity.setId(city.getId());
        oldCity.setName(city.getName());
        oldCity.setInformation("Moscow is the biggest city in Russia.");
        mvc.perform(put("/cities/{id}", oldCity.getId())
                        .content(new ObjectMapper().writeValueAsString(oldCity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(oldCity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(oldCity.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("information").value(oldCity.getInformation()));
        mvc.perform(put("/cities/{id}", this.city.getId())
                        .content(new ObjectMapper().writeValueAsString(this.city))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(this.city.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(this.city.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("information").value(this.city.getInformation()))
                .andExpect(MockMvcResultMatchers.jsonPath("information", not(oldCity.getInformation())));
    }

    /**
     * Тестовый метод для проверки получения города по идентификатору.
     * Он выполняет GET-запрос к конечной точке "/cities/{id}",
     * ожидает статус 200 OK и проверяет, что возвращаемый JSON
     * содержит правильные значения для полей "name" и "information".
     */
    @Test
    public void testFindById() throws Exception {
        mvc.perform(get("/cities/{id}", city.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is(city.getName())))
                .andExpect(jsonPath("information", is(city.getInformation())));
    }

    /**
     * Тест проверяет, что при запросе ресурса с несуществующим идентификатором
     * сервер возвращает HTTP статус 404 (Not Found).
     */
    @Test
    public void testFindByIdFailNotFound() throws Exception {
        mvc.perform(get("/cities/{id}", Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Тест проверяет успешное выполнение фильтрации городов по имени.
     */
    @Test
    public void testFindByName() throws Exception {
        mvc.perform(get("/cities/filtering/byName/{name}", city.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is(city.getName())))
                .andExpect(jsonPath("information", is(city.getInformation())));
    }

    /**
     * Тест проверяет успешное удаление города по идентификатору.
     */
    @Test
    public void testDeleteById() throws Exception {
        mvc.perform(delete("/cities/{id}", city.getId()))
                .andExpect(status().isAccepted());
    }
}