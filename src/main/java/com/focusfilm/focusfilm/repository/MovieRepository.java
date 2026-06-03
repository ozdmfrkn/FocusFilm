package com.focusfilm.focusfilm.repository;

import com.focusfilm.focusfilm.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByKategori(String kategori);
}