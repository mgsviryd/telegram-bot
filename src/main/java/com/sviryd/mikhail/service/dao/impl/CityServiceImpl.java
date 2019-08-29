package com.sviryd.mikhail.service.dao.impl;

import com.sviryd.mikhail.dao.entity.City;
import com.sviryd.mikhail.repos.CityRepo;
import com.sviryd.mikhail.service.dao.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepo cityRepo;

    @Override
    public City save(City city) {
        return cityRepo.save(city);
    }

    @Override
    public Iterable<City> saveAll(Iterable<City> cities) {
        return cityRepo.saveAll(cities);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepo.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return cityRepo.existsById(id);
    }

    @Override
    public Iterable<City> findAll() {
        return cityRepo.findAll();
    }

    @Override
    public Iterable<City> findAllById(Iterable<Long> ids) {
        return cityRepo.findAllById(ids);
    }

    @Override
    public long count() {
        return cityRepo.count();
    }

    @Override
    public void deleteById(Long id) {
        cityRepo.deleteById(id);
    }

    @Override
    public void delete(City city) {
        cityRepo.delete(city);
    }

    @Override
    public void deleteAll(Iterable<City> cities) {
        cityRepo.deleteAll(cities);
    }

    @Override
    public void deleteAll() {
        cityRepo.deleteAll();
    }
}
