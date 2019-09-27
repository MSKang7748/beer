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

import com.springbeer.beerproject.entity.MemberEntity;
import com.springbeer.beerproject.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberManageController {
	
	@Autowired
	MemberService memberService;

	@GetMapping("/list")
	public String showList(Model model) {
		
		List<MemberEntity> members = memberService.findAllMembers();
		model.addAttribute("members", members);
		
		return "member/list";
	}
	
	@GetMapping("/register")
	public String showRegisterForm() {
		return "member/register";
	}
	
	@PostMapping("/register")
	public String register(MemberEntity member, Model model) {
//		if (!member.getPasswd().equals(confirm)) {
//			return "redirect:/account/register";
//		}		
		
//		System.out.println(member); //에러 찾을 때 콘솔에 연결 데이터 확인.
		memberService.registerMember(member);		
		return "/home";
	}
	
	/////////////////////
	@GetMapping("/login")
	public String showLoginForm() {		
		return "member/login";  		
	}
	
	@PostMapping("/login")
	public String login(String memberId, String passwd, HttpSession session) {
		
		//passwd = Util.getHashedString(passwd, "SHA-256"); //암호화		
		MemberEntity member = memberService.findMemberByIdAndPasswd(memberId, passwd);
		//MemberEntity member = memberService.findMemberById(memberId);
		
		if (member != null) { //로그인 가능 -> 로그인 처리 : 세션에 로그인 데이터 저장
			session.setAttribute("loginuser", member); //로그인 처리
			return "redirect:/home"; 			
		} else { //로그인 실패
			return "member/login";			
		}	
	}
		
	@GetMapping("/logout")  
	public String logout(HttpSession session) {
		
	 session.removeAttribute("loginuser"); 
	 return "redirect:/home"; 
	 }
	
	 ////////////////////////
	
	@GetMapping(path = "/mypageupdate")
	public String mypageupdate(Model model, HttpSession session) {
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		String memberId = member.getMemberId();
		
		MemberEntity mp = memberService.findByMemberId(memberId); 

		model.addAttribute("mypage", mp);
	
		return "/mypage/mypageupdate";
	}
	
	@PostMapping(path="/mypageupdate")
	public String mypageupdatewrite(Model model, HttpSession session) {

		return "/mypage/mypagelist";
	}
	

	@RequestMapping(path = "/mypagelist/{memberId}", method = RequestMethod.GET)
	public String mypage(@PathVariable String memberId, Model model, HttpSession session) {
		
		MemberEntity mp = memberService.findByMemberId(memberId); 

		model.addAttribute("mypage", mp);
	
		return "/mypage/mypagelist";
	}
	
	@GetMapping(path = "/mypagelist")
	public String mypagereturn(Model model, HttpSession session) {
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		String memberId = member.getMemberId();
		MemberEntity mp = memberService.findByMemberId(memberId); 

		model.addAttribute("mypage", mp);
	
		
		return "/mypage/mypagelist";
	}
	
	
}
