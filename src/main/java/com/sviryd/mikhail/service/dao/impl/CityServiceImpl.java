package com.sviryd.mikhail.service.dao;

import com.sviryd.mikhail.repos.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService extends Service {
    @Autowired
    private CityRepo cityRepo;

}
