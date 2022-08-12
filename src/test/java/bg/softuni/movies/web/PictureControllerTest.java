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
public class PictureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PictureController pictureController;

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void testPictureAddFormWithCorrectStatus() throws Exception {
        this.mockMvc.perform(get("/pictures/add-picture").secure(true)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void testPictureGetAttribute() throws Exception {
        mockMvc.perform(
                get("/pictures/add-picture")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("movieTitles")).
                andExpect(model().attributeExists("actorNames")).
                andExpect(view().name("add-picture"));
    }
}
