package com.movies.bookmovie.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movies.bookmovie.config.CommonConstants;
import com.movies.bookmovie.config.ResponseEntityDecoder;

import feign.Response;

@FeignClient(value = CommonConstants.CLIENT_VALUE, url = "${movies.tmdb.uri}", configuration = ResponseEntityDecoder.class)
public interface MoviesClient {

	@RequestMapping(method = RequestMethod.GET, path = CommonConstants.CLIENT_DISCOVER, produces = MediaType.APPLICATION_JSON_VALUE)
	Response getFeaturedMovies(@RequestParam("api_key") String api_key, @RequestParam("language") String language,
			@RequestParam("sort_by") String sort_by, @RequestParam("include_adult") Boolean include_adult,
			@RequestParam("include_video") Boolean include_video, @RequestParam("page") Integer page);

	@RequestMapping(method = RequestMethod.GET, path = CommonConstants.CLIENT_SEARCH, produces = MediaType.APPLICATION_JSON_VALUE)
	Response searchMovies(@RequestParam("api_key") String api_key, @RequestParam("language") String language, @RequestParam("query") String query,
			@RequestParam("include_adult") Boolean include_adult);

	@RequestMapping(method = RequestMethod.GET, path = CommonConstants.CLIENT_MOVIE_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
	Response movieDetails(@PathVariable("movie_id") Integer movie_id, @RequestParam("api_key") String api_key,
			@RequestParam("language") String language);
}
