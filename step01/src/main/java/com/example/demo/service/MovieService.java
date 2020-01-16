package com.example.demo.service;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.domain.MovieInfo;

import lombok.Getter;
import lombok.Setter;

@Service
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix="naver.openapi")
@Getter @Setter
public class MovieService {
	@Autowired
	private RestTemplate restTemplate;
	
	private String movieSearchUrl;
	private String clientId;
	private String clientSecret;
	
	public ResponseEntity<MovieInfo> findByQuery(String query, Integer display) throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.add("X-Naver-Client-Id", clientId);
		httpHeaders.add("X-Naver-Client-Secret", clientSecret);
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(movieSearchUrl)
														.queryParam("query", query)
														.queryParam("display", display)
														.build();
		
		return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, new HttpEntity(httpHeaders), MovieInfo.class);
	}
}
