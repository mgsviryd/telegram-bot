
package com.sviryd.mikhail.service.repos.impl;

import com.sviryd.mikhail.entity.City;
import com.sviryd.mikhail.repositories.CityReporitory;
import com.sviryd.mikhail.service.CityService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceTest {
    @Autowired
    private CityService cityService;
    @MockBean
    private CityReporitory cityReporitory;

    @Test
    public void autowired(){
        Assertions.assertThat(cityService).isNotNull();
    }

    @Test
    public void testSave() throws Exception {
        City city = new City();
        city.setName("Moscow");
        City save = cityService.save(city);
        Assert.assertNotNull(save);
        Mockito.verify(cityReporitory, Mockito.times(1)).save(city);
    }

    @Test
    public void testSaveFailAlreadyExists() throws Exception {
        City city = new City();
        String name = "Moscow";
        city.setName(name);
        Mockito.doReturn(Optional.of(new City()))
                .when(cityReporitory)
                .findByName(name);
        City save = cityService.save(city);
        Assert.assertNull(save);
        Mockito.verify(cityReporitory, Mockito.times(1)).save(city);
    }

    @Test
    public void testFindById() throws Exception {
        City city = new City();
        Long id = 1L;
        city.setId(id);
        Mockito.doReturn(Optional.of(new City()))
                .when(cityReporitory)
                .findById(id);
        Optional<City> cityOptional = cityService.findById(id);
        Assert.assertTrue(cityOptional.isPresent());
        Mockito.verify(cityReporitory, Mockito.times(1)).findById(id);
    }

    @Test
    public void testFindByIdFail() throws Exception {
        City city = new City();
        Long id = 1L;
        city.setId(id);
        Optional<City> cityOptional = cityService.findById(id);
        Assert.assertFalse(cityOptional.isPresent());
        Mockito.verify(cityReporitory, Mockito.times(1)).findById(id);
    }

    @Test
    public void testDeleteById() throws Exception {
        Long id = 1L;
        cityService.deleteById(id);
        Mockito.verify(cityReporitory, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testFindByName() throws Exception {
        City city = new City();
        String name = "Moscow";
        city.setName(name);
        Mockito.doReturn(Optional.of(new City()))
                .when(cityReporitory)
                .findByName(name);
        Optional<City> cityOptional = cityService.findByName(name);
        Assert.assertTrue(cityOptional.isPresent());
        Mockito.verify(cityReporitory, Mockito.times(1)).findByName(name);
    }
    @Test
    public void testFindByNameFail() throws Exception {
        City city = new City();
        String name = "Moscow";
        city.setName(name);
        Optional<City> cityOptional = cityService.findByName(name);
        Assert.assertFalse(cityOptional.isPresent());
        Mockito.verify(cityReporitory, Mockito.times(1)).findByName(name);
    }
}