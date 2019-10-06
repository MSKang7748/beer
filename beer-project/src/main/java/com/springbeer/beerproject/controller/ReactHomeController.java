package com.springbeer.beerproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactHomeController { // 웹페이지 Home 으로 가는 컨트롤러 
	
	@GetMapping(value = {"/dashboard"})
	public String home() {
		return "dashhome";
	}

}
