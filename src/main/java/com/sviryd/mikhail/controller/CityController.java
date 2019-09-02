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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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
    @ResponseStatus(HttpStatus.CREATED)
    public City save(@Valid @RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("/{id}")
    public City update(@Valid @RequestBody City city, @PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        cityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found."));
        city.setId(id);
        return cityService.save(city);
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        return cityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found."));
    }

    @GetMapping("/filtering/byName/{name}")
    public City findByName(@PathVariable String name) {
        Optional<City> cityOptional = cityService.findByName(name);
        return cityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable Long id) {
        cityService.deleteById(id);
    }
}
