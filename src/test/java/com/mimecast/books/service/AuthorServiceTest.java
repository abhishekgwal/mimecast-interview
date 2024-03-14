package com.mimecast.books.service;

import com.mimecast.books.model.Author;
import com.mimecast.books.model.Book;
import com.mimecast.books.repository.BookRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void testCreateAuthor() {
        // Arrange
        String authorName = "TestAuthor";
        String apiKey = "testApiKey";
        String booksApiUrl = "http://example.com/api/books";
        Author mockAuthorFromApi = createMockAuthorFromApi();

        lenient().when(restTemplate.getForObject(
                eq(booksApiUrl + "?author=" + authorName + "&api-key=" + apiKey),
                eq(Author.class)
        ))
                .thenReturn(mockAuthorFromApi);

        authorService.createAuthor(authorName);

        verify(restTemplate, times(1)).getForObject(anyString(), eq(Author.class));
        verify(bookRepository, times(1)).save(any(Author.class));
    }

    @Test
    void testGetAuthorBooks() {
        // Arrange
        String authorName = "TestAuthor";
        List<Author> mockAuthors = createMockAuthors();

        when(bookRepository.findByAuthorName(eq(authorName))).thenReturn(mockAuthors);

        List<Author> result = authorService.getAuthorBooks(authorName);

        verify(bookRepository, times(1)).findByAuthorName(eq(authorName));
        assertEquals(mockAuthors, result);
    }

    private Author createMockAuthorFromApi() {
        List<Book> mockBooks = createMockBooks();
        Author author = new Author();
        author.setAuthorName("TestAuthor");
        author.setBooks(mockBooks);
        author.setId(new ObjectId());
        return author;
    }

    private List<Author> createMockAuthors() {
        List<Book> mockBooks = createMockBooks();
        Author author1 = new Author(new ObjectId(), mockBooks, "TestAuthor1");
        Author author2 = new Author(new ObjectId(), mockBooks, "TestAuthor2");
        return Arrays.asList(author1, author2);
    }

    private List<Book> createMockBooks() {
        Book book1 = new Book("Book1", "Summary1");
        Book book2 = new Book("Book2", "Summary2");
        return Arrays.asList(book1, book2);
    }
}
