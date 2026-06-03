package com.focusfilm.focusfilm.controller;

import com.focusfilm.focusfilm.dto.MovieCreateDTO;
import com.focusfilm.focusfilm.entity.Movie;
import com.focusfilm.focusfilm.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> filmEkle(@Valid @RequestBody MovieCreateDTO dto) {
        Movie kaydedilenMovie = movieService.kaydet(dto);
        return new ResponseEntity<>(kaydedilenMovie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> tumFilmler() {
        return ResponseEntity.ok(movieService.tumunuGetir());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> idIleGetir(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.idIleGetir(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> filmGuncelle(@PathVariable Long id, @Valid @RequestBody MovieCreateDTO dto) {
        Movie guncellenenMovie = movieService.guncelle(id, dto);
        return ResponseEntity.ok(guncellenenMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> filmSil(@PathVariable Long id) {
        movieService.sil(id);
        return ResponseEntity.noContent().build();
    }
}