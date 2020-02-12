package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.db.UserDAO;
import lv.accenture.roadmaptask.entity.Book;
import lv.accenture.roadmaptask.entity.User;
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
public class UserControllerTest {

    @Mock
    UserDAO userDAO;

    @InjectMocks
    UserController controller;

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
    public void userListPageTest() {
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        when(userDAO.findAll()).thenReturn(userList);

        ModelAndView modelAndView = controller.userListPage();

        assertEquals("users", modelAndView.getViewName());
    }

    @Test
    public void saveUserTest() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/add/user")
                        .param("username", "TestUsername");
        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl(
                        "/users"));

    }

    @Test
    public void updateUserPageTest() throws Exception {
        mockMvc.perform(get("/update/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("userUpdate"));
    }

    @Test
    public void updateUserTest() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/update/user")
                        .param("userId", "4")
                        .param("username", "TestUsernameUpdate");
        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl(
                        "/users"));
    }
}
