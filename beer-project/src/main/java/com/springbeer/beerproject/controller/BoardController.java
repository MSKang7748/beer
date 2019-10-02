package com.springbeer.beerproject.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
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
import com.springbeer.beerproject.entity.Subscription;
import com.springbeer.beerproject.service.BoardService;


@Controller
@RequestMapping(value="/subsq")
public class BoardController {
	
	
	@Autowired
	BoardService boardService;
	
	@GetMapping(value = "/lecture") // home에서 board 선택하러 들어갈 때 강좌별로 먼저 보이는 list 연결
	public String lecture(Model model, HttpSession session) {
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		int memberNo = member.getMemberNo();
		int deleteNo = 1;
		
		String usertype = member.getUserType();
		
			if (usertype.equals("admin")) {
	
				List<Lecture> lecturelist = boardService.findlectureByMemberNo(memberNo, deleteNo);
				model.addAttribute("lecturelist", lecturelist);
				
				return "/lectureboard/lecturelist";
			}
		
			if (usertype.equals("specialist")) { // specialist로 분류된 사용자일 경우 admin과 같은 page 보여줌.
				
				List<Lecture> lecturelist = boardService.findlectureByMemberNo(memberNo, deleteNo);
				model.addAttribute("lecturelist", lecturelist);
				
				return "/lectureboard/lecturelist";
			}
		
		List<Lecture> lecturelist = boardService.findlecture(deleteNo); // admin, specialist가 아닌 나머지가 들어가는 lecturelist
		model.addAttribute("lecturelist", lecturelist);
		
		return "/lectureboard/lecturelist2";
	}
	
	@GetMapping(value="/mylecture")
	public String mylecture(Model model, HttpSession session) {
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		int memberNo = member.getMemberNo();
		
		int deleteNo = 1;
		
		String usertype = member.getUserType();
		
		if (usertype.equals("admin")) {

			List<Lecture> lecturelist = boardService.findlectureByMemberNo(memberNo, deleteNo);
			model.addAttribute("lecturelist", lecturelist);
			
			return "/lectureboard/lecturelist";
		}
	
		if (usertype.equals("specialist")) { // specialist로 분류된 사용자일 경우 admin과 같은 page 보여줌.
			
			List<Lecture> lecturelist = boardService.findlectureByMemberNo(memberNo, deleteNo);
			model.addAttribute("lecturelist", lecturelist);
			
			return "/lectureboard/lecturelist";
		}
		
		List<Lecture> lecturelist = boardService.findsubsqByMemberNo(memberNo, deleteNo); // 일반 user의 subscription table 내용 가져옴. // checkbox 한 번 더 클릭하면 구독 취소하기로 바뀌는 것 추가하기.
		
		model.addAttribute("lecturelist", lecturelist);
		
		return "/lectureboard/lecturelist2";
	}
	
	@GetMapping(value ="/lecturewrite")
	public String lecturewrite(Model model) { // lecturewrite로 이동만 해주는 메소드.
		
		return "/lectureboard/lecturewrite";
	}
	
	@PostMapping(value="/lecturemake")
	public String lecturemake(Lecture lecture, Model model, HttpSession session) {
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		int memberNo = member.getMemberNo();
		lecture.setMemberNo(memberNo);
		
		boardService.addLecture(lecture); // lecture강좌 등록 하는 것 연결.
		
		return "redirect:/subsq/lecture";
	}
	
	@GetMapping(value = "/boardlist")
	public String lectureList(@RequestParam(name="lectureNo") int lectureNo, Model model, HttpSession session) {
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		String usertype = member.getUserType();
		
		int deletedNo = 1;
		
			if (usertype.equals("admin")) {
	
				List<LectureBoard> lecturelist = boardService.findlectureByLectureNo(lectureNo, deletedNo); 
				List<Lecture> lecture = boardService.findLectureContentByLectureNo(lectureNo);
				
				model.addAttribute("lecture",lecture);
				model.addAttribute("Board",lecturelist);
				return "/lectureboard/boardlist";
			}
		
			if (usertype.equals("specialist")) {

				List<LectureBoard> lecturelist = boardService.findlectureByLectureNo(lectureNo, deletedNo); 
				List<Lecture> lecture = boardService.findLectureContentByLectureNo(lectureNo); // boardlist에 띄우기 위해 
				
				model.addAttribute("lecture",lecture);
				model.addAttribute("Board",lecturelist);
				return "/lectureboard/boardlist";
			}

			List<LectureBoard> lecturelist = boardService.findlectureByLectureNo(lectureNo, deletedNo); // lecture No에 따른 boardlist로 이동.
		List<Lecture> lecture = boardService.findLectureContentByLectureNo(lectureNo);
		
		model.addAttribute("lecture",lecture);
		model.addAttribute("Board",lecturelist);
		
		return "/lectureboard/boardlist2";
	}
	
	@RequestMapping(value = "/lecturelist", method = RequestMethod.GET) // 로그인 후에 list누르면 구독 한 것(강좌)에 포함된 글만 보이게. 수정요망
	public String contentList(Model model, HttpSession session) {

		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		int deleteNo = 1;
		
		if (member == null) {
			return "redirect:/member/login";
		}
		String usertype = member.getUserType();
		
			if (usertype.equals("admin")) {
				
				int memberNo = member.getMemberNo();
				List<Lecture> lecturelist = boardService.findlectureByMemberNo(memberNo, deleteNo); //memberNo column이 entity 안에 있어야 만들 수 있음.
			    
				model.addAttribute("lectures",lecturelist);
				return "/lectureboard/lecturelist";
			}
		
			if (usertype.equals("specialist")) {
				
				int memberNo = member.getMemberNo();
				List<Lecture> lecturelist = boardService.findlectureByMemberNo(memberNo, deleteNo); //memberNo column이 entity 안에 있어야 만들 수 있음.

				model.addAttribute("lecturelist",lecturelist);
				return "/lectureboard/lecturelist";
			}

		List<Lecture> lecturelist = boardService.findlecture(deleteNo);
		
		model.addAttribute("lecturelist", lecturelist);

		return "/lectureboard/lecturelist2";
	}

	
	@GetMapping(value = "/write/{lectureNo}")
	public String subsqWrite(@PathVariable int lectureNo, Model model, HttpSession session) { // lecture를 가져와서 boardwrite페이지에 연결함.

		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		int memberNo = member.getMemberNo();
		Lecture lectures = boardService.findlectureByMemberNoAndLectureNo(memberNo, lectureNo);
		
		model.addAttribute("lectures", lectures);
		model.addAttribute("member",member);
		
	return "/lectureboard/boardwrite";
	
	}

	@PostMapping(value = "/write/{lectureNo}")
	public String contentWrite(@PathVariable int lectureNo, LectureBoard lectureBoard, MultipartHttpServletRequest req) { // image를 포함한 board글 write
		
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
							lectureBoard.setLectureboardfile(fileList);
							lectureBoard.setLectureNo(lectureNo); // lectureNo를 lectureboard객체에 추가.
							
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
		
		Lecture lectures = boardService.findlectureByMemberNoAndLectureNo(memberNo, lectureNo); // lectureNo와 memberNo를 받아가서 해당 lecture를 가져옴.
		
		model.addAttribute("boarddetail", boardDetails);
		model.addAttribute("lectures",lectures);
		
	return "/lectureboard/boarddetail";
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.GET)
	public String boardUpdate(@RequestParam(name="boardNo") int boardNo, Model model, HttpSession session) { // boardNo와 memberNo, lectureNo를 가지고 update view와 연결.
		
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
	public String boardUpdateWrite(@PathVariable int boardNo, LectureBoard lectureBoard, MultipartHttpServletRequest req) { // boardNo를 가지고 덮어 씀 = update
		
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
		
		int deletedNo = 0;

		LectureBoard boardDetails = boardService.boardDetail(boardNo);
		int lectureNo = boardDetails.getLectureNo(); // lectureNo와 boardNo는 읽어 온다.
		
		boardDetails.setDeletedNo(deletedNo);

		boardService.boarddelete(boardDetails); // boardNo에 해당하는 글 삭제

		return "redirect:/subsq/boardlist?lectureNo=" + lectureNo;
	}
	
	@GetMapping(value = "/mysubsqcheck/{lectureNo}")
	public String mysubsqCheck(@PathVariable int lectureNo, Model model, HttpSession session, HttpServletResponse response )throws Exception {
		
		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		int memberNo = member.getMemberNo();
		Subscription subsq = boardService.findByMemberNoAndLectureNo(memberNo, lectureNo);
		if (subsq == null) {
		boardService.saveByMemberNoAndLectureNo(memberNo, lectureNo);
		
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 구독하신 강좌 입니다.'); location.href='/subsq/lecture';</script>");
			out.flush();
			
			return "redirect:/subsq/lecture";
		}
		
		return "/home";
	}
	
	@GetMapping(value="/mylectdel/{lectureNo}")
	public String mylectureDelete(@PathVariable int lectureNo, Model model) {

		int deleteNo = 0;
        int deletesNo = deleteNo +1 ;
		
		Lecture lecture = boardService.findlectureByLectureNoAndDeleteNo(lectureNo, deletesNo);
		lecture.setDeleteNo(deleteNo);
		
		boardService.deleteUpdate(lecture);
		
		return "redirect:/subsq/lecture";
	}
	
}