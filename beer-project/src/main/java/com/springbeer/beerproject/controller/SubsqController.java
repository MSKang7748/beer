package com.springbeer.beerproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springbeer.beerproject.common.Util;
import com.springbeer.beerproject.entity.MemberEntity;
import com.springbeer.beerproject.entity.LectureBoardFile;
import com.springbeer.beerproject.entity.Subscription;
import com.springbeer.beerproject.service.SubsqService;


@Controller
@RequestMapping(value="/subsq")
public class SubsqController {
	
	
	@Autowired
	SubsqService subsqService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String contentList(Model model, HttpSession session) {

		return "/subscripts/subsqlist";
	}
	
	
}