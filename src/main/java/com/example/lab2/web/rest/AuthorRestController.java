package com.example.lab2.web.rest;

import com.example.lab2.model.Author;
import com.example.lab2.model.Book;
import com.example.lab2.model.Category;
import com.example.lab2.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping({"/", "/authors"})
    public List<Author> findAll(){
        return this.authorService.findAll();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/authors/add")
    public ResponseEntity<Author> save(@RequestParam String name,
                                        @RequestParam String surname,
                                        @RequestParam Long country){
        return this.authorService.save(name, surname, country)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/authors/edit/{id}")
    public ResponseEntity<Author> save(@PathVariable Long id,
                                       @RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam Long country){
        return this.authorService.edit(id, name, surname, country)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/authors/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.authorService.deleteById(id);
        if(this.authorService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
