package bg.softuni.movies.services;

import bg.softuni.movies.models.entity.Movie;
import bg.softuni.movies.models.enums.Genre;
import bg.softuni.movies.models.service.ActorServiceModel;
import bg.softuni.movies.models.service.MovieServiceModel;
import bg.softuni.movies.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository mockMovieRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Mock
    private MovieService toTestMovieService;
    @Mock
    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private ActorService actorService;
    @Mock
    private UserService userService;
    @Mock
    private PictureService pictureService;

    @Mock
    private ActorRepository actorRepository;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;
    private AppUserDetailsService userDetailsService;
    private PictureRepository pictureRepository;


    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        actorService = new ActorService(actorRepository, modelMapper);
        userService = new UserService(userRepository, userRoleRepository, passwordEncoder, userDetailsService, modelMapper);

        toTestMovieService = new MovieService(mockMovieRepository, modelMapper, actorService, userService, pictureRepository);
    }

    @Test
    void findAll() {
        toTestMovieService.getAllMovies();
        verify(mockMovieRepository).findAll();
    }

    @Test
    void findMovieByTitle() {
        MovieServiceModel movie = initMovie();

        toTestMovieService.findMovieByTitle(movie.getTitle());
        verify(mockMovieRepository).findByTitle("Top Gun");
    }

    @Test
    void getAllTitleOfMovies() {

        toTestMovieService.getAllTitleOfMovies();
        verify(mockMovieRepository).findAllTitles();
    }

    @Test
    void getAllMoviesByGenre() {
        MovieServiceModel movie = initMovie();

        toTestMovieService.getAllMoviesByGenre(String.valueOf(Genre.ACTION));
        verify(mockMovieRepository).findAllByGenre(Genre.valueOf("ACTION"));
    }

//    @Test
//    void addMovie() {
//        Movie movie = new Movie();
//        movie.setTitle("Top Gun");
//        List<Genre> genres = new ArrayList<>();
//        genres.add(Genre.ACTION);
//        movie.setGenres(genres);
//
//        List<Actor> actors = new ArrayList<>();
//        Actor firstActor = new Actor();
//        firstActor.setName("Tom Cruise");
//        firstActor.setBiography("Some info");
//        actors.add(firstActor);
//
//        movie.setActors(actors);
//        MovieAddBM movieAddBM = new MovieAddBM();
//        movieAddBM.setActors("Tom Cruise");
//        movieAddBM.setGenres("Action");
//        movieAddBM.setTitle("Top Gun");
//        movieAddBM.setPlot("Some Info");
//
//        toTestMovieService.addMovie(initMovie(),movieAddBM, "peter");
//        verify(mockMovieRepository).saveAndFlush(modelMapper.map(movie, Movie.class));
//    }

    private MovieServiceModel initMovie() {
        MovieServiceModel movie = new MovieServiceModel();
        movie.setTitle("Top Gun");
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

        mockMovieRepository.saveAndFlush(modelMapper.map(movie, Movie.class));
        return movie;
    }


}
