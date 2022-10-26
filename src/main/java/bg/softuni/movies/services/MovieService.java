package bg.softuni.movies.services;

import bg.softuni.movies.models.bindings.MovieAddBM;
import bg.softuni.movies.models.entity.Movie;
import bg.softuni.movies.models.entity.Picture;
import bg.softuni.movies.models.entity.UserEntity;
import bg.softuni.movies.models.enums.Genre;
import bg.softuni.movies.models.service.ActorServiceModel;
import bg.softuni.movies.models.service.MovieServiceModel;
import bg.softuni.movies.models.service.UserServiceModel;
import bg.softuni.movies.repository.MovieRepository;
import bg.softuni.movies.repository.PictureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final ActorService actorService;
    private final UserService userService;
    private final PictureRepository pictureRepository;



    public MovieService(MovieRepository movieRepository, ModelMapper modelMapper,
                        ActorService actorService, UserService userService, PictureRepository pictureRepository) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.actorService = actorService;
        this.userService = userService;
        this.pictureRepository = pictureRepository;
    }


    public MovieServiceModel addMovie(MovieServiceModel movie, @Valid MovieAddBM movieAddBM, String currentPrincipalName) {

        Picture picture = new Picture();
        picture.setUrl(movieAddBM.getPicture());

        List<Genre> genres = new ArrayList<>();

        genres.add(this.modelMapper.map(movieAddBM.getGenre(), Genre.class));
        genres.add(this.modelMapper.map(movieAddBM.getGenres(), Genre.class));

        movie.setGenres(genres);

        List<ActorServiceModel> actors = new ArrayList<>();
        actors.add(this.actorService.findActorsByNames(movieAddBM.getActor()));
        actors.add(this.actorService.findActorByNameFromList(movieAddBM.getActors()));

        movie.setActors(actors);

        List<UserServiceModel> usersList = new ArrayList<>();
        usersList.add(userService.findByUsername(currentPrincipalName));
        movie.setUsers(usersList);

        this.movieRepository.saveAndFlush(this.modelMapper.map(movie, Movie.class));

        Movie movieToPicture = this.movieRepository.findByTitle(movieAddBM.getTitle());
        picture.setMovie(movieToPicture);
        this.pictureRepository.save(picture);

        return movie;
    }

    public Movie findMovieByTitle(String movieTitle) {
        return this.movieRepository.findByTitle(movieTitle);
    }

    public List<String> getAllTitleOfMovies() {
        return this.movieRepository.findAllTitles();
    }

    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    public MovieServiceModel findById(Long id) {
        Movie movie = this.movieRepository.findById(id).orElse(null);

        if(movie != null) {
            return this.modelMapper.map(movie, MovieServiceModel.class);
        } else {
            return null;
        }
    }

    public List<Movie> getAllMoviesByGenre(String genre) {
        return this.movieRepository.findAllByGenre(Genre.valueOf(genre));
    }

    public List<Movie> getAllMoviesByUser(String username) {
        UserEntity userEntity = this.modelMapper.map(this.userService.findByUsername(username), UserEntity.class);
        List<Movie> movies = this.movieRepository.findMoviesByUserEntities(userEntity);

        return movies;

    }

    public void deleteMovieById(Long id) {
        this.movieRepository.deleteById(id);
    }

//    public List<MovieDetailView> searchMovies(SearchMovieBM searchMovieBM) {
//        return this.movieRepository.findAll(new MovieSpecification(searchMovieBM))
//                .stream().map(movie -> this.modelMapper.map(movie, MovieDetailView.class))
//                .toList();
//    }
}
