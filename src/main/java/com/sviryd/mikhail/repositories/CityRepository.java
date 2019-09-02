package com.sviryd.mikhail.repositories;

import com.sviryd.mikhail.entity.City;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityReporitory extends CrudRepository<City, Long> {
    Optional<City> findByName(String name);
}
