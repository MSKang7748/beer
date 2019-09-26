package com.springbeer.beerproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/content")
public class ContentController { // 웹페이지 Home 으로 가는 컨트롤러 
	
	@RequestMapping(value = "/contentlist")
	public String contentList() {
		
		return "/contents/contentlist";
	}

	@RequestMapping(value = "/detail", method=RequestMethod.GET)
	public String productDetail(Model model) {
	
	return "/products/productdetail";
	}
	
	@RequestMapping(value = "/cart", method=RequestMethod.GET)
	public String productCart(Model model) {
	
	return "/products/cart";
	}
	
	@RequestMapping(value = "/checkout", method=RequestMethod.GET)
	public String productCheckout(Model model) {
	
	return "/products/checkout";
	}
	
}