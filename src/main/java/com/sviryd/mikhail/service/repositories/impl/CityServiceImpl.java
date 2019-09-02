package com.sviryd.mikhail.service.repos.impl;

import com.sviryd.mikhail.entity.City;
import com.sviryd.mikhail.repositories.CityReporitory;
import com.sviryd.mikhail.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityReporitory cityReporitory;

    @Autowired
    public CityServiceImpl(CityReporitory cityReporitory) {
        this.cityReporitory = cityReporitory;
    }

    @Override
    public City save(City city) {
        return cityReporitory.save(city);
    }

    @Override
    public Iterable<City> saveAll(Iterable<City> cities) {
        return cityReporitory.saveAll(cities);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityReporitory.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return cityReporitory.existsById(id);
    }

    @Override
    public Iterable<City> findAll() {
        return cityReporitory.findAll();
    }

    @Override
    public Iterable<City> findAllById(Iterable<Long> ids) {
        return cityReporitory.findAllById(ids);
    }

    @Override
    public long count() {
        return cityReporitory.count();
    }

    @Override
    public void deleteById(Long id) {
        cityReporitory.deleteById(id);
    }

    @Override
    public void delete(City city) {
        cityReporitory.delete(city);
    }

    @Override
    public void deleteAll(Iterable<City> cities) {
        cityReporitory.deleteAll(cities);
    }

    @Override
    public void deleteAll() {
        cityReporitory.deleteAll();
    }

    @Override
    public Optional<City> findByName(String name) {
        return cityReporitory.findByName(name);
    }
}
