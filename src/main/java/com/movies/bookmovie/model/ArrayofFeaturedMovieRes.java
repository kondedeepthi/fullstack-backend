package com.movies.bookmovie.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "featureMoviesResponse"
})
@Generated("jsonschema2pojo")
public class ArrayofFeaturedMovieRes {
	
	@JsonProperty("featureMoviesResponse")
	private FeatureMoviesResponse featureMoviesResponse;

	public FeatureMoviesResponse getFeatureMoviesResponse() {
		return featureMoviesResponse;
	}

	public void setFeatureMoviesResponse(FeatureMoviesResponse featureMoviesResponse) {
		this.featureMoviesResponse = featureMoviesResponse;
	}
	
}
