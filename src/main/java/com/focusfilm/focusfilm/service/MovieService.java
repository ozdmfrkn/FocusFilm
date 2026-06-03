package com.focusfilm.focusfilm.service;

import com.focusfilm.focusfilm.dto.MovieCreateDTO;
import com.focusfilm.focusfilm.entity.Movie;
import com.focusfilm.focusfilm.exception.MovieNotFoundException;
import com.focusfilm.focusfilm.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie kaydet(MovieCreateDTO dto) {
        Movie movie = new Movie();
        movie.setFilmAdi(dto.getFilmAdi());
        movie.setCikisYili(dto.getCikisYili());
        movie.setKategori(dto.getKategori());
        movie.setYonetmen(dto.getYonetmen());
        movie.setImdbPuani(dto.getImdbPuani());

        return movieRepository.save(movie);
    }

    public List<Movie> tumunuGetir() {
        return movieRepository.findAll();
    }

    public Movie idIleGetir(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Film bulunamadı. ID: " + id));
    }

    public Movie guncelle(Long id, MovieCreateDTO dto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Film bulunamadı. ID: " + id));

        movie.setFilmAdi(dto.getFilmAdi());
        movie.setCikisYili(dto.getCikisYili());
        movie.setKategori(dto.getKategori());
        movie.setYonetmen(dto.getYonetmen());
        movie.setImdbPuani(dto.getImdbPuani());

        return movieRepository.save(movie);
    }

    public void sil(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException("Film bulunamadı. ID: " + id);
        }
        movieRepository.deleteById(id);
    }
}