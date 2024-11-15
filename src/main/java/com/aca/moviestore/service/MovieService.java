package com.aca.moviestore.service;

import com.aca.moviestore.dao.MovieDao;
import com.aca.moviestore.dao.MovieDaoImpl;
import com.aca.moviestore.model.Genre;
import com.aca.moviestore.model.Movie;
import com.aca.moviestore.model.MovieException;

import java.util.List;

public class MovieService {

    private MovieDao movieDao = new MovieDaoImpl();

    public List<Movie> getMovies() {
        return movieDao.getMovies();
    }

    public List<Movie> getMoviesByGenre(Genre genre) {
        return movieDao.getMoviesByGenre(genre);
    }

    public List<Movie> getMoviesByReleaseYear(Integer releaseYear) throws MovieException {
        validateReleaseYear(releaseYear);
        return movieDao.getMoviesByReleaseYear(releaseYear);
    }

    private void validateReleaseYear(Integer releaseYear) throws MovieException {
        if (null == releaseYear) {
            throw new MovieException("Invalid value for release year. Null value not allowed");
        } else if (releaseYear < 1950 || releaseYear > 2025) {
            throw new MovieException("Invalid value for release year. Must be between 1950 and 2025.");
        }
    }

    public List<Movie> getMoviesById(Integer movieId) {
        return movieDao.getMoviesById(movieId);
    }

    public List<Movie> getMoviesByTitle(String title) {
        return movieDao.getMoviesByTitle(title);
    }

    public Movie createMovie(Movie newMovie) throws MovieException {
        validateReleaseYear(newMovie.getReleaseYear());
        return movieDao.createMovie(newMovie);
    }

    public Movie updateMovie(Movie updateMovie) { return movieDao.updateMovie(updateMovie); }

    public Movie deleteMovieById(Integer movieIdValue) {
       return movieDao.deleteMovieById(movieIdValue);
    }

    public List<Movie> getReport(Integer startReleaseYear, Integer endReleaseYear) throws MovieException {
        validateReportYears(startReleaseYear, endReleaseYear);
        return movieDao.getReport(startReleaseYear, endReleaseYear);
    }

    private void validateReportYears(Integer startReleaseYear, Integer endReleaseYear) throws MovieException {
        validateReleaseYear(startReleaseYear);
        validateReleaseYear(endReleaseYear);
        if (startReleaseYear > endReleaseYear) {
            throw new MovieException("Invalid value for release year. End year must be greater than start year.");
        }
    }

}
