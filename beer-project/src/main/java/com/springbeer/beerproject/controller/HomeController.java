package com.springbeer.beerproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController { // 웹페이지 Home 으로 가는 컨트롤러 
	
	@RequestMapping(value = {"/","/home"})
	public String home() {
		return "home";
	}

}