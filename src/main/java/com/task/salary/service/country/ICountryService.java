package com.task.salary.service.country;

import com.task.salary.model.CountryEntity;

import java.util.List;

public interface ICountryService {

    public List<CountryEntity> getCountries();

    public CountryEntity getCountryByName(String name);

    public CountryEntity save(CountryEntity countryEntity);

}
