package bg.softuni.movies.web;


import bg.softuni.movies.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Autowired
    private LoginController loginController;

    @Test
    void testLoginPageShown() throws Exception {
        mockMvc.perform(get("/login")).
                andExpect(status().isOk()).
                andExpect(view().name("login"));
    }

    @Test
    void testUserLogin() throws Exception {
        mockMvc.perform(post("/login").
                param("username", "peter").
                param("password", "1234").
                with(csrf())
        ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/home"));

    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void testLoginGetAttribute() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                post("/login-error")).
                andExpect(status().is(403)).
//                andExpect(MockMvcResultMatchers.flash()
//                        .attribute("bad_credentials", "bad_credentials")).
                andExpect(redirectedUrl(null));
    }
}
