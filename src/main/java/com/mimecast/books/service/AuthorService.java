package com.mimecast.books.service;

import com.mimecast.books.model.Author;
import com.mimecast.books.model.Book;
import com.mimecast.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthorService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${books.api.url}")
    private String booksApiUrl;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createAuthor(String authorName) {

        Author getAuthorBooks = restTemplate.getForObject(booksApiUrl + "?author=" + authorName + "&api-key="+apiKey, Author.class);
        List<Book> getAuthorBooksList = null;

        if (getAuthorBooks != null) {
            getAuthorBooksList = getAuthorBooks.getBooks();
        }

        Author author = new Author();
        author.setAuthorName(authorName);
        author.setBooks(getAuthorBooksList);
        bookRepository.save(author);
    }

    public List<Author> getAuthorBooks(String authorName) {
        return bookRepository.findByAuthorName(authorName);
    }

}

