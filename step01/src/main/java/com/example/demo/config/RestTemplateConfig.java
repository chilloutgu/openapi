package com.example.demo.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Bean
	public HttpClient httpClient() {
		HttpClient httpClient = HttpClientBuilder.create()
									.setMaxConnTotal(1000)
									.setMaxConnPerRoute(30)
									.build();
		
		return httpClient;
	}
	
	
	@Bean
	public HttpComponentsClientHttpRequestFactory requestFactory() {
		HttpComponentsClientHttpRequestFactory requestFactory = 
				new HttpComponentsClientHttpRequestFactory();
		
		requestFactory.setReadTimeout(5000);
		requestFactory.setConnectTimeout(3000);
		requestFactory.setHttpClient(httpClient());
		
		return requestFactory;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(requestFactory());
	}
}
