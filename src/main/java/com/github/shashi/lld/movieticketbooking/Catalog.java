package com.github.shashi.lld.movieticketbooking;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Catalog implements Search {
    HashMap<String, List<Movie>> movieTitles;
    HashMap<String, List<Movie>> movieLanguages;
    HashMap<String, List<Movie>> movieGenres;

    // The Date datatype represents and deals with both date and time.
    HashMap<Date, List<Movie>> movieReleaseDates;

    public List<Movie> searchMovieTitle(String title) {
        // functionality
        return java.util.Collections.emptyList();
    }

    public List<Movie> searchMovieLanguage(String language) {
        // functionality
        return java.util.Collections.emptyList();
    }

    public List<Movie> searchMovieGenre(String genre) {
        // functionality
        return java.util.Collections.emptyList();
    }

    public List<Movie> searchMovieReleaseDate(Date date) {
        // functionality
        return java.util.Collections.emptyList();
    }
}