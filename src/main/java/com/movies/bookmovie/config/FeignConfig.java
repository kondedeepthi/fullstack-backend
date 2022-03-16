package com.movies.bookmovie.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableFeignClients
@Import(FeignClientsConfiguration.class)
public class FeignConfig {
	
	@Bean
	feign.Logger.Level feignLoggerLevel() {
		return feign.Logger.Level.BASIC;
	}
}
