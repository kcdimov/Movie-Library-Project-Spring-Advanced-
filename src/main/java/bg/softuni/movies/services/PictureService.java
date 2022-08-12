package bg.softuni.movies.services;

import bg.softuni.movies.models.entity.Actor;
import bg.softuni.movies.models.entity.Movie;
import bg.softuni.movies.models.entity.Picture;
import bg.softuni.movies.models.entity.UserEntity;
import bg.softuni.movies.models.service.PictureServiceModel;
import bg.softuni.movies.repository.PictureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final MovieService movieService;
    private final ActorService actorService;
    private final UserService userService;
    private final Random random;

    public PictureService(PictureRepository pictureRepository, ModelMapper modelMapper,
                          MovieService movieService, ActorService actorService,
                          UserService userService, Random random) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.movieService = movieService;
        this.actorService = actorService;
        this.userService = userService;
        this.random = random;
    }


    public PictureServiceModel addPicture(PictureServiceModel pictureServiceModel, String movieTitle, String actorName) {

        Picture picture = this.modelMapper.map(pictureServiceModel, Picture.class);

        try {
            Movie movie = this.movieService.findMovieByTitle(movieTitle);
            picture.setMovie(movie);
        } catch (Exception ex) {

        }

        try {
            Actor actor = this.modelMapper.map(this.actorService.findActorsByNames(actorName), Actor.class);
            picture.setActor(actor);
        } catch (Exception ex) {

        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        picture.setUser(this.modelMapper.map(this.userService.findByUsername(currentPrincipalName), UserEntity.class));

        this.pictureRepository.saveAndFlush(picture);

        return pictureServiceModel;
    }

    public List<Picture> findPictureByMovieId(Long id) {
        return this.pictureRepository.findAllByMovie_Id(id);
    }

    public List<Picture> findPictureByActorId(Long id) {
        return this.pictureRepository.findAllByActor_Id(id);
    }

    public String getRandomPictureOfMovie(Long id) {
        List<Picture> pictureList  = findPictureByMovieId(id);

        return getRandomPicture(pictureList);
    }

    public String getActorRandomPicture(Long id) {
        List<Picture> pictures = findPictureByActorId(id);
        return getRandomPicture(pictures);
    }

    private String getRandomPicture(List<Picture> pictureList) {
        if (pictureList.size() != 0) {
            int n = this.random.nextInt(pictureList.size()) + 1;
            int count = 1;

            for (Picture picture : pictureList) {
                if (count == n) {
                    return picture.getUrl();
                }
                count++;
            }
        }

        return "/images/movie_random.jpg";
    }

    public Optional<Picture> getAllPicturesByActor(Long id) {

        List<Picture> picturesByActorId = findPictureByActorId(id);

        Optional<Picture> first = picturesByActorId.stream().findFirst();

        return first;
    }
}
