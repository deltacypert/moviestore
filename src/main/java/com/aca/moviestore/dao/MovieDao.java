package com.aca.moviestore.dao;

import java.util.List;

import com.aca.moviestore.model.Genre;
import com.aca.moviestore.model.Movie;

public interface MovieDao {

    public abstract List<Movie> getMovies();
    public abstract List<Movie> getMoviesByGenre(Genre genre);
    public abstract List<Movie> getMoviesByReleaseYear(Integer releaseYear);
    public abstract List<Movie> getMoviesById(Integer movieId);
    public abstract List<Movie> getMoviesByTitle(String title);

}