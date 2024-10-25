package com.aca.moviestore.dao;

import com.aca.moviestore.model.Genre;
import com.aca.moviestore.model.Movie;
import com.aca.moviestore.model.MovieException;

import java.util.ArrayList;
import java.util.List;

public class MovieDaoMock implements MovieDao {

    private static List<Movie> movies = new ArrayList<>();

    private static Integer lastMovieId = 0;

    private static Integer getNextMovieId() {
        lastMovieId++;
        return lastMovieId;
    }

    static {
        Movie bond = new Movie();
        bond.setTitle("Bond: The World is Not Enough");
        bond.setReleaseYear(1999);
        bond.setGenre(Genre.Action);
        bond.setId(getNextMovieId());

        Movie jerk = new Movie();
        jerk.setTitle("The Jerk");
        jerk.setReleaseYear(1979);
        jerk.setGenre(Genre.Comedy);
        jerk.setId(getNextMovieId());

        Movie spaceballs = new Movie();
        spaceballs.setTitle("Spaceballs");
        spaceballs.setReleaseYear(1987);
        spaceballs.setGenre(Genre.Comedy);
        spaceballs.setId(getNextMovieId());

        Movie it = new Movie();
        it.setTitle("It");
        it.setReleaseYear(2017);
        it.setGenre(Genre.Horror);
        it.setId(getNextMovieId());

        Movie starWars = new Movie();
        starWars.setTitle("Star Wars");
        starWars.setReleaseYear(1977);
        starWars.setGenre(Genre.SciFi);
        starWars.setId(getNextMovieId());

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

    @Override
    public Movie createMovie(Movie newMovie) {
        newMovie.setId(getNextMovieId());
        movies.add(newMovie);
        return newMovie;
    }

    @Override
    public Movie updateMovie(Movie updateMovie) {
        for (Movie movie : movies) {
            if (movie.getId().intValue() == updateMovie.getId().intValue()) {
                movie.setTitle(updateMovie.getTitle());
                movie.setReleaseYear(updateMovie.getReleaseYear());
                movie.setGenre(updateMovie.getGenre());
                break;
            }
        }
        return updateMovie;
    }

    @Override
    public Movie deleteMovieById(Integer movieIdValue) {
        List<Movie> movies = getMoviesById(movieIdValue);
        Movie movie = null;
        if (!movies.isEmpty()) {
            movie = movies.get(0);
            MovieDaoMock.movies.remove(movie);
        }
        return movie;
    }

    @Override
    public List<Movie> getReport(Integer startReleaseYear, Integer endReleaseYear) {
        List<Movie> myMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getReleaseYear() >= startReleaseYear &&
                movie.getReleaseYear() <= endReleaseYear) {
                myMovies.add(movie);
            }
        }
        return myMovies;
    }
}
