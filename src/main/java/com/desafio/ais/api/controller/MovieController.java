package com.desafio.ais.api.controller;

import com.desafio.ais.api.dto.MovieDTO;
import com.desafio.ais.api.model.Movie;
import com.desafio.ais.api.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Movie")
public class MovieController {

	@Autowired
	private MovieService service;

	@Autowired
	private ModelMapper modelMapper;

	@ApiOperation(value = "Save a movie")
	@PostMapping(value = "/movie")
	public ResponseEntity<?> saveMovie(@RequestBody MovieDTO movieDTO) {
		Movie movie = convertToEntity(movieDTO);
		return new ResponseEntity<>(service.saveMovie(movie), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get all movies")
	@GetMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getMovies() {
		return new ResponseEntity<>(service.getMovies(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get a specific movie")
	@GetMapping(value = "/movie/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie getMoviesById(@PathVariable Integer id) {
		return service.getMoviesById(id);
	}

	private Movie convertToEntity(MovieDTO movieDTO) {
		return modelMapper.map(movieDTO, Movie.class);
	}

}
