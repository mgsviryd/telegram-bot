package com.sviryd.mikhail.repos;

import com.sviryd.mikhail.entity.City;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityRepo extends CrudRepository<City, Long> {
    Optional<City> findByName(String name);
}
