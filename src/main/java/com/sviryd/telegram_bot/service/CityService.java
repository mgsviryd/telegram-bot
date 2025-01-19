package com.sviryd.telegram_bot.service;

import com.sviryd.telegram_bot.entity.City;
import com.sviryd.telegram_bot.repo.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
    private final CityRepo cityRepo;

    @Autowired
    public CityService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public City save(City city) {
        return cityRepo.save(city);
    }

    public Iterable<City> saveAll(Iterable<City> cities) {
        return cityRepo.saveAll(cities);
    }

    public Optional<City> findById(Long id) {
        return cityRepo.findById(id);
    }

    public boolean existsById(Long id) {
        return cityRepo.existsById(id);
    }

    public Iterable<City> findAll() {
        return cityRepo.findAll();
    }

    public Iterable<City> findAllById(Iterable<Long> ids) {
        return cityRepo.findAllById(ids);
    }

    public long count() {
        return cityRepo.count();
    }

    public void deleteById(Long id) {
        cityRepo.deleteById(id);
    }

    public void delete(City city) {
        cityRepo.delete(city);
    }

    public void deleteAll(Iterable<City> cities) {
        cityRepo.deleteAll(cities);
    }

    public void deleteAll() {
        cityRepo.deleteAll();
    }

    public Optional<City> findByName(String name) {
        return cityRepo.findByName(name);
    }
}
