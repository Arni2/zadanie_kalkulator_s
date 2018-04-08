package com.task.salary.service.country.impl;

import com.task.salary.model.CountryEntity;
import com.task.salary.service.country.ICountryService;
import com.task.salary.service.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements ICountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<CountryEntity> getCountries() {
        return countryRepository.findAll();
    }

    @Override
    public CountryEntity getCountryByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public CountryEntity save(CountryEntity countryEntity) {
        return countryRepository.save(countryEntity);
    }
}
