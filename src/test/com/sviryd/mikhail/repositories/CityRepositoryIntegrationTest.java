package com.sviryd.mikhail.repositories;

import com.sviryd.mikhail.entity.City;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("/application-integrationtest.properties")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CityRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void whenFindByName_thenReturnCity() {
        City city = new City();
        String name = "Moscow";
        city.setName(name);
        entityManager.persist(city);
        entityManager.flush();

        final Optional<City> cityOptional = cityRepository.findByName(name);
        Assert.assertTrue(cityOptional.isPresent());

        Assertions.assertThat(cityOptional.get().getName()).isEqualTo(name);
    }
}