package com.sviryd.mikhail.service.dao;

import com.sviryd.mikhail.dao.entity.City;

import java.util.Optional;

public interface CityService {
    City save(City var1);

    Iterable<City> saveAll(Iterable<City> var1);

    Optional<City> findById(Long var1);

    boolean existsById(Long var1);

    Iterable<City> findAll();

    Iterable<City> findAllById(Iterable<Long> var1);

    long count();

    void deleteById(Long var1);

    void delete(City var1);

    void deleteAll(Iterable<City> var1);

    void deleteAll();
}
