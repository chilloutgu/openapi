package com.example.demo.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BlogInfo {
	private String total;
	private List<BlogItem> items;
	
}
