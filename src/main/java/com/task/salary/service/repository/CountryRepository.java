package com.task.salary.service.repository;

import com.task.salary.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    CountryEntity findByName(String name);

    CountryEntity saveAndFlush(CountryEntity countryEntity);

}
