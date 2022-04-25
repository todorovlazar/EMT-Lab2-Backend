package com.example.lab2.service.impl;

import com.example.lab2.exceptions.CountryNotFoundException;
import com.example.lab2.model.Country;
import com.example.lab2.repository.CountryRepository;
import com.example.lab2.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow( () -> new CountryNotFoundException(id));
    }

    @Override
    public Country findByName(String name) {
        return this.countryRepository.findByNameLike(name);
    }

    @Override
    public Country save(String name, String continent) {
        Country country = new Country(name, continent);
        return this.countryRepository.save(country);
    }

    @Override
    public Country edit(Long id, String name, String continent) {
        Country country = this.findById(id);
        country.setName(name); country.setContinent(continent);
        return this.countryRepository.save(country);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
