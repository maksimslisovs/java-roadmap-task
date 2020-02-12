package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.db.UserDAO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AssignControllerTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    UserDAO userDAO;

    @InjectMocks
    AssignController controller;

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
    public void getBookTest() throws Exception {
        mockMvc.perform(get("/book/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(view().name("book"));
    }

    @Test
    public void assignUserTest() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/assign/user")
                        .param("id", "2")
                        .param("title", "TestTitleUpdate")
                        .param("aname", "TestAuthorUpdate")
                        .param("userId", "4")
                        .param("username", "TestAuthorUpdate");
        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl(
                        "/books"));
    }
}
