package com.sviryd.telegram_bot.service.impl;


import com.sviryd.telegram_bot.entity.City;
import com.sviryd.telegram_bot.repo.CityRepo;
import com.sviryd.telegram_bot.service.CityService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {CityService.class})
@ActiveProfiles("it")
public class CityServiceIT {
    @Autowired
    private CityService cityService;
    @MockBean
    private CityRepo cityRepo;

    private City city;

    @BeforeEach
    public void initCity() {
        Long id = 1L;
        String name = "Moscow";
        String information = "Moscow in the capital of Russia.";
        this.city = new City(id, name, information);
    }

    @Test
    public void autowiredCityService() {
        Assertions.assertThat(cityService).isNotNull();
    }

    @Test
    public void autowiredCityRepository() {
        Assertions.assertThat(cityRepo).isNotNull();
    }

    @Test
    public void testSave() {
        when(cityService.save(city)).thenReturn(city);
    }

    @Test
    public void testFindById() {
        Mockito.doReturn(Optional.of(new City()))
                .when(cityRepo)
                .findById(city.getId());
        Optional<City> cityOptional = cityService.findById(city.getId());
        assertTrue(cityOptional.isPresent());
        verify(cityRepo, Mockito.times(1)).findById(city.getId());
    }

    @Test
    public void testFindByIdFail() {
        Optional<City> cityOptional = cityService.findById(city.getId());
        assertFalse(cityOptional.isPresent());
        verify(cityRepo, Mockito.times(1)).findById(city.getId());
    }

    @Test
    public void testDeleteById() {
        cityService.deleteById(city.getId());
        verify(cityRepo, Mockito.times(1)).deleteById(city.getId());
    }

    @Test
    public void testFindByName() {
        Mockito.doReturn(Optional.of(new City()))
                .when(cityRepo)
                .findByName(city.getName());
        Optional<City> cityOptional = cityService.findByName(city.getName());
        assertTrue(cityOptional.isPresent());
        verify(cityRepo, Mockito.times(1)).findByName(city.getName());
    }

    @Test
    public void testFindByNameFail() {
        Optional<City> cityOptional = cityService.findByName(city.getName());
        assertFalse(cityOptional.isPresent());
        verify(cityRepo, Mockito.times(1)).findByName(city.getName());
    }
}