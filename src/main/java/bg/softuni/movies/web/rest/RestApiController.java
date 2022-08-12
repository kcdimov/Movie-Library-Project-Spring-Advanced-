package bg.softuni.movies.web.rest;

import bg.softuni.movies.models.entity.Movie;
import bg.softuni.movies.models.view.StatisticViewModel;
import bg.softuni.movies.services.MovieService;
import bg.softuni.movies.services.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RestApiController {

    private final StatisticService statisticService;
    private final MovieService movieService;

    public RestApiController(StatisticService statisticService, MovieService movieService) {
        this.statisticService = statisticService;
        this.movieService = movieService;
    }

    @GetMapping("/statistic")
    @Transactional
    public ResponseEntity<List<StatisticViewModel>> getStatistic() {
        List<StatisticViewModel> statisticViewModels = this.statisticService.getStatistic();
        return new ResponseEntity<>(statisticViewModels, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.
                ok(movieService.getAllMovies());
    }
}
