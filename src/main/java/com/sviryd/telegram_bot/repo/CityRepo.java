package com.sviryd.telegram_bot.repo;

import com.sviryd.telegram_bot.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepo extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
}
