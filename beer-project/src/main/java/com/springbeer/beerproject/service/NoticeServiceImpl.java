package com.springbeer.beerproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbeer.beerproject.entity.Lecture;
import com.springbeer.beerproject.entity.Notice;
import com.springbeer.beerproject.repository.LectureRepository;
import com.springbeer.beerproject.repository.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private LectureRepository lectureRepository;
	
	@Override
	public void addNote(Notice notice) {
		
		noticeRepository.save(notice);
	}

	@Override
	public List<Notice> loadNoteList() {
		List<Notice> notices = (List<Notice>)noticeRepository.findAll(); // 형식이 달라서 형변환 함 
		return notices;
	}

	@Override
	public void deleteNoteById(Long noticeId) {
		noticeRepository.deleteById(noticeId);
	}

	@Override
	public List<Lecture> findlecture() {
		
		int deleteNo = 1;
		
		List<Lecture> lects = (List<Lecture>)lectureRepository.findByDeleteNo(deleteNo);
		
		return lects;
	}

	@Override
	public void deleteNote(int noteId) {
		noticeRepository.deleteById((long)noteId);
		
	}

	@Override
	public void updateNote(Notice note) {
		noticeRepository.save(note);
	}

	@Override
	public List<Lecture> loadLectureList() {
		
		int deleteNo = 1;
		List<Lecture> findlecture = (List<Lecture>)lectureRepository.findListByDeleteNo(deleteNo); 
		return findlecture;
	}

	@Override
	public Notice noticeDetail(Long id) {
		
		Notice notice = noticeRepository.findByid(id);
		
		return notice;
	}
}
