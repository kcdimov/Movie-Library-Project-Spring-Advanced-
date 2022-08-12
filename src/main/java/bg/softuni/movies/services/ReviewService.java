package bg.softuni.movies.services;

import bg.softuni.movies.models.entity.Movie;
import bg.softuni.movies.models.entity.Review;
import bg.softuni.movies.models.entity.UserEntity;
import bg.softuni.movies.models.service.ReviewServiceModel;
import bg.softuni.movies.repository.ReviewRepository;
import bg.softuni.movies.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final MovieService movieService;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, ModelMapper modelMapper,
                         UserService userService, MovieService movieService, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.movieService = movieService;
        this.userRepository = userRepository;
    }

    public List<Review> getAllReviewsByUsername(String username) {
        UserEntity userEntity = modelMapper.map(this.userService.findByUsername(username), UserEntity.class);

        return this.reviewRepository.findAllMovieByUser(userEntity);
    }

    public ReviewServiceModel addReview(ReviewServiceModel reviewServiceModel, String movieTitle, UserDetails userDetail) {

        Review review = this.modelMapper.map(reviewServiceModel, Review.class);
        Movie movie = this.movieService.findMovieByTitle(movieTitle);

        review.setMovie(movie);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();

        UserEntity user = this.modelMapper.map(this.userService.findByUsername(currentPrincipal), UserEntity.class);
//        UserEntity user = this.userRepository.findByUsername(reviewServiceModel.getUser().getUsername()).get();
        review.setUser(user);

        this.reviewRepository.save(review);
        this.reviewRepository.flush();
        return reviewServiceModel;
    }
}
