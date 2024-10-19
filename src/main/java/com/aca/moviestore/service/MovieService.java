package com.aca.moviestore.service;

import com.aca.moviestore.dao.MovieDAO;
import com.aca.moviestore.dao.MovieDaoMock;
import com.aca.moviestore.model.Genre;
import com.aca.moviestore.model.Movie;

import java.util.List;

public class MovieService {

    private MovieDAO movieDao = new MovieDaoMock();

    public List<Movie> getMovies() {
        return movieDao.getMovies();
    }

    public List<Movie> getMoviesByGenre(Genre genre) {
        return movieDao.getMoviesByGenre(genre);
    }

    public List<Movie> getMoviesByReleaseYear(Integer releaseYear) {
        // TODO need to validate release year, e.g. -1200 is not valid
        return movieDao.getMoviesByReleaseYear(releaseYear);
    }

    public List<Movie> getMoviesById(Integer movieId) {
        return movieDao.getMoviesById(movieId);
    }

}
