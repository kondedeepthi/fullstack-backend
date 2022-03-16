package com.movies.bookmovie.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movies.bookmovie.client.MoviesClient;
import com.movies.bookmovie.config.CommonConstants;
import com.movies.bookmovie.service.DiscoverMovieService;

@RestController
@EnableFeignClients(basePackageClasses = MoviesClient.class)
@CrossOrigin(origins = "*")
public class BookMovieController {

	@Autowired
	private DiscoverMovieService service;

	/**
	 * API to fetch lst of Featured Movies
	 * 
	 * @param language
	 * @param sort_by
	 * @param include_adult
	 * @param include_video
	 * @param page
	 * @return
	 * @throws IOException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws JSONException
	 */
	@GetMapping(value = CommonConstants.DISCOVER_API, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getFeaturedMovies(@RequestParam("language") String language,
			@RequestParam("sort_by") String sort_by, @RequestParam("include_adult") Boolean include_adult,
			@RequestParam("include_video") Boolean include_video, @RequestParam("page") Integer page)
			throws IOException, NoSuchFieldException, SecurityException, JSONException {
		return service.getMovies(language, sort_by, include_adult, include_video, page);
	}

	/**
	 * API to fetch Search Movie
	 * 
	 * @param language
	 * @param results
	 * @param query
	 * @param include_adult
	 * @param page
	 * @return
	 * @throws IOException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws JSONException
	 */
	@GetMapping(value = CommonConstants.SEARCH_API, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> searchMovies(@RequestParam("language") String language,
			@RequestParam("query") String query,
			@RequestParam("include_adult") Boolean include_adult)
			throws IOException, NoSuchFieldException, SecurityException, JSONException {
		return service.searchMovies(language, query, include_adult);
	}

	/**
	 * API to fetch Movie Details
	 * 
	 * 
	 * @param movie_id
	 * @param language
	 * @return
	 * @throws IOException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws JSONException
	 */
	@GetMapping(value = CommonConstants.MOVIE_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> movieDetails(@PathVariable("movie_id") Integer movie_id,
			@RequestParam("language") String language)
			throws IOException, NoSuchFieldException, SecurityException, JSONException {
		return service.movieDetails(movie_id, language);
	}
}
