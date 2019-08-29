package com.sviryd.mikhail.controller;

import com.sviryd.mikhail.controller.exception.CityNotFoundException;
import com.sviryd.mikhail.dao.entity.City;
import com.sviryd.mikhail.service.dao.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody City city) {
        City savedCity = cityService.save(city);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody City city, @PathVariable Long id) {
        Optional<City> storedCity = cityService.findById(id);
        if (!storedCity.isPresent())
            return ResponseEntity.notFound().build();

        city.setId(id);
        cityService.save(city);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable Long id) throws CityNotFoundException {
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            throw new CityNotFoundException("id-" + id);
        }
        return city.get();
    }

    @GetMapping
    public Iterable<City> findAll() {
        return cityService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cityService.deleteById(id);
    }
}
