package com.example.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorsRepository repo;

    @GetMapping
    public List<Author> findAllAuthors() {
        List<Author> list = repo.findAll();

        return list;
    }
}