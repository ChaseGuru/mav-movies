package com.maverik.Testproblem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @Id
    private String imdbID;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private String rated;

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    private String released;

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    private String runtime;

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // TODO: Consider how list fields should be modeled
    private String[] directors;

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    private String[] actors;

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    private String plot;

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    private String[] languages;

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    private String[] countries;

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    private String poster;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    private float rating;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

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
