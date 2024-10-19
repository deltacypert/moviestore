package com.aca.moviestore.dao;

import com.aca.moviestore.model.Genre;
import com.aca.moviestore.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDaoMock implements MovieDao {

    private static List<Movie> movies = new ArrayList<>();

    static {
        Movie bond = new Movie();
        bond.setTitle("Bond: The World is Not Enough");
        bond.setReleaseYear(1999);
        bond.setGenre(Genre.Action);
        bond.setId(1);

        Movie jerk = new Movie();
        jerk.setTitle("The Jerk");
        jerk.setReleaseYear(1979);
        jerk.setGenre(Genre.Comedy);
        jerk.setId(2);

        Movie spaceballs = new Movie();
        spaceballs.setTitle("Spaceballs");
        spaceballs.setReleaseYear(1987);
        spaceballs.setGenre(Genre.Comedy);
        spaceballs.setId(3);

        Movie it = new Movie();
        it.setTitle("It");
        it.setReleaseYear(2017);
        it.setGenre(Genre.Horror);
        it.setId(4);

        Movie starWars = new Movie();
        starWars.setTitle("Star Wars");
        starWars.setReleaseYear(1977);
        starWars.setGenre(Genre.SciFi);
        starWars.setId(5);

        movies.add(bond);
        movies.add(jerk);
        movies.add(spaceballs);
        movies.add(starWars);
        movies.add(it);
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> myMovies = new ArrayList<>();
        myMovies.addAll(MovieDaoMock.movies);
        return myMovies;
    }

    @Override
    public List<Movie> getMoviesByGenre(Genre genre) {
        List<Movie> myMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenre().equals(genre)) {
                myMovies.add(movie);
            }
        }
        return myMovies;
    }

    @Override
    public List<Movie> getMoviesByReleaseYear(Integer releaseYear) {
        List<Movie> myMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getReleaseYear().equals(releaseYear)) {
                myMovies.add(movie);
            }
        }
        return myMovies;
    }

    @Override
    public List<Movie> getMoviesById(Integer movieIdValue) {
        List<Movie> myMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getId().equals(movieIdValue)) {
                myMovies.add(movie);
            }
        }
        return myMovies;
    }

    @Override
    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> myMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                myMovies.add(movie);
            }
        }
        return myMovies;
    }

}
