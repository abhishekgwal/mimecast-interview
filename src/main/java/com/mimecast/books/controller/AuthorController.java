package com.mimecast.books.controller;

import com.mimecast.books.model.Author;
import com.mimecast.books.model.Book;
import com.mimecast.books.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/{authorName}")
    public ResponseEntity<Author> createAuthor(@PathVariable String authorName) {
        authorService.createAuthor(authorName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{authorName}")
    public ResponseEntity<List<Author>> getAuthorBooks(@PathVariable String authorName) {

        List<Author> booksByAuthorName = authorService.getAuthorBooks(authorName);
        return ResponseEntity.ok(booksByAuthorName);
    }
}

