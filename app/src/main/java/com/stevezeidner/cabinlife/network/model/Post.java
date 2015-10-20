package com.stevezeidner.cabinlife.network.model;

/**
 * Created by szeidner on 10/9/15.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("totalViews")
    @Expose
    private Integer totalViews;
    @SerializedName("favorites")
    @Expose
    private Integer favorites;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("summary")
    @Expose
    private String summary;

    @Expose
    private String body;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The publishedAt
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * @param publishedAt The publishedAt
     */
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return The totalViews
     */
    public Integer getTotalViews() {
        return totalViews;
    }

    /**
     * @param totalViews The totalViews
     */
    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    /**
     * @return The favorites
     */
    public Integer getFavorites() {
        return favorites;
    }

    /**
     * @param favorites The favorites
     */
    public void setFavorites(Integer favorites) {
        this.favorites = favorites;
    }

    /**
     * @return The Latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The Latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

}