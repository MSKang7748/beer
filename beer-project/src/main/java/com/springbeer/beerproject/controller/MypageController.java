package com.springbeer.beerproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbeer.beerproject.entity.Mypage;

@Controller
@RequestMapping(value="/mypage")
public class MypageController {
	
	@RequestMapping(path = "/mypagelist", method = RequestMethod.GET)
	public String showCustomerForm(Model model, Mypage mypage) {
		
		String name = "이용은";
		String email="yongen001";
		String div = "darkbeer";
		String id="lye";
		
		Mypage mp = new Mypage();
		mp.setEmail(email);
		mp.setName(name);
		mp.setDiv(div);
		mp.setId(id);
		
		model.addAttribute("mypage", mp);
	
		return "/mypage/mypagelist";
	}
	
	@RequestMapping(path = "/update", method = RequestMethod.GET)
	public String updateForm (Model model, Mypage mypage) {   
		
		String name = "이용은";
		String email="yongen001";
		String div = "darkbeer";
		String id="lye";
		
		Mypage mp = new Mypage();
		mp.setEmail(email);
		mp.setName(name);
		mp.setDiv(div);
		mp.setId(id);
		
		model.addAttribute("mypage", mp);

		return "mypage/mypageupdate"; 
	}
}
