package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.entity.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BookControllerTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookController controller;

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();

    }

    @Test
    public void listBooksTest() {
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);
        List<Book> booksList = new ArrayList<>();
        booksList.add(book1);
        booksList.add(book2);
        when(bookRepository.findAll()).thenReturn(booksList);

        ModelAndView modelAndView = controller.listBooks();

        assertEquals("books", modelAndView.getViewName());
    }

    @Test
    public void addBookTest() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"));
    }


    @Test
    public void saveBookTest() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/add/book")
                        .param("title", "TestTitle")
                        .param("authorName", "TestAuthor");
        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl(
                        "/books"));

    }

    @Test
    public void deleteBookTest() throws Exception {
        this.mockMvc.perform(get("/delete/book/{id}", 3))
                .andExpect(MockMvcResultMatchers.redirectedUrl(
                        "/books"));
    }

    @Test
    public void updatePageTest() throws Exception {
        mockMvc.perform(get("/update/book/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(view().name("update"));
    }

    @Test
    public void updateBookTest() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/update/book")
                        .param("id", "2")
                        .param("title", "TestTitleUpdate")
                        .param("aname", "TestAuthorUpdate");
        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl(
                        "/books"));
    }

    @Test
    public void returnBookTest() throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/return/book/{id}", 2);
        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl(
                        "/books"));
    }
}