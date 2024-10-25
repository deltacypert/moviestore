package com.aca.moviestore.controller;

import com.aca.moviestore.model.Genre;
import com.aca.moviestore.model.Movie;
import com.aca.moviestore.model.MovieException;
import com.aca.moviestore.service.MovieService;
import org.springframework.web.bind.annotation.*;

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
    public List<Movie> getMoviesByReleaseYear(@PathVariable Integer releaseYearValue) throws MovieException {
        return service.getMoviesByReleaseYear(releaseYearValue);
    }

    @RequestMapping(
            value = "/{movieIdValue}",
            method = RequestMethod.GET)
    public List<Movie> getMoviesById(@PathVariable Integer movieIdValue) {
        return service.getMoviesById(movieIdValue);
    }

    @RequestMapping(
            value = "/title/{titleValue}",
            method = RequestMethod.GET)
    public List<Movie> getMoviesByTitle(@PathVariable String titleValue) {
        return service.getMoviesByTitle(titleValue);
    }

    @RequestMapping(
            consumes = "application/json",
            method = RequestMethod.POST)
    public Movie createMovie(@RequestBody Movie newMovie) throws MovieException {
        return service.createMovie(newMovie);
    }

    @RequestMapping(
            consumes = "application/json",
            method = RequestMethod.PUT)
    public Movie updateMovie(@RequestBody Movie updateMovie) {
        return service.updateMovie(updateMovie);
    }

    @RequestMapping(
            value = "/{movieIdValue}",
            method = RequestMethod.DELETE)
    public Movie deleteMovieById(@PathVariable Integer movieIdValue) {
        System.out.println("movie: " + movieIdValue + " deleted.");
        return service.deleteMovieById(movieIdValue);
    }

    @RequestMapping(
            value = "/report",
            method = RequestMethod.GET)
    public List<Movie> getReport(@RequestParam Integer startReleaseYear,
                                 @RequestParam Integer endReleaseYear) throws MovieException {
        return service.getReport(startReleaseYear, endReleaseYear);
    }

}
