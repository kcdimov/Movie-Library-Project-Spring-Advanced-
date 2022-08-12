package bg.softuni.movies.web;


import bg.softuni.movies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@ContextConfiguration(locations = "classpath*:/spring/applicationContext*.xml")
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/register")).
                andExpect(status().isOk()).
                andExpect(view().name("register"));
    }

    @Test
    void testUserRegistration() throws Exception {
        mockMvc.perform(post("/register").
                param("username", "peter").
                param("email", "peter@abv.bg").
                param("firstName", "Peter").
                param("lastName", "McGrane").
                param("password", "1234").
                param("confirmPassword", "1234").
                with(csrf())
        ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/home"));

    }
}
