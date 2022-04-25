package com.example.lab2.web.rest;

import com.example.lab2.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://libraryapplicationfrontend.herokuapp.com/")
@RequestMapping("/api")
public class CategoryRestController {

    @GetMapping
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        categories.add(Category.BIOGRAPHY);
        categories.add(Category.CLASSICS);
        categories.add(Category.DRAMA);
        categories.add(Category.FANTASY);
        categories.add(Category.HISTORY);
        categories.add(Category.NOVEL);
        categories.add(Category.THRILLER);
        return categories;
    }
}
