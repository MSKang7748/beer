package com.springbeer.beerproject.service;

import java.util.List;

import com.springbeer.beerproject.entity.Lecture;
import com.springbeer.beerproject.entity.Notice;

public interface NoticeService {
	
	void addNote(Notice note);

	List<Notice> loadNoteList();

	void deleteNoteById(Long noticeId);

	List<Lecture> findlecture();

	void deleteNote(int noteId);

	void updateNote(Notice note);

	List<Lecture> loadLectureList();

}
