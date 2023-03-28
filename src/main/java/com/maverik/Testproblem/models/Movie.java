package com.maverik.Testproblem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @Id
    private String imdbID;
    private String title;
    private int year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    // TODO: Consider how list fields should be modeled
    private String[] directors;
    private String[] actors;
    private String plot;
    private String[] languages;
    private String[] countries;
    private String poster;
    private float rating;

    public Movie() {
    }

    public Movie(MaverikMovie maverikMovie) {
        imdbID = maverikMovie.imdbID();
        title = maverikMovie.title();
        year = Integer.parseInt(maverikMovie.year());
        rated = maverikMovie.rated();
        released = maverikMovie.released();
        runtime = maverikMovie.runtime();
        genre = maverikMovie.genre();
        directors = maverikMovie.director().split(", ");
        actors = maverikMovie.actors().split(", ");
        plot = maverikMovie.plot();
        languages = maverikMovie.language().split(", ");
        countries = maverikMovie.country().split(", ");
        poster = maverikMovie.poster();
        rating = Float.parseFloat(maverikMovie.imdbRating());
    }
}
