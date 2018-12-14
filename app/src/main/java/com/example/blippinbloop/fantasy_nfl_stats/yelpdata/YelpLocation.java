package com.example.blippinbloop.fantasy_nfl_stats.yelpdata;

public class YelpLocation {
    public String name;
    public String image_url;
    public String url;
    public String phone;

    public YelpLocation(String name, String image_url, String url, String phone) {
        this.name = name;
        this.image_url = image_url;
        this.url = url;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getUrl() {
        return url;
    }

    public String getPhone() {
        return phone;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

