package bg.softuni.movies.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeController homeController;

    @Test
    public void setHomeController() throws Exception {
        assertThat(homeController).isNotNull();
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void testHomeCorrectStatusCodeToBeExpected() throws Exception {
        this.mockMvc.perform(get("/home").secure(true)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "peter", password = "1234", roles = "USER")
    public void testPathWithUserWhetherReturnsCorrectStatusCodeWithUser() throws Exception {
        this.mockMvc.perform(get("/home").secure(true)).andExpect(status().isOk());
    }

    @Test
    public void testWithAnonymousWhetherReturnsCorrectStatusCode() throws Exception {
        this.mockMvc.perform(get("/").secure(true)).andExpect(status().isOk());
    }
}
