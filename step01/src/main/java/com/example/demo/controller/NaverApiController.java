package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.BlogInfo;
import com.example.demo.service.BlogService;
import com.example.demo.service.MovieService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class NaverApiController {
	
	private BlogService blogService;
	private MovieService movieService;
	
	@RequestMapping(value="/blog", method=RequestMethod.GET)
	public ResponseEntity<BlogInfo> getList(String query, Integer display) throws Exception {
		
		return blogService.findByQuery("대구", 10);
	}
}
