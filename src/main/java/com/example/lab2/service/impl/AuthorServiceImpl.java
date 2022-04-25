package com.example.lab2.service.impl;

import com.example.lab2.exceptions.AuthorNotFoundException;
import com.example.lab2.exceptions.CountryNotFoundException;
import com.example.lab2.model.Author;
import com.example.lab2.model.Country;
import com.example.lab2.repository.AuthorRepository;
import com.example.lab2.repository.CountryRepository;
import com.example.lab2.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }


    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return this.authorRepository.findByNameLike(name);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long country) {
        Country _country = this.countryRepository.findById(country).orElseThrow( () -> new CountryNotFoundException(country));
        Author author = new Author(name, surname, _country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long country) {
        Country _country = this.countryRepository.findById(country).orElseThrow( () -> new CountryNotFoundException(country));
        Author author = this.findById(id).orElseThrow( () -> new AuthorNotFoundException(id));
        author.setName(name); author.setSurname(surname);
        author.setCountry(_country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
