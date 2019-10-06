package com.springbeer.beerproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbeer.beerproject.entity.Lecture;
import com.springbeer.beerproject.entity.Notice;
import com.springbeer.beerproject.service.NoticeService;

@Controller
@RequestMapping(path = { "/note-api" })
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@PostMapping(path = { "/add" })
	@ResponseBody
	public String addNote(@RequestBody Notice note) {

//		//데이터베이스에 데이터 저장.
//		System.out.println(note.getTitle());
//		System.out.println(note.getContent());
//		System.out.println(note.getTags()); // console에 출력 되는지 확인 용도

		noticeService.addNote(note); // database 연동 용
		
		return "{ \"result\": \"success\" }";
	}

	@GetMapping(path = {"/list"})
	@ResponseBody
	public List<Notice> loadNoteList() {
		
		List<Notice> notices = noticeService.loadNoteList();
		return notices;
	}
	
	@DeleteMapping(path = { "/delete/{noteId}"}) // REST 방식을 적용
	@ResponseBody
	public String deleteNote(@PathVariable int noteId) { // 경로를 통해 변수를 받으므로 PathVariable 씀
		
		noticeService.deleteNote(noteId);
		return "success";
	}

	@PutMapping(path = {"/update"})
	@ResponseBody
	public String updateNote(@RequestBody Notice note) {

		noticeService.updateNote(note);
		return "success";
	}
	
	@GetMapping(path = {"/lecturelist"})
	@ResponseBody
	public List<Lecture> loadLectureList() {
		
		List<Lecture> lecture = noticeService.loadLectureList();
		return lecture;
	}
}