package com.focusfilm.focusfilm.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovieCreateDTO {

    @NotBlank
    private String filmAdi;

    @NotNull
    @Min(1888)
    private Integer cikisYili;

    @NotBlank
    private String kategori;

    @NotBlank
    private String yonetmen;

    @NotNull
    @Min(1)
    @Max(10)
    private Double imdbPuani;

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