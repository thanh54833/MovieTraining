package com.example.thanh.movietraining.object;

public class Movie {

    private String movie;
    private String name;
    private String view;
    private String url;
    private String description;
    private boolean like;
    private String id;

    private String director;
    private String manufacturer;
    private String duration;
    private String actor;
    private String link;
    private String genres;

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Movie(String movie, String name, String view, String url, String description, boolean like, String id, String director, String manufacturer, String duration, String actor, String link, String genres) {
        this.movie = movie;
        this.name = name;
        this.view = view;
        this.url = url;
        this.description = description;
        this.like = like;
        this.id = id;
        this.director = director;
        this.manufacturer = manufacturer;
        this.duration = duration;
        this.actor = actor;
        this.link = link;
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Movie(String movie, String name, String view, String url, String description, boolean like, String id) {
        this.movie = movie;
        this.name = name;
        this.view = view;
        this.url = url;
        this.description = description;
        this.like = like;
        this.id = id;
    }

    public Movie(String movie, String name, String view, String url, String description, boolean like) {
        this.movie = movie;
        this.name = name;
        this.view = view;
        this.url = url;
        this.description = description;
        this.like = like;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
