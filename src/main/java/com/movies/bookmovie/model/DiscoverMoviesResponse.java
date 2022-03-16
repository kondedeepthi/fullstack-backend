package com.movies.bookmovie.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiscoverMoviesResponse {
	
	private Integer page;
	private List<DiscoverMovieResultsResponse> results;
	private Integer total_pages;
	private Integer total_results;
}
