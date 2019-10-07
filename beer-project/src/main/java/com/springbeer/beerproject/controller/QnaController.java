package com.springbeer.beerproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.springbeer.beerproject.entity.MemberEntity;
import com.springbeer.beerproject.entity.Notice;
import com.springbeer.beerproject.entity.Qna;
import com.springbeer.beerproject.service.NoticeService;
import com.springbeer.beerproject.service.QnaService;


@Controller
@RequestMapping(value="/qna")
public class QnaController {
	
	@Autowired
	QnaService qnaService;
	
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping(value = "/qnalist", method = RequestMethod.GET)
	public String qnatList(Model model) {

		List<Qna> Qnas = qnaService.findQnaList();
		
		List<Notice> notices = noticeService.loadNoteList();
		
		
		model.addAttribute("qna",Qnas);
		model.addAttribute("notice", notices);
		return "/qna/qnalist";
	}
	
	@GetMapping(value = "/qnawrite")
	public String subsqWrite(Model model, HttpSession session ) {
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		model.addAttribute("member",member);
		
	return "/qna/qnawrite";
	}
	
	@PostMapping(value = "/qnawrite") // PostMapping
	public String contentWrite(Qna qna, Model model, HttpSession session) {
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		String memberId = member.getMemberId();
		
		qna.setMemberId(memberId);
		qnaService.writeqna(qna);
		

	return "redirect:/qna/qnalist";
	}
	
	@GetMapping(value = "/qnadetail")
	public String qnaDetail(@RequestParam(name="qnaNo") int qnaNo, Model model, HttpSession session) {
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		if(member == null) {
			return "redirect:/member/login";
		}
		
		String memberId1 = member.getMemberId();
		
		List<Qna> qnaDetails = qnaService.qnadetail(qnaNo);
		Qna qna = qnaDetails.get(0);
		String memberId2 = qna.getMemberId();
		
		if(memberId1.equals(memberId2)) {
			model.addAttribute("qnadetail", qnaDetails);
			return "/qna/qnadetail";
			
		}
		if(member.getUserType().equals("admin")) {
			
			model.addAttribute("member",member);
			model.addAttribute("qnadetail", qnaDetails);
			return "/qna/qnadetail3";
		}
		
		model.addAttribute("qnadetail", qnaDetails);
	return "/qna/qnadetail2";
	}
	
	@GetMapping(value = "/answrite")
	public String ansWrite(@RequestParam(name="qnaNo") int qnaNo, Model model, HttpSession session) {

		List<Qna> qnaDetails = qnaService.qnadetail(qnaNo);
		
		model.addAttribute("qnadetail", qnaDetails);
		
		return "/qna/answrite";
	}
	
	
	
	@GetMapping(value = "/noticedetail")
	public String noticeDetail(@RequestParam(name="id") Long id, Model model, HttpSession session) {

		Notice noticeDetails = noticeService.noticeDetail(id);
		
		model.addAttribute("noticedetail", noticeDetails);
		
	return "/qna/noticedetail";
	}
	
	@PostMapping(value = "/ansupdate/{qnaNo}")
	public String ansWrite(@PathVariable("qnaNo") int qnaNo, Qna qna, Model model) {
		
		String ansContent = qna.getAnsContent();
		List<Qna> qnas = qnaService.qnadetail(qnaNo);
		Qna qnaa = qnas.get(0);
		
		int ansCheck = 1;
		
		qnaa.setAnsContent(ansContent);
		qnaa.setAnsCheck(ansCheck);
		
		qnaService.writeqna(qnaa);

	return "redirect:/qna/qnalist";
	}
	
	
	@RequestMapping(value = "/qnaupdate", method = RequestMethod.GET)
	public String qnaUpdate(@RequestParam(name="qnaNo") int qnaNo, Model model,  HttpSession session) {
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		List<Qna> qnaUpdate = qnaService.qnadetail(qnaNo);		
		
		model.addAttribute("qnaupdate", qnaUpdate);

		model.addAttribute("member", member);

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