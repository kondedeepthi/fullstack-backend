package com.movies.bookmovie.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiscoverMovieResultsResponse {

	private Boolean adult;
	private String backdrop_path;
	private List<Integer> genre_ids;
	private Integer id;
	private String original_language;
	private String original_title;
	private String overview;
	private Double popularity;
	private String poster_path;
	private String release_date;
	private String title;
	private Boolean video;
	private Double vote_average;
	private Integer vote_count;
}
