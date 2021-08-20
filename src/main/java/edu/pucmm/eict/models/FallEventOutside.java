package edu.pucmm.eict.models;


public class FallEventOutside {
    private String username;
    private String photo;
    private Double latitude;
    private Double longitude;
    private String dateTime;

    public FallEventOutside(String username, String photo, Double latitude, Double longitude, String dateTime) {
        this.username = username;
        this.photo = photo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
    }

    public FallEventOutside() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
