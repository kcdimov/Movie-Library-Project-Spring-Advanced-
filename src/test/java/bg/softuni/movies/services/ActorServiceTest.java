package bg.softuni.movies.services;

import bg.softuni.movies.models.entity.Actor;
import bg.softuni.movies.models.service.ActorServiceModel;
import bg.softuni.movies.repository.ActorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorService actorService;

    @Autowired
    private TestEntityManager entityManager;

    private ActorServiceModel toTest;

    private ModelMapper modelMapper;
//    @BeforeEach
//    public void setUp() {
//        Actor actor = new Actor();
//        actor.setName("Denzel Washington");
//        actor.setBiography("some info");
//        actor.setId(1L);
//
//        Mockito.when(actorRepository.findByName(actor.getName()))
//                .thenReturn(actor);
//    }
    @Test
    void findByName() {
        ActorServiceModel actor = new ActorServiceModel();
        actor.setActorName("Denzel Washington");
        actor.setBiography("some info");
        actor.setId(1L);


        String expected = "Denzel Washington";

        Assertions.assertEquals(expected, actor.getActorName());

    }

    @Test
    void findById() {
        Actor actor = new Actor();
        actor.setName("Denzel Washington");
        actor.setBiography("some info");
        actor.setId(1L);
        Assertions.assertEquals(1, actor.getId());
    }

    @Test
    void addActor() {
    }

    @Test
    void getAllActorNames() {

    }

    @Test
    void getAllActors() {
        List<Actor> datas = new ArrayList<>();
        Actor actor = new Actor();
        actor.setName("Tom Cruise");

        Actor actor1 = new Actor();
        actor1.setName("Vin Disel");

        datas.add(actor);
        datas.add(actor1);

        given(actorRepository.findAll()).willReturn(datas);

        List<Actor> expexted = actorService.getAllActors();

        Assertions.assertEquals(expexted, datas);

    }
}
