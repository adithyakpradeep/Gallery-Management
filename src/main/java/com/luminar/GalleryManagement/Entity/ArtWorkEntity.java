package com.luminar.GalleryManagement.Entity;

import jakarta.persistence.*;

@Entity
public class ArtWorkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artwork_id") // Map the id field to the existing artwork_id column
    private Long id;

    private String title;
    private double price;
    private String type;

    @Column(name = "temp_url") // Map tempUrl to temp_url column
    private String tempUrl;

    @Column(name = "image_url") // Map imageUrl to image_url column
    private String imageUrl;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "artist_id", referencedColumnName = "id")
    private ArtistEntity artist;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTempUrl() {
        return tempUrl;
    }

    public void setTempUrl(String tempUrl) {
        this.tempUrl = tempUrl;
    }
}
