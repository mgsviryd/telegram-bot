package com.sviryd.mikhail.controller;

import com.sviryd.mikhail.dao.entity.City;
import com.sviryd.mikhail.service.dao.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody City city) {
        cityService.save(city);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<City> update(@RequestBody City city, @PathVariable Long id) {
        final Optional<City> storedCity = cityService.findById(id);
        if (!storedCity.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        city.setId(id);
        final City savedCity = cityService.save(city);
        return new ResponseEntity<>(savedCity, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<City> findById(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city.get(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<City> findByName(@PathVariable String name) {
        final City city = cityService.findByName(name);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        cityService.deleteById(id);
    }
}
