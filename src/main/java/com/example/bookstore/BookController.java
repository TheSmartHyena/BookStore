package com.example.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/author/{authorId}/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorsRepository authorsRepository;

    @GetMapping
    public ResponseEntity<?> findAllBooks(@PathVariable Long authorId) {
        Optional<Author> item = authorsRepository.findById(authorId);

        if (item.isPresent()) {
            return ResponseEntity.ok(item.get().getBooks());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addBookToAuthor(@PathVariable Long authorId, @RequestBody Book book) {
        return authorsRepository.findById(authorId).map(author -> {
            author.getBooks().add(book); // Assuming you have properly initialized the list in the Author entity

            bookRepository.save(book);
            return ResponseEntity.ok(book); // Respond with the created book
        }).orElse(ResponseEntity.notFound().build());
    }
}
