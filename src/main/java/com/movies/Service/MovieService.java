package com.movies.Service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.movies.model.Movie;
import com.movies.repo.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository repo;

    public MovieService(MovieRepository repo) {
        this.repo = repo;
    }

    public Movie create(Movie movie) {
        if (movie == null) {
            throw new RuntimeException("Invalid Movie");
        }
        return repo.save(movie);
    }

    public Movie read(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Movie update(Long id, Movie update) {
        Movie movie = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setName(update.getName());
        movie.setDirector(update.getDirector());
        movie.setActors(update.getActors());

        return repo.save(movie);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Movie> getAll() {
        return repo.findAll();
    }
}