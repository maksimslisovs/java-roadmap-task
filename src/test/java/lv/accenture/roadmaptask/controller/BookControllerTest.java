package lv.accenture.roadmaptask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.entity.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsInstanceOf.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BookControllerTest {
    private ModelMap modelMap;

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

//    @Before
//    public void setUp() {
//        controller = new BookController();
        //mockLibraryService = mock(LibraryService.class);
        //ReflectionTestUtils.setField(controller, "libraryService", mockLibraryService);

        //modelMap = new ModelMap();

        //MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void listBooksTest() {
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);
        List<Book> booksList= new ArrayList<>();
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
    public void saveBook () throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/add/book")
                        .param("title", "KatieK")
                        .param("authorName", "kat@exmaple.com");
        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl(
                        "/books"));

    }

  /*  @Test
    public void shouldProcessSaveAction() throws Exception {

        // given
        BindingResult result = mock(BindingResult.class);
        ExampleEntity exampleEntity = mock(ExampleEntity.class);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

        given(editorProcessor.processSaveAction("confirmed", exampleEntity, result, httpServletRequest)).willReturn(true);

        // when
        ResultActions perform = mockMvc.perform(post("/").sessionAttr("exampleEntity", exampleEntity)
                .param("id", "123456"
                        .param("action","save"));

        // then
        perform.andDo(print())
                .andExpect(status().isOk());

    }*/

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//        public void saveBookTest() throws Exception {
//            mockMvc.perform(post("add/book")
//                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                    .param("title", "title")
//                    .param("authorName", "authorName"))
//                    .andExpect(status().is3xxRedirection())
//                    .andExpect(header().string("Location", "/books")
//                    );
//        }

//    Book newBook = new Book(1L, "Spring Boot Guide", "mkyong", new BigDecimal("2.99"));
//    when(mockRepository.save(any(Book.class))).thenReturn(newBook);
//
//        mockMvc.perform(post("/books")
//                .content(om.writeValueAsString(newBook))
//            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//            /*.andDo(print())*/
//            .andExpect(status().isCreated())
//            .andExpect(jsonPath("$.id", is(1)))
//            .andExpect(jsonPath("$.name", is("Spring Boot Guide")))
//            .andExpect(jsonPath("$.author", is("mkyong")))
//            .andExpect(jsonPath("$.price", is(2.99)));
//
//    verify(mockRepository, times(1)).save(any(Book.class));
//
//}

//    @Test
//    public void updateEmployeeAPI() throws Exception
//    {
//        mvc.perform( MockMvcRequestBuilders
//                .put("/employees/{id}", 2)
//                .content(asJsonString(new EmployeeVO(2, "firstName2", "lastName2", "email2@mail.com")))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email2@mail.com"));
//    }
//    @RequestMapping(value = "add/book", method = RequestMethod.POST)
//    public ModelAndView saveBook(@ModelAttribute Book book) {
//        bookRepository.save(book);
//        return new ModelAndView("redirect:/books");
//    }
//
//    @Test
//    public void addBook() throws Exception {
//        mockMvc.perform(post("add/book")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("title", "Test_title")
//                .param("authorName", "Test_author"))
//                .andExpect(status().is3xxRedirection());
//
//    }
//
//
//
}