package com.github.shashi.lld.movieticketbooking;

import java.util.Date;
import java.util.List;

public interface Search {
    public List<Movie> searchMovieTitle(String title);
    public List<Movie> searchMovieLanguage(String language);
    public List<Movie> searchMovieGenre(String genre);
    public List<Movie> searchMovieReleaseDate(Date date);
}