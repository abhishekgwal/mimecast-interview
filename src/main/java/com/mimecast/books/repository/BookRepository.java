package com.mimecast.books.repository;

import com.mimecast.books.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Author, String> {
    List<Author> findByAuthorName(String name);
}

