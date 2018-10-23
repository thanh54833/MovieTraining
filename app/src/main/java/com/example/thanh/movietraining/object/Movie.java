package com.example.thanh.movietraining.object;

public class Movie {

    private String movie;
    private String name;
    private String view;
    private String url;
    private String description;
    private boolean like;
    private String id;

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
