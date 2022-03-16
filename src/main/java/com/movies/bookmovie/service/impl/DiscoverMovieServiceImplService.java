package com.movies.bookmovie.service.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.bookmovie.client.MoviesClient;
import com.movies.bookmovie.config.ResponseEntityDecoder;
import com.movies.bookmovie.service.DiscoverMovieService;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.Response;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Service
public class DiscoverMovieServiceImplService implements DiscoverMovieService {

	@Autowired
	private MoviesClient moviesClient;

	@Value("${movies.api.key}")
	private String apiKey;

	private final ResponseEntityDecoder decoder = null;

	@Autowired
	public DiscoverMovieServiceImplService(Decoder decoder, Encoder encoder, Client client, Contract contract) {
		this.moviesClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
				.requestInterceptor(new BasicAuthRequestInterceptor("user", "user"))
				.target(MoviesClient.class, "http://api.themoviedb.org");
	}

	@Override
	public Map<String, Object> getMovies(String language, String sort_by, Boolean include_adult, Boolean include_video,
			Integer page) throws IOException, NoSuchFieldException, SecurityException, JSONException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		Response response = moviesClient.getFeaturedMovies(apiKey, language, sort_by, include_adult, include_video,
				page);
		URL url = new URL(response.request().url());
		Map<String, Object> map = mapper.readValue(url, Map.class);
		return map;
	}

	@Override
	public Map<String, Object> searchMovies(String language, String query, Boolean include_adult) throws IOException, NoSuchFieldException, SecurityException, JSONException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		Response response = moviesClient.searchMovies(apiKey, language, query, include_adult);
		URL url = new URL(response.request().url());
		Map<String, Object> map = mapper.readValue(url, Map.class);

		return map;
	}

	@Override
	public Map<String, Object> movieDetails(Integer movie_id, String language)
			throws IOException, NoSuchFieldException, SecurityException, JSONException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		Response response = moviesClient.movieDetails(movie_id, apiKey, language);
		URL url = new URL(response.request().url());
		Map<String, Object> map = mapper.readValue(url, Map.class);

		return map;
	}
}
