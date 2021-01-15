package com.desafio.ais.api.service;

import com.desafio.ais.api.dao.MovieRepository;
import com.desafio.ais.api.error.ResourceNotFoundException;
import com.desafio.ais.api.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }

    public List<Movie> getMovies() {
        List<Movie> movies = repository.findAll();
        if(movies.isEmpty()){
            throw new ResourceNotFoundException("No movies registered");
        }
        return movies;
    }

    public Movie getMoviesById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie id: "+ id));
    }

}
