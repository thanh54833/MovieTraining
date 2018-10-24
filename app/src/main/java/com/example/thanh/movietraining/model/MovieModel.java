package com.example.thanh.movietraining.model;

import com.google.gson.annotations.SerializedName;

public class MovieModel {

    @SerializedName("message")
    private String message;
    @SerializedName("error")
    private String error;
    @SerializedName("data")
    private Data[] data;
    @SerializedName("code")
    private String code;
    @SerializedName("paging")
    private Paging paging;

    public MovieModel(String message, String error, Data[] data, String code, Paging paging) {
        this.message = message;
        this.error = error;
        this.data = data;
        this.code = code;
        this.paging = paging;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public class Paging {
        @SerializedName("per_page")
        private String per_page;
        @SerializedName("total_item")
        private String total_item;
        @SerializedName("total_pages")
        private String total_pages;
        @SerializedName("current_page")
        private String current_page;

        public Paging(String per_page, String total_item, String total_pages, String current_page) {
            this.per_page = per_page;
            this.total_item = total_item;
            this.total_pages = total_pages;
            this.current_page = current_page;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public String getTotal_item() {
            return total_item;
        }

        public void setTotal_item(String total_item) {
            this.total_item = total_item;
        }

        public String getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(String total_pages) {
            this.total_pages = total_pages;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }
    }


    public  class Data {
        @SerializedName("link")
        private String link;
        @SerializedName("image")
        private String image;
        @SerializedName("actor")
        private String actor;
        @SerializedName("type")
        private String type;
        @SerializedName("director")
        private String director;
        @SerializedName("id")
        private String id;
        @SerializedName("title")
        private String title;
        @SerializedName("category")
        private String category;
        @SerializedName("duration")
        private String duration;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("views")
        private String views;
        @SerializedName("description")
        private String description;
        @SerializedName("manufacturer")
        private String manufacturer;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("year")
        private String year;


        public Data(String link, String image, String actor, String type, String director, String id, String title, String category, String duration, String updated_at, String views) {
            this.link = link;
            this.image = image;
            this.actor = actor;
            this.type = type;
            this.director = director;
            this.id = id;
            this.title = title;
            this.category = category;
            this.duration = duration;
            this.updated_at = updated_at;
            this.views = views;
            this.description = description;
            this.manufacturer = manufacturer;
            this.created_at = created_at;
            this.year = year;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getActor() {
            return actor;
        }

        public void setActor(String actor) {
            this.actor = actor;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }
    }


}
