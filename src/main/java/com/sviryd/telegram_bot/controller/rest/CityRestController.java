package com.sviryd.telegram_bot.controller.rest;

import com.sviryd.telegram_bot.entity.City;
import com.sviryd.telegram_bot.service.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityRestController {
    private final CityService cityService;

    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City save(
            @Valid @RequestBody City city
    ) {
        return cityService.save(city);
    }

    @PutMapping("/{id}")
    public City update(
            @Valid @RequestBody City city,
            @PathVariable Long id
    ) {
        Optional<City> cityOptional = cityService.findById(id);
        cityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found."));
        city.setId(id);
        return cityService.save(city);
    }

    @GetMapping("/{id}")
    public City findById(
            @PathVariable Long id
    ) {
        Optional<City> cityOptional = cityService.findById(id);
        return cityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found."));
    }

    @GetMapping("/filtering/byName/{name}")
    public City findByName(
            @PathVariable String name
    ) {
        Optional<City> cityOptional = cityService.findByName(name);
        return cityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(
            @PathVariable Long id
    ) {
        cityService.deleteById(id);
    }
}
