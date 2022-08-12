package bg.softuni.movies.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewController reviewController;

    @Test
    @WithMockUser(username = "peter", password = "1234", roles = "USER")
    public void testReviewsWhetherReturnCorrectStatusCode() throws Exception {
        this.mockMvc.perform(get("/reviews/add-review").secure(true)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void testReviewGetAttribute() throws Exception {
        mockMvc.perform(
                get("/reviews/add-review")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("movieTitles")).
                andExpect(view().name("add-review"));
    }
}
