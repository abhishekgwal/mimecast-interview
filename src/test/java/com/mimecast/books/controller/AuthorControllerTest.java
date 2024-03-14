package com.mimecast.books.controller;

import com.mimecast.books.model.Author;
import com.mimecast.books.model.Book;
import com.mimecast.books.service.AuthorService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    void testCreateAuthor() throws Exception {
        String authorName = "TestAuthor";
        authorService.createAuthor(authorName);

        mockMvc.perform(post("/author/{authorName}", authorName))
                .andExpect(status().isNoContent());

        verify(authorService, times(2)).createAuthor(authorName);
    }

    @Test
    void testGetAuthorBooks() throws Exception {
        String authorName = "TestAuthor";
        List<Author> mockAuthors = List.of(createMockAuthor());

        when(authorService.getAuthorBooks(authorName)).thenReturn(mockAuthors);

        mockMvc.perform(get("/author/{authorName}", authorName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].authorName").value("TestAuthor"));

        verify(authorService, times(1)).getAuthorBooks(authorName);
    }

    private Author createMockAuthor() {
        List<Book> mockBooks = createMockBooks();
        Author author = new Author();
        author.setAuthorName("TestAuthor");
        author.setBooks(mockBooks);
        author.setId(new ObjectId());
        return author;
    }

    private List<Book> createMockBooks() {
        Book book1 = new Book("Book1", "Summary1");
        Book book2 = new Book("Book2", "Summary2");
        return Arrays.asList(book1, book2);
    }
}

