package com.example.lab2.web.rest;

import com.example.lab2.model.Book;
import com.example.lab2.model.Category;
import com.example.lab2.service.AuthorService;
import com.example.lab2.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://libraryapplicationfrontend.herokuapp.com/")
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    private List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestParam String name,
                                     @RequestParam Category category,
                                     @RequestParam List<Long> authors,
                                     @RequestParam Integer copies){
        return this.bookService.save(name, category, authors, copies)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id,
                                     @RequestParam String name,
                                     @RequestParam Category category,
                                     @RequestParam List<Long> authors,
                                     @RequestParam Integer copies){
        return this.bookService.edit(id, name, category, authors, copies)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet( () -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/markAsTaken/{id}")
    public Book markAsTaken(@PathVariable Long id){
        return this.bookService.markAsTaken(id);
    }
}
