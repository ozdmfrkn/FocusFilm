package com.focusfilm.focusfilm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filmAdi;
    private Integer cikisYili;
    private String kategori;
    private String yonetmen;
    private Double imdbPuani;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilmAdi() {
        return filmAdi;
    }

    public void setFilmAdi(String filmAdi) {
        this.filmAdi = filmAdi;
    }

    public Integer getCikisYili() {
        return cikisYili;
    }

    public void setCikisYili(Integer cikisYili) {
        this.cikisYili = cikisYili;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getYonetmen() {
        return yonetmen;
    }

    public void setYonetmen(String yonetmen) {
        this.yonetmen = yonetmen;
    }

    public Double getImdbPuani() {
        return imdbPuani;
    }

    public void setImdbPuani(Double imdbPuani) {
        this.imdbPuani = imdbPuani;
    }
}