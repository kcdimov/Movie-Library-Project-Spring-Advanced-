package bg.softuni.movies.web;

import bg.softuni.movies.models.entity.Actor;
import bg.softuni.movies.repository.ActorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
public class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActorRepository actorRepository;


    private Long FIRST_ACTOR_ID, SECOND_ACTOR_ID;
    private String FIRST_ACTOR_NAME = "Tom Cruise", SECOND_ACTOR_NAME = "Vin Disel";

    Actor firstActor = new Actor();
    Actor secondActor = new Actor();

    @Autowired
    private ActorController controller;

    @BeforeEach
    public void setup() {

        firstActor.setName(FIRST_ACTOR_NAME);
        actorRepository.save(firstActor);
        FIRST_ACTOR_ID = firstActor.getId();

        secondActor.setName(SECOND_ACTOR_NAME);
        actorRepository.save(secondActor);
        SECOND_ACTOR_ID = secondActor.getId();
    }

    @AfterEach
    public void tearDown() {
        this.actorRepository.delete(firstActor);
        this.actorRepository.delete(secondActor);
    }

    @Test
    public void setController() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    @WithMockUser(username = "peter", password = "1234", roles = "USER")
    public void testActorsWithCorrectStatusCode() throws Exception {
        this.mockMvc.perform(get("/actors/all-actors").secure(true)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void testActorIfCannotBeFound() throws Exception {
        this.mockMvc.perform(get("/actor-details/11111/").secure(true)).andExpect(status().isNotFound());
    }

}
