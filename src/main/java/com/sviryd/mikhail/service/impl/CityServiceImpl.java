package com.sviryd.mikhail.service.impl;

import com.sviryd.mikhail.entity.City;
import com.sviryd.mikhail.repositories.CityRepository;
import com.sviryd.mikhail.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Iterable<City> saveAll(Iterable<City> cities) {
        return cityRepository.saveAll(cities);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return cityRepository.existsById(id);
    }

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Iterable<City> findAllById(Iterable<Long> ids) {
        return cityRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return cityRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void delete(City city) {
        cityRepository.delete(city);
    }

    @Override
    public void deleteAll(Iterable<City> cities) {
        cityRepository.deleteAll(cities);
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }

    @Override
    public Optional<City> findByName(String name) {
        return cityRepository.findByName(name);
    }
}
