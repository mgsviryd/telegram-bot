
package com.sviryd.mikhail.service.impl;

import com.sviryd.mikhail.entity.City;
import com.sviryd.mikhail.repositories.CityRepository;
import com.sviryd.mikhail.service.CityService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceIT {
    @Autowired
    private CityService cityService;
    @MockBean
    private CityRepository cityRepository;

    private City city;

    @Before
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
        Assertions.assertThat(cityRepository).isNotNull();
    }

    @Test
    public void testSave() {
        when(cityService.save(city)).thenReturn(city);
    }

    @Test
    public void testFindById() {
        Mockito.doReturn(Optional.of(new City()))
                .when(cityRepository)
                .findById(city.getId());
        Optional<City> cityOptional = cityService.findById(city.getId());
        assertTrue(cityOptional.isPresent());
        verify(cityRepository, Mockito.times(1)).findById(city.getId());
    }

    @Test
    public void testFindByIdFail() {
        Optional<City> cityOptional = cityService.findById(city.getId());
        assertFalse(cityOptional.isPresent());
        verify(cityRepository, Mockito.times(1)).findById(city.getId());
    }

    @Test
    public void testDeleteById() {
        cityService.deleteById(city.getId());
        verify(cityRepository, Mockito.times(1)).deleteById(city.getId());
    }

    @Test
    public void testFindByName() {
        Mockito.doReturn(Optional.of(new City()))
                .when(cityRepository)
                .findByName(city.getName());
        Optional<City> cityOptional = cityService.findByName(city.getName());
        assertTrue(cityOptional.isPresent());
        verify(cityRepository, Mockito.times(1)).findByName(city.getName());
    }

    @Test
    public void testFindByNameFail() {
        Optional<City> cityOptional = cityService.findByName(city.getName());
        assertFalse(cityOptional.isPresent());
        verify(cityRepository, Mockito.times(1)).findByName(city.getName());
    }
}