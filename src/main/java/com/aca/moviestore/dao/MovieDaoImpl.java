package com.aca.moviestore.dao;

import com.aca.moviestore.model.Genre;
import com.aca.moviestore.model.Movie;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao {

    // always test querys in database before you write string
    private static String selectAllMovies =
            "SELECT id, title, releaseYear, genreId, updateDateTime, createDateTime " +
            "FROM movies";
    private static String selectMoviesByGenre =
            "SELECT id, title, releaseYear, genreId, updateDateTime, createDateTime " +
            "FROM movies " +
            "WHERE genreId = ? ";
    private static String selectMoviesByReleaseYear =
            "SELECT id, title, releaseYear, genreId, updateDateTime, createDateTime " +
                    "FROM movies " +
                    "WHERE releaseYear = ? ";
    private static String selectMoviesById =
            "SELECT id, title, releaseYear, genreId, updateDateTime, createDateTime " +
                    "FROM movies " +
                    "WHERE id = ? ";
    private static String selectMoviesByTitle =
            "SELECT id, title, releaseYear, genreId, updateDateTime, createDateTime " +
                    "FROM movies " +
                    "WHERE title LIKE ? ";
    private static String deleteMovieById =
            "DELETE FROM movies " +
            "WHERE id = ? ";
    private static String updateMovieById =
            "UPDATE movies " +
            "SET title = ?, " +
            "    releaseYear = ?," +
            "    genreId = ? " +
            "WHERE id = ? ";
    private static String selectMoviesByRangeOfReleaseYear =
            "SELECT id, title, releaseYear, genreId, updateDateTime, createDateTime " +
            "FROM movies " +
            "WHERE releaseYear >= ? " +
            "AND releaseYear <= ? ";
    private static String insertMovie =
        "INSERT INTO movies (title, releaseYear, genreId) " +
        "VALUES " +
        "(?, ?, ?) ";

    @Override
    public List<Movie> getMovies() {
        List<Movie> myMovies = new ArrayList<>();
        ResultSet result = null; // an object that contains the rows and columns
        Statement statement = null; // what we build and send

        Connection connection = MariaDbUtil.getConnection();

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(selectAllMovies);
            myMovies = makeMovies(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return myMovies;
    }

    private List<Movie> makeMovies(ResultSet result) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        while(result.next()) {
            Movie movie = new Movie();
            movie.setTitle(result.getString("title"));
            movie.setId(result.getInt("id"));
            movie.setReleaseYear(result.getInt("releaseYear"));

            String genreString = result.getString("genreID");
            movie.setGenre(Genre.convertStringToGenre(genreString));

            movie.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));
            movie.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));

            movies.add(movie);
        }

        return movies;
    }

    @Override
    public List<Movie> getMoviesByGenre(Genre genre) {
        List<Movie> myMovies = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();
        try {
            ps = conn.prepareStatement(selectMoviesByGenre);
            ps.setString(1, genre.toString());
            rs = ps.executeQuery();
            myMovies = makeMovies(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                rs.close();
//                ps.close();
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        return myMovies;
    }

    @Override
    public List<Movie> getMoviesByReleaseYear(Integer releaseYear) {
        List<Movie> myMovies = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectMoviesByReleaseYear);
            ps.setInt(1, releaseYear);
            rs = ps.executeQuery();
            myMovies = makeMovies(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myMovies;
    }

    @Override
    public List<Movie> getMoviesById(Integer movieId) {
        List<Movie> myMovies = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectMoviesById);
            ps.setInt(1, movieId);
            rs = ps.executeQuery();
            myMovies = makeMovies(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myMovies;
    }

    @Override
    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> myMovies = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectMoviesByTitle);
            title = "%" + title + "%";
            ps.setString(1, title);
            rs = ps.executeQuery();
            myMovies = makeMovies(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myMovies;
    }

    @Override
    public Movie createMovie(Movie newMovie) {
        PreparedStatement ps = null;
        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(insertMovie);
            ps.setString(1, newMovie.getTitle());
            ps.setInt(2, newMovie.getReleaseYear());
            ps.setString(3, newMovie.getGenre().toString());
            int rowCount = ps.executeUpdate();
            System.out.println("insert count: " + rowCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newMovie;
    }

    @Override
    public Movie updateMovie(Movie updateMovie) {
        List<Movie> myMovies = this.getMoviesById(updateMovie.getId());

        if (myMovies.size() > 0) {
            PreparedStatement ps = null;
            Connection conn = MariaDbUtil.getConnection();
            try {
                ps = conn.prepareStatement(updateMovieById);
                ps.setString(1, updateMovie.getTitle());
                ps.setInt(2, updateMovie.getReleaseYear());
                ps.setString(3, updateMovie.getGenre().toString());
                ps.setInt(4, updateMovie.getId());
                int rowCount = ps.executeUpdate();
                System.out.println("rows updated: " + rowCount);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updateMovie;
    }

    @Override
    public Movie deleteMovieById(Integer movieIdValue) {
        List<Movie> myMovies = getMoviesById(movieIdValue);
        Movie movieToDelete = null;

        if (myMovies.size() > 0) {
            movieToDelete = myMovies.get(0);
            PreparedStatement ps = null;

            Connection conn = MariaDbUtil.getConnection();
            try {
                ps = conn.prepareStatement(deleteMovieById);
                ps.setInt(1, movieIdValue);
                int rowCount = ps.executeUpdate();
                System.out.println("rows deleted: " + rowCount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return movieToDelete;
    }

    @Override
    public List<Movie> getReport(Integer startReleaseYear, Integer endReleaseYear) {
        List<Movie> myMovies = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectMoviesByRangeOfReleaseYear);
            ps.setInt(1, startReleaseYear);
            ps.setInt(2, endReleaseYear);
            rs = ps.executeQuery();
            myMovies = makeMovies(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myMovies;
    }
}
