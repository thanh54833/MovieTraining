package com.example.thanh.movietraining.model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("message")
    public String message;
    @SerializedName("error")
    public String error;
    @SerializedName("data")
    public Data data;
    @SerializedName("code")
    public String code;



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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LoginModel(String message, String error, Data data, String code) {
        this.message = message;
        this.error = error;
        this.data = data;
        this.code = code;
    }
    public static class Data {
        @SerializedName("id")
        private String id;
        @SerializedName("birthday")
        private String birthday;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("email")
        private String email;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("google_id")

        private String google_id;
        @SerializedName("gender")
        private String gender;
        @SerializedName("facebook_id")
        private String facebook_id;
        @SerializedName("password")
        private String password;
        @SerializedName("access_token")
        private String access_token;
        @SerializedName("full_name")
        private String full_name;


        public Data(String id, String birthday, String updated_at, String email, String created_at, String google_id, String gender, String facebook_id, String password, String access_token, String full_name) {
            this.id = id;
            this.birthday = birthday;
            this.updated_at = updated_at;
            this.email = email;
            this.created_at = created_at;
            this.google_id = google_id;
            this.gender = gender;
            this.facebook_id = facebook_id;
            this.password = password;
            this.access_token = access_token;
            this.full_name = full_name;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getGoogle_id() {
            return google_id;
        }

        public void setGoogle_id(String google_id) {
            this.google_id = google_id;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getFacebook_id() {
            return facebook_id;
        }

        public void setFacebook_id(String facebook_id) {
            this.facebook_id = facebook_id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }
    }
}