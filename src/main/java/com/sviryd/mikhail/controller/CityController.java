package com.sviryd.mikhail.controller;

import com.sviryd.mikhail.entity.City;
import com.sviryd.mikhail.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public void add(@RequestBody City city) {
        cityService.save(city);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public City update(@RequestBody City city, @PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        cityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found."));
        city.setId(id);
        return cityService.save(city);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public City findById(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        return cityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found."));
    }

    @GetMapping("/{name}")
    @ResponseBody
    public City findByName(@PathVariable String name) {
        Optional<City> cityOptional = cityService.findByName(name);
        return cityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found."));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cityService.deleteById(id);
    }
}
