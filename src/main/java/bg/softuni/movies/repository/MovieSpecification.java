package bg.softuni.movies.repository;

import bg.softuni.movies.models.bindings.SearchMovieBM;
import bg.softuni.movies.models.entity.Movie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MovieSpecification implements Specification<Movie> {
    private final SearchMovieBM searchMovieBM;

    public MovieSpecification(SearchMovieBM searchMovieBM) {
        this.searchMovieBM = searchMovieBM;
    }

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate p = criteriaBuilder.conjunction();

        if (searchMovieBM.getTitle() != null && !searchMovieBM.getTitle().isEmpty() ) {
            p.getExpressions().add(criteriaBuilder.and(
                    criteriaBuilder.equal
                            (root.join("movie").get("title"), searchMovieBM.getTitle())));
        }

        if (searchMovieBM.getGenre() !=null && !searchMovieBM.getGenre().isEmpty()) {
            p.getExpressions().add(criteriaBuilder.and(criteriaBuilder.equal
                    (root.join("movie").get("genre"), searchMovieBM.getGenre())));
        }
        return p;
    }
}
