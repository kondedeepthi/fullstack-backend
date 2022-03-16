package com.movies.bookmovie.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;

public interface DiscoverMovieService {

	public Map<String, Object> getMovies(String language, String sort_by, Boolean include_adult, Boolean include_video,
			Integer page) throws IOException, NoSuchFieldException, SecurityException, JSONException;

	public Map<String, Object> searchMovies(String language, String query, Boolean include_adult) throws IOException, NoSuchFieldException, SecurityException, JSONException;

	public Map<String, Object> movieDetails(Integer movie_id, String language)
			throws IOException, NoSuchFieldException, SecurityException, JSONException;

}
