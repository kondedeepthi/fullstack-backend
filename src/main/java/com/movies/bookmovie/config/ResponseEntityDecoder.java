package com.movies.bookmovie.config;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.Encoder;

public class ResponseEntityDecoder implements Decoder {
	
	private Decoder decoder;

	public ResponseEntityDecoder(Decoder decoder) {
		this.decoder = decoder;
	}
	
	public Encoder feignEncoder() {
    	HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
    	ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
    	return new SpringEncoder(objectFactory);
	}

	@Override
	public Object decode(final Response response, Type type) throws IOException, FeignException {

		if (isParameterizeHttpEntity(type)) {
			type = ((ParameterizedType) type).getActualTypeArguments()[0];
			Object decodedObject = this.decoder.decode(response, type);

			return createResponse(decodedObject, response);
		}
		else if (isHttpEntity(type)) {
			return createResponse(null, response);
		}
		else {
			return this.decoder.decode(response, type);
		}
	}

	private boolean isParameterizeHttpEntity(Type type) {
		if (type instanceof ParameterizedType) {
			return isHttpEntity(((ParameterizedType) type).getRawType());
		}
		return false;
	}

	private boolean isHttpEntity(Type type) {
		if (type instanceof Class) {
			Class c = (Class) type;
			return HttpEntity.class.isAssignableFrom(c);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private <T> ResponseEntity<T> createResponse(Object instance, Response response) {

		HttpHeaders headers = new HttpHeaders();
		for (String key : response.headers().keySet()) {
			headers.put(key, new LinkedList<>(response.headers().get(key)));
		}

		return new ResponseEntity<>((T) instance, headers, HttpStatus.valueOf(response.status()));
	}
}
