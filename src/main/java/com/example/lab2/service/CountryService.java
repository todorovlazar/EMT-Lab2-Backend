package com.example.lab2.service;

import com.example.lab2.model.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country findById(Long id);

    Country findByName(String name);

    Country save(String name, String continent);

    Country edit(Long id, String name, String continent);

    void deleteById(Long id);

}
