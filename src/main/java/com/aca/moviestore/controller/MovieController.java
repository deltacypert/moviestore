package com.aca.moviestore.controller;

import com.aca.moviestore.model.Genre;
import com.aca.moviestore.model.Movie;
import com.aca.moviestore.service.MovieService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/movies", produces = "application/json")
public class MovieController {
    private MovieService service = new MovieService();

    @RequestMapping(method = RequestMethod.GET)
    public List<Movie> getMovies() {
        return service.getMovies();
    }

    @RequestMapping(
            value = "/genre/{genreValue}",
            method = RequestMethod.GET)
    public List<Movie> getMoviesByGenre(@PathVariable Genre genreValue) {
        return service.getMoviesByGenre(genreValue);
    }

    @RequestMapping(
            value = "/releaseyear/{releaseYearValue}",
            method = RequestMethod.GET)
    public List<Movie> getMoviesByReleaseYear(@PathVariable Integer releaseYearValue) {
        return service.getMoviesByReleaseYear(releaseYearValue);
    }

    @RequestMapping(
            value = "/{movieIdValue}",
            method = RequestMethod.GET)
    public List<Movie> getMoviesById(@PathVariable Integer movieIdValue) {
        return service.getMoviesById(movieIdValue);
    }
}
