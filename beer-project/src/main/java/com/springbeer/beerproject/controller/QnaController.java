package com.springbeer.beerproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springbeer.beerproject.entity.Qna;
import com.springbeer.beerproject.service.QnaService;


@Controller
@RequestMapping(value="/qna")
public class QnaController {
	
	@Autowired
	QnaService qnaService;
	
	@RequestMapping(value = "/qnalist", method = RequestMethod.GET)
	public String qnatList(Model model
		//	,@RequestParam(required = false, defaultValue = "1") int page
		//	, @RequestParam(required = false, defaultValue = "1") int range, HttpSession session
			) {

		List<Qna> Qnas = qnaService.findQnaList();

		model.addAttribute("qna",Qnas);

		return "/qna/qnalist";
	}
	
	@GetMapping(value = "/qnawrite")
	public String subsqWrite() {
		
	return "/qna/qnawrite";
	}
	
	@PostMapping(value = "/qnawrite") // PostMapping
	public String contentWrite(Qna qna, Model model) {

		qnaService.writeqna(qna);

	return "redirect:/qna/qnalist";
	}
	
	@GetMapping(value = "/qnadetail")
	public String qnaDetail(@RequestParam(name="qnaNo") int qnaNo, Model model) {

		List<Qna> qnaDetails = qnaService.qnadetail(qnaNo);	
		model.addAttribute("qnadetail", qnaDetails);
		
	return "/qna/qnadetail";
	}
	
	@RequestMapping(value = "/qnaupdate", method = RequestMethod.GET)
	public String qnaUpdate(@RequestParam(name="qnaNo") int qnaNo, Model model) {
		
		List<Qna> qnaUpdate = qnaService.qnadetail(qnaNo);		
		
		model.addAttribute("qnaupdate", qnaUpdate);

	return "/qna/qnaupdate";
	}

	@PostMapping(value ="/qnaupdates/{qnaNo}")
	public String qnaUpdatewrite(@PathVariable("qnaNo") int qnaNo, Qna qna, Model model) {
		
		qnaService.updateqna(qna);
		
		return "redirect:/qna/qnalist";
	}
	
	
	
	@PostMapping(value = "/updatewrite")
	public String qnaUpdateWrite(Model model, Qna qna) {
		
		
	return "redirect:/qna/qnalist";
	}
	
	@RequestMapping(value = "/qnadelete", method=RequestMethod.GET)
	public String qnaDelete(@RequestParam(name="qnaNo") int qnaNo, Model model) {

		qnaService.qnadelete(qnaNo);

	return "redirect:/qna/qnalist";
	}


}