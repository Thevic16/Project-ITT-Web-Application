package edu.pucmm.eict.models;


public class FallEventOutside {
    private String username;
    private String password;
    private String photo;
    private Double latitude;
    private Double longitude;
    private String dateTime;
    private String hour;

    public FallEventOutside(String username,String password, String photo, Double latitude, Double longitude, String dateTime,String hour) {
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
        this.hour = hour;
    }

    public FallEventOutside() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
