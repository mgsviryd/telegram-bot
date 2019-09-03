package com.sviryd.mikhail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sviryd.mikhail.entity.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-it.yml")
@Sql(value = {"/init-db-city-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/cleanup-db-city-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CityControllerIT {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CityController cityController;
    private City city;

    @Before
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
        assertThat(cityController).isNotNull();
    }

    @Test
    public void testSave() throws Exception {
        mvc.perform(post("/cities")
                .content(new ObjectMapper().writeValueAsString(city))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());
    }

    @Test
    public void testSaveFailNull() throws Exception {
        mvc.perform(post("/cities")
                .content(new ObjectMapper().writeValueAsString(null))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSaveFailValidation() throws Exception {
        city.setInformation(null);
        mvc.perform(post("/cities")
                .content(new ObjectMapper().writeValueAsString(city))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

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

    @Test
    public void testFindByIdFailNotFound() throws Exception {
        mvc.perform(get("/cities/{id}", Long.MAX_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

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

    @Test
    public void testDeleteById() throws Exception {
        mvc.perform(delete("/cities/{id}", city.getId()))
                .andExpect(status().isAccepted());
    }
}