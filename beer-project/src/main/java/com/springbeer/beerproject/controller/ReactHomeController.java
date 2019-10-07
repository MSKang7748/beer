package com.springbeer.beerproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springbeer.beerproject.entity.MemberEntity;

@Controller
public class ReactHomeController { // 웹페이지 Home 으로 가는 컨트롤러 
	
	@GetMapping(value = {"/dashboard"})
	public String home() {
		
		return "dashhome";
	}

}
