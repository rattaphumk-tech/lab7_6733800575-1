package com.example.lab7.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private String platform;
    private Double rating;
    private Double price;
    private String discountType; // "NONE", "STUDENT", "SEASONAL"
    private LocalDate releaseDate;

    @Transient
    private Double finalPrice;

    public Game() {}

    public Game(String title, String genre, String platform, Double rating, Double price, String discountType, LocalDate releaseDate) {
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.rating = rating;
        this.price = price;
        this.discountType = discountType;
        this.releaseDate = releaseDate;
    }

    // --- Helper Methods สำหรับ Thymeleaf ---
    
    // แปลงประเภทส่วนลดเป็นชื่อภาษาไทยที่ list.html เรียกใช้
    public String getDiscountName() {
        if ("STUDENT".equalsIgnoreCase(discountType)) return "ส่วนลดนักศึกษา (10%)";
        if ("SEASONAL".equalsIgnoreCase(discountType)) return "ส่วนลดเทศกาล (20%)";
        return "ราคาปกติ";
    }

    // คำนวณราคาหลังหักส่วนลดเพื่อป้องกัน NullPointerException
    public Double getFinalPrice() {
        if (finalPrice != null) return finalPrice;
        if (price == null) return 0.0;
        
        if ("STUDENT".equalsIgnoreCase(discountType)) return price * 0.9;
        if ("SEASONAL".equalsIgnoreCase(discountType)) return price * 0.8;
        return price;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public void setFinalPrice(Double finalPrice) { this.finalPrice = finalPrice; }
}