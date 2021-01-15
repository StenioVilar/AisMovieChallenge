package com.desafio.ais.api.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.ais.api.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Optional<Movie> findById(Integer id);

}
