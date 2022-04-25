package com.example.lab2.service.impl;

import com.example.lab2.exceptions.BookNotFoundException;
import com.example.lab2.model.Author;
import com.example.lab2.model.Book;
import com.example.lab2.model.Category;
import com.example.lab2.repository.AuthorRepository;
import com.example.lab2.repository.BookRepository;
import com.example.lab2.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByNameLike(name);
    }

    @Override
    public Optional<Book> save(String name, Category category, List<Long> authors, Integer availableCopies) {
        List<Author> authorList = this.authorRepository.findAllById(authors);
        Book book = new Book(name, category, authorList, availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, List<Long> authors, Integer availableCopies) {
        List<Author> authorList = this.authorRepository.findAllById(authors);
        Book book = this.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        book.setName(name); book.setCategory(category);
        book.setAuthors(authorList); book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Book markAsTaken(Long id) {
        Book book = this.findById(id).orElseThrow( () -> new BookNotFoundException(id));
        Integer tmp = book.getAvailableCopies()-1;
        if(tmp<=0){
            book.setAvailableCopies(0);
        }
        else
            book.setAvailableCopies(tmp);
        return this.bookRepository.save(book);
    }
}
