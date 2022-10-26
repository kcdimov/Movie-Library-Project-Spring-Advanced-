package bg.softuni.movies.services;



import bg.softuni.movies.models.entity.*;
import bg.softuni.movies.models.enums.Genre;
import bg.softuni.movies.models.service.ActorServiceModel;
import bg.softuni.movies.models.service.MovieServiceModel;
import bg.softuni.movies.models.service.PictureServiceModel;
import bg.softuni.movies.models.service.UserServiceModel;
import bg.softuni.movies.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PictureServiceTest {


    @Mock
    private PictureService testPictureService;
    @Mock
    private PictureRepository mockPictureRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ActorRepository actorRepository;
    @Mock
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;
    private AppUserDetailsService userDetailsService;
    private MovieService movieService;
    private ActorService actorService;
    private PictureService pictureService;
    private UserService userService;
    private Random random;
    private MovieRepository movieRepository;
    private PictureRepository pictureRepository;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        actorService = new ActorService(actorRepository, modelMapper);
        userService = new UserService(userRepository, userRoleRepository, passwordEncoder, userDetailsService, modelMapper);
        movieService = new MovieService(movieRepository, modelMapper, actorService, userService, pictureRepository);


        random = new Random();
        testPictureService = new PictureService(mockPictureRepository, modelMapper, movieService, actorService, userService, random);
    }

    @Test
    public void findPictureByMovieId() {
        PictureServiceModel pictureServiceModel = initPicture();

        testPictureService.findPictureByMovieId(pictureServiceModel.getId());
        verify(mockPictureRepository).findAllByMovie_Id(1L);
    }

    @Test
    public void findPictureByActorId() {
        PictureServiceModel pictureServiceModel = initPicture();
        testPictureService.findPictureByActorId(pictureServiceModel.getActor().getId());

        verify(mockPictureRepository).findAllByActor_Id(1L);
    }

    private PictureServiceModel initPicture() {
        PictureServiceModel picture = new PictureServiceModel();
        MovieServiceModel movie = new MovieServiceModel();
        movie.setTitle("Top Gun");
        movie.setId(1L);
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        movie.setGenres(genres);

        List<ActorServiceModel> actors = new ArrayList<>();
        ActorServiceModel firstActor = new ActorServiceModel();
        firstActor.setId(1L);
        firstActor.setActorName("Tom Cruise");
        firstActor.setBiography("Some info");
        actors.add(firstActor);

        movie.setActors(actors);

        picture.setMovie(movie);

        picture.setActor(firstActor);

        UserServiceModel user = new UserServiceModel();
        user.setUsername("peter");

        picture.setUser(user);

        picture.setId(1L);

        Picture testPicture = this.modelMapper.map(picture, Picture.class);
        this.mockPictureRepository.saveAndFlush(testPicture);

        return picture;
    }
}
