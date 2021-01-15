package com.desafio.ais.api;


import com.desafio.ais.api.dao.MovieRepository;
import com.desafio.ais.api.dto.MovieDTO;
import com.desafio.ais.api.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootDesafioAisApplicationTests {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieRepository movieRepository;

	@Before
	public void init() {
		Movie movie = new Movie(1, "Movie Name");
		when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
	}

	@Test
	public void find_movieId_OK() throws Exception {

		mockMvc.perform(get("/movie/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Movie Name")));
		verify(movieRepository, times(1)).findById(1);

	}

	@Test
	public void find_movieIdNotFound_404() throws Exception {
		mockMvc.perform(get("/movie/5")).andExpect(status().isNotFound());
	}

	@Test
	public void find_allmovie_OK() throws Exception {

		List<Movie> movies = Arrays.asList(
				new Movie(1, "The Room"),
				new Movie(2, "Stranger Things"));

		when(movieRepository.findAll()).thenReturn(movies);

		mockMvc.perform(get("/movie"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("The Room")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Stranger Things")));

		verify(movieRepository, times(1)).findAll();
	}

	@Test
	public void find_allmovieNotFound_404() throws Exception {
		mockMvc.perform(get("/movie")).andExpect(status().isNotFound());
	}

	@Test
	public void save_movie_OK() throws Exception {

		Movie newMovie = new Movie(1,"The Butterfly Effect");
		MovieDTO newMovieDTO = new MovieDTO("The Butterfly Effect");
		when(movieRepository.save(any(Movie.class))).thenReturn(newMovie);

		mockMvc.perform(post("/movie")
				.content(om.writeValueAsString(newMovieDTO))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("The Butterfly Effect")));
		verify(movieRepository, times(1)).save(any(Movie.class));

	}

}
