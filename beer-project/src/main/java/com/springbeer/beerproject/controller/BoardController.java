package com.springbeer.beerproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springbeer.beerproject.common.Util;
import com.springbeer.beerproject.entity.Lecture;
import com.springbeer.beerproject.entity.LectureBoard;
import com.springbeer.beerproject.entity.LectureBoardFile;
import com.springbeer.beerproject.entity.MemberEntity;
import com.springbeer.beerproject.service.BoardService;


@Controller
@RequestMapping(value="/subsq")
public class BoardController {
	
	
	@Autowired
	BoardService boardService;
	
	@GetMapping(value = "/lecture")
	public String lecture(Model model, HttpSession session) {
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		int memberNo = member.getMemberNo();
		
		String usertype = member.getUserType();
		
			if (usertype.equals("admin")) {
	
				List<Lecture> lecturelist = boardService.findlectureByMemberNo(memberNo);
				model.addAttribute("lecturelist", lecturelist);
				
				return "/lectureboard/lecturelist";
			}
		
			if (usertype.equals("specialist")) {
				
				List<Lecture> lecturelist = boardService.findlectureByMemberNo(memberNo);
				model.addAttribute("lecturelist", lecturelist);
				
				return "/lectureboard/lecturelist";
			}
		
		List<Lecture> lecturelist = boardService.findlecture();
		model.addAttribute("lecturelist", lecturelist);
		
		return "/lectureboard/lecturelist2";
	}
	
	@GetMapping(value="/mylecture")
	public String mylecture(Model model, HttpSession session) {
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		int memberNo = member.getMemberNo();
		
		List<Lecture> lecturelist = boardService.findlectureByMemberNo(memberNo);
		model.addAttribute("lecturelist", lecturelist);
		
		return "/lectureboard/lecturelist2";
	}
	
	@GetMapping(value ="/lecturewrite")
	public String lecturewrite(Model model) {
		
		return "/lectureboard/lecturewrite";
	}
	
	@PostMapping(value="/lecturemake")
	public String lecturemake(Lecture lecture, Model model, HttpSession session) {
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		int memberNo = member.getMemberNo();
		lecture.setMemberNo(memberNo);
		
		boardService.addLecture(lecture);
		
		return "redirect:/subsq/lecture";
	}
	
	@GetMapping(value = "/boardlist")
	public String lectureList(@RequestParam(name="lectureNo") int lectureNo, Model model, HttpSession session) {
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		String usertype = member.getUserType();
		
			if (usertype.equals("admin")) {
	
				List<LectureBoard> lecturelist = boardService.findlectureByLectureNo(lectureNo); 
				List<Lecture> lecture = boardService.findLectureContentByLectureNo(lectureNo);
				
				model.addAttribute("lecture",lecture);
				model.addAttribute("Board",lecturelist);
				return "/lectureboard/boardlist";
			}
		
			if (usertype.equals("specialist")) {
				
				List<LectureBoard> lecturelist = boardService.findlectureByLectureNo(lectureNo); 
				List<Lecture> lecture = boardService.findLectureContentByLectureNo(lectureNo);
				
				model.addAttribute("lecture",lecture);
				model.addAttribute("Board",lecturelist);
				return "/lectureboard/boardlist";
			}
		
		List<LectureBoard> lecturelist = boardService.findlectureByLectureNo(lectureNo); 
		model.addAttribute("Board",lecturelist);
		return "/lectureboard/boardlist2";
	}
	
	@RequestMapping(value = "/lecturelist", method = RequestMethod.GET) // 로그인 후에 list누르면 구독 한 것(강좌)에 포함된 글만 보이게. 수정요망
	public String contentList(Model model, HttpSession session) {

		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		if (member == null) {
			return "redirect:/member/login";
		}
		String usertype = member.getUserType();
		
			if (usertype.equals("admin")) {
				
				int memberNo = member.getMemberNo();
				List<Lecture> lectures = boardService.findlectureByMemberNo(memberNo); //memberNo column이 entity 안에 있어야 만들 수 있음.
			    
				model.addAttribute("lectures",lectures);
				return "/lectureboard/lecturelist";
			}
		
			if (usertype.equals("specialist")) {
				
				int memberNo = member.getMemberNo();
				List<Lecture> lectures = boardService.findlectureByMemberNo(memberNo); //memberNo column이 entity 안에 있어야 만들 수 있음.

				model.addAttribute("lecturelist",lectures);
				return "/lectureboard/lecturelist";
			}

		List<Lecture> lecturelist = boardService.findlecture();
		
		model.addAttribute("lecturelist", lecturelist);

		return "/lectureboard/lecturelist2";
	}

	
	@GetMapping(value = "/write/{lectureNo}")
	public String subsqWrite(@PathVariable int lectureNo, Model model, HttpSession session) {

		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		int memberNo = member.getMemberNo();
		Lecture lectures = boardService.findlectureByMemberNoAndLectureNo(memberNo, lectureNo);
		
		model.addAttribute("lectures", lectures);
		model.addAttribute("member",member);
		
	return "/lectureboard/boardwrite";
	
	}

	@PostMapping(value = "/write/{lectureNo}")
	public String contentWrite(@PathVariable int lectureNo, LectureBoard lectureBoard, MultipartHttpServletRequest req) {	
		
			MultipartFile mf = req.getFile("lbfile");
			boolean test = mf.isEmpty();
			
			if (test == false) {
		
				ServletContext application = req.getServletContext();
				String path = application.getRealPath("/images/");

				String subsqUserFileName =  mf.getOriginalFilename();
				if (subsqUserFileName.contains("\\")) {
	 
					subsqUserFileName = subsqUserFileName.substring(subsqUserFileName.lastIndexOf("\\") + 1);
				}
				String subsqSavedFileName = Util.makeUniqueFileName(subsqUserFileName);

						try {

							mf.transferTo(new File(path, subsqSavedFileName));

							LectureBoardFile boardFile = new LectureBoardFile();
							boardFile.setSubsqSavedFileName(subsqSavedFileName);
							boardFile.setSubsqUserFileName(subsqUserFileName);
							ArrayList<LectureBoardFile> fileList = new ArrayList<LectureBoardFile>();
							fileList.add(boardFile);
							lectureBoard.setLectureboardfile(fileList); // 일일이 VO에 담는 것을 Controller에서 옮김.
							lectureBoard.setLectureNo(lectureNo);
							
							
							boardService.writeSubsq(lectureBoard);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

			return "redirect:/subsq/boardlist?lectureNo=" + lectureNo;
	}
	
	@RequestMapping(value = "/detail", method=RequestMethod.GET)
	public String boardDetail(@RequestParam(name="boardNo") int boardNo, Model model, HttpSession session) {
		
		LectureBoard boardDetails = boardService.boardDetail(boardNo);
		int lectureNo = boardDetails.getLectureNo();
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		int memberNo = member.getMemberNo();
		
		Lecture lectures = boardService.findlectureByMemberNoAndLectureNo(memberNo, lectureNo);
		
		model.addAttribute("boarddetail", boardDetails);
		model.addAttribute("lectures",lectures);
		
	return "/lectureboard/boarddetail";
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.GET)
	public String boardUpdate(@RequestParam(name="boardNo") int boardNo, Model model, HttpSession session) {
		
		LectureBoard boardDetails = boardService.boardDetail(boardNo);
		int lectureNo = boardDetails.getLectureNo();
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		int memberNo = member.getMemberNo();
		
		Lecture lectures = boardService.findlectureByMemberNoAndLectureNo(memberNo, lectureNo);
		
		model.addAttribute("boarddetail", boardDetails);
		model.addAttribute("lectures",lectures);
		model.addAttribute("member",member);
		
		LectureBoard boardUpdate = boardService.boardDetail(boardNo);	

		model.addAttribute("boardupdate", boardUpdate);

	return "/lectureboard/boardupdate";
	
	}
	
	@PostMapping(value = "/updatewrite/{boardNo}")
	public String boardUpdateWrite(@PathVariable int boardNo, LectureBoard lectureBoard, MultipartHttpServletRequest req) {
		
		LectureBoard boardDetails = boardService.boardDetail(boardNo);
		int lectureNo = boardDetails.getLectureNo();
		
		MultipartFile mf = req.getFile("lbfile");
		boolean test = mf.isEmpty();
		
		if (test == false) { // 파일 안 넣어도 업로드 가능 하려면  @Query사용해야 될 듯.
	
			ServletContext application = req.getServletContext();
			String path = application.getRealPath("/images/");

			String subsqUserFileName =  mf.getOriginalFilename();
			if (subsqUserFileName.contains("\\")) {
 
				subsqUserFileName = subsqUserFileName.substring(subsqUserFileName.lastIndexOf("\\") + 1);
			}
			String subsqSavedFileName = Util.makeUniqueFileName(subsqUserFileName);

					try {

						mf.transferTo(new File(path, subsqSavedFileName));

						LectureBoardFile boardFile = new LectureBoardFile();
						boardFile.setSubsqSavedFileName(subsqSavedFileName);
						boardFile.setSubsqUserFileName(subsqUserFileName);
						ArrayList<LectureBoardFile> fileList = new ArrayList<LectureBoardFile>();
						fileList.add(boardFile);
						lectureBoard.setLectureboardfile(fileList); // 일일이 VO에 담는 것을 Controller에서 옮김.
						lectureBoard.setLectureNo(lectureNo);

						boardService.writeSubsq(lectureBoard);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

	return "redirect:/subsq/boardlist?lectureNo=" + lectureNo;
	}
	
	@GetMapping(value = "/delete")
	public String subsqDelete(@RequestParam(name="boardNo") int boardNo, Model model) {

		boardService.boarddelete(boardNo);

	return "redirect:/subsq/list";
	}
	
}