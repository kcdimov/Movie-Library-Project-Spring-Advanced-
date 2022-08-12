package bg.softuni.movies.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieController movieController;

    @Test
    @WithMockUser(username = "peter", password = "1234", roles = "USER")
    public void testMovieWithCorrectStatusCode() throws Exception {
        this.mockMvc.perform(get("/movies/all-movies").secure(true)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void testMovieIfCannotBeFound() throws Exception {
        this.mockMvc.perform(get("/movie-details/11111/").secure(true)).andExpect(status().isNotFound());
    }
}
